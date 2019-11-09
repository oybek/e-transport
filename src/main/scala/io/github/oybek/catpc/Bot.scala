package io.github.oybek.catpc

import cats.implicits._
import cats.effect.{Async, Concurrent, Sync, Timer}
import cats.effect.syntax.all._
import cats.effect.concurrent.Ref
import io.github.oybek.catpc.db.repository.Repositories
import io.github.oybek.catpc.model.Offer
import io.github.oybek.catpc.service.{CityServiceAlg, Jaw, WallPostHandler}
import io.github.oybek.catpc.vk._
import io.github.oybek.catpc.vk.api.{Action, Button, GetLongPollServerReq, Keyboard, SendMessageReq, VkApi, WallCommentReq}
import org.http4s.client.Client
import org.slf4j.LoggerFactory
import scala.concurrent.duration._

case class Bot[F[_] : Async : Timer : Concurrent](httpClient: Client[F], userStates: Ref[F, Map[Long, List[Offer]]],
                                                  vkApi: VkApi[F],
                                                  cityServiceAlg: CityServiceAlg[F],
                                                  repo: Repositories[F],
                                                  getLongPollServerReq: GetLongPollServerReq,
                                                  jaw: Jaw[F],
                                                  wallPostHandler: WallPostHandler)
  extends LongPollBot[F](httpClient, vkApi, getLongPollServerReq) {

  private val defaultCity = 829

  private val adminIds = List(213461412L)

  private val log = LoggerFactory.getLogger("bot")

  private val cityButton = Button(Action("text", "город поиска".some))
  private val helpButton = Button(Action("text", "помощь".some))
  private val statButton = Button(Action("text", "статистика".some))

  private def defaultKeyboard(topButton: Option[Button] = None) = Keyboard(
    oneTime = false,
    buttons =
      (if (topButton.nonEmpty) List(List(topButton.get)) else List()) ++
        List(List(cityButton, helpButton, statButton))
  ).some

  override def onMessageNew(message: MessageNew): F[Unit] =
    message.text match {
      case text if text.matches("жри -?[0-9]+") =>
        "-?[0-9]+".r
          .findFirstIn(text)
          .traverse { groupId =>
            for {
              _ <- sendMessage(message.peerId, "Начинаю пожирать данную группу...")
              res <- jaw.breakfast(List(groupId.toLong), adminIds, message.geo.map(_.coordinates))
              _ <- sendMessage(message.peerId, s"Сожрал ${res.length} постов\nПереварил ${res.count(_.isRight)}")
            } yield ()
          }.whenA(adminIds.contains(message.peerId) && message.geo.nonEmpty).start.void

      case text if Seq(
        text.startsWith("это")
      ).forall(identity) =>
        (text.split(' ') match {
          case Array(_, textPart) =>
            for {
              states <- userStates.get
              offers = states.getOrElse(message.peerId, List())
              query = for {
                offer <- offers.headOption
                ttype <- wallPostHandler.getTType(textPart)
              } yield (offer, ttype)
              _ <- query match {
                case None =>
                  sendMessage(message.peerId, "Чегооо блять?", None, None)
                case Some((offer, ttype)) =>
                  for {
                    _ <- repo.ofOffer.changeTType(offer.id, offer.groupId, ttype)
                    _ <- sendMessage(message.peerId, "Обновил босс", None, None)
                  } yield ()
              }
            } yield ()
          case _ =>
            sendMessage(message.peerId, "И че это?", None, None)
        }).whenA(adminIds.contains(message.peerId)).start.void

      case text =>
        for {
          _ <- Sync[F].delay {
            log.info(s"Got message: $message")
          }
          cityOfUser <- repo.ofUser.cityOf(message.fromId)
          _ <- repo.ofUser.upsert(message.fromId -> defaultCity).whenA(cityOfUser.isEmpty)
          _ <- message.geo.map { geo =>
            for {
              city <- cityServiceAlg.findByCoord(geo.coordinates)
              _ <- Sync[F].delay {
                log.info(s"Message has geo, updating user_info's geo: $geo")
              }
              _ <- repo.ofUser.upsert(message.peerId -> city.id)
              _ <- sendMessage(message.peerId,
                s"""
                   |Отлично!
                   |Теперь я буду искать объявления в городе ${city.name}
                   |""".stripMargin, None, defaultKeyboard())
            } yield ()
          }.getOrElse {
            wallPostHandler.getTType(text) match {
              case Some(thing) => whenNewSearch(message)(thing)
              case None => whenNotSearch(message)
            }
          }
        } yield ()
    }

  def whenNewSearch(message: MessageNew)(thing: String): F[Unit] =
    for {
      userInfo <- repo.ofUser.selectById(message.fromId)
      offers <- userInfo.traverse { case (userId, cityId) =>
        repo.ofOffer.selectByTTypeAndCity(thing, cityId).map { offs =>
          val minPrice = "от[ ]+\\d+".r.findFirstIn(message.text).map(_.split(' ')(1).toLong).getOrElse(0L)
          val maxPrice = "до[ ]+\\d+".r.findFirstIn(message.text).map(_.split(' ')(1).toLong).getOrElse(Long.MaxValue)
          offs.filter(offer =>
            offer.price.exists(x => x >= minPrice && x <= maxPrice) &&
              offer.sold.isEmpty
          ).sortWith {
            case (off1, off2) => off1.date > off2.date
          }
        }
      }.map(_.getOrElse(Nil))
      _ <- offers match {
        case Nil =>
          sendMessage(message.peerId, s"Нет объявлений по твоему запросу", None, defaultKeyboard())
        case offersNonEmpty =>
          def word(n: Int): String = n match {
            case 1 => "объявление"
            case 2 | 3 | 4 => "объявления"
            case _ => "объявлений"
          }

          for {
            _ <- sendMessage(message.peerId, "Так, начинаю искать...", Some("doc-165649310_524800694"))
            _ <- Timer[F].sleep(3500 millis)
            _ <- sendMessage(
              message.peerId,
              s"""
                 |Я нашел ${offers.length} ${word(offers.length)}
                 |${if (offers.length > 1) "Вот первое. Напиши 'еще' я скину следующее" else "Вот оно:"}
                 |""".stripMargin,
              attachment = s"wall${offersNonEmpty.head.groupId}_${offersNonEmpty.head.id}".some,
              keyboard =
                if (offers.length > 1)
                  defaultKeyboard(Some(
                    Button(Action("text", Some(s"еще [${offers.length - 1}]")))
                  ))
                else
                  defaultKeyboard()
            )
          } yield ()
      }
      _ <- userStates.modify {
        states => (states + (message.peerId -> offers), states)
      }
    } yield ()

  def whenNotSearch(message: MessageNew): F[Unit] =
    for {
      states <- userStates.get
      offers = states.getOrElse(message.peerId, List())
      _ <- message.text.toLowerCase match {

        case "еще" | "ещё" if offers.length >= 2 =>
          val rest = offers.tail
          for {
            _ <- userStates.modify { x =>
              (x + (message.peerId -> rest), x)
            }.void
            _ <- sendMessage(
              message.peerId,
              if (rest.length == 1) "" else s"Еще ${rest.length - 1} в списке",
              Some(s"wall${rest.head.groupId}_${rest.head.id}"),
              if (rest.length > 1)
                defaultKeyboard(Some(
                  Button(Action("text", Some(s"еще [${rest.length - 1}]")))
                ))
              else
                defaultKeyboard()
            )
          } yield ()

        case "помощь" | "начать" =>
          for {
            _ <- sendMessage(message.peerId,
              """
                |Привет - Я Гик Медведь!
                |""".stripMargin, None, defaultKeyboard()).whenA(message.text.toLowerCase == "начать")
            _ <- sendMessage(message.peerId,
              s"""
                 |1. Помогу найти нужную вещь
                 |Напиши что ищешь от и до скольки, например:
                 |Системник до 20000
                 |Материнка от 1000 до 3000
                 |
                 |2. Я могу добавить твое объявление в поиск
                 |Для этого предложи пост на стену в формате:
                 |1. Название товара (Ноут, Системник, Моник, Материка и т. п.)
                 |2. Цену в рублях
                 |3. Описание и фотки
                 |4. Город (По умолчанию Екатеринбург)
                 |
                 |3. Подскажу сколько есть объявлений по каждому товару
                 |Напиши "статистика"
                 |""".stripMargin, None, defaultKeyboard())
          } yield ()

        case "еще" | "ещё" =>
          sendMessage(message.peerId, s"Что еще ищешь?")

        case "статистика" =>
          for {
            stats <- repo.ofStat.stats
            byTypeCount = stats._3.sortBy(-_._2).map {
              case (ttype, count) => s"${wallPostHandler.getRussianName(ttype)} = $count штук"
            }.mkString("\n")
            _ <- sendMessage(message.peerId,
              s"""
                 |Всего предложений ${stats._1}
                 |За последние 24 часа ${stats._2}
                 |
                 |$byTypeCount
                 |""".stripMargin)
          } yield ()

        case "город поиска" =>
          for {
            userOpt <- repo.ofUser.selectById(message.fromId)
            city <- userOpt.flatTraverse {
              case (userId, cityId) => repo.ofCity.selectById(cityId)
            }
            cityText = city.map(_.name).getOrElse("Не определен")
            _ <- sendMessage(message.peerId,
              s"""
                 |Твой город поиска: $cityText
                 |Отправь геопозицию если хочешь сменить город поиска
                 |""".stripMargin, None, defaultKeyboard())
          } yield ()

        case _ =>
          sendMessage(message.peerId,
            s"""
               |Не очень понял тебя
               |Напиши 'помощь' я подскажу что умею
               |""".stripMargin,
            None, defaultKeyboard())
      }
    } yield ()

  private def sendMessage(to: Long, text: String,
                          attachment: Option[String] = None,
                          keyboard: Option[Keyboard] = None): F[Unit] = {
    val sendMessageReq = SendMessageReq(
      peerId = to,
      message = text,
      version = getLongPollServerReq.version,
      accessToken = getLongPollServerReq.accessToken,
      attachment = attachment,
      keyboard = keyboard
    )
    for {
      _ <- Sync[F].delay {
        log.info(s"Sending message: $sendMessageReq")
      }
      _ <- vkApi.sendMessage(sendMessageReq).void
    } yield ()
  }

  override def onWallPostNew(wallPostNew: WallPostNew): F[Unit] =
    wallPostNew.postType match {
      case Some("suggest") =>
        for {
          _ <- Sync[F].delay {
            log.info(s"Got new wallpost suggestion: $wallPostNew")
          }
          _ <- adminIds.traverse(id =>
            sendMessage(
              id,
              "Новая запись на модерацию",
              Some(s"wall-${getLongPollServerReq.groupId}_${wallPostNew.id}")
            )).void
        } yield ()

      case Some("post") =>
        for {
          _ <- Sync[F].delay {
            log.info(s"Posting new wallpost: ${wallPostNew.toString}")
          }
          city <- wallPostNew.geo.traverse(geo => cityServiceAlg.findByCoord(geo.coordinates))

          userInfo = for {
            userId <- wallPostNew.signerId
            cityId <- city.map(_.id)
          } yield userId -> cityId

          _ <- userInfo.traverse(x => repo.ofUser.upsert(x))
          city <- wallPostNew.signerId.flatTraverse(repo.ofUser.cityOf)

          offer = wallPostHandler.wallPostToOffer(wallPostNew)
            .copy(city = city)
          _ <- repo.ofOffer.insert(offer)
          _ <- wallPostNew.signerId map { signerId =>
            sendMessage(
              signerId,
              "Твое предложение опубликовано",
              Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")).void
          } getOrElse {
            adminIds.traverse(id =>
              sendMessage(
                id,
                "Ты опубликовал предложение без подписи! Поправь и обнови в базе",
                attachment = Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")
              )).void
          }
        } yield ()

      case _ =>
        Sync[F].delay().void
    }

  override def onWallReplyNew(wallReplyNew: WallReplyNew): F[Unit] =
    wallReplyNew.text.toLowerCase match {
      case "продан" => for {
        offerOpt <- repo.ofOffer.selectById(wallReplyNew.postId)
        _ <- offerOpt.filter { offer =>
          offer.fromId == wallReplyNew.fromId && offer.sold.isEmpty
        }.traverse { offer =>
          for {
            _ <- repo.ofOffer.sold(offer.id, wallReplyNew.date)
            _ <- vkApi.wallComment(
              WallCommentReq(
                ownerId = -getLongPollServerReq.groupId.toLong,
                postId = offer.id,
                message = s"Продан в течении ${daysHours(wallReplyNew.date - offer.date)}",
                replyToComment = wallReplyNew.id,
                version = getLongPollServerReq.version,
                accessToken = getLongPollServerReq.accessToken,
              )
            )
          } yield ()
        }
      } yield ()
      case _ => Sync[F].delay().void
    }

  private def daysHours(seconds: Long): String = {
    val days = seconds / (24 * 60 * 60)
    val hours = seconds % (24 * 60 * 60) / (60 * 60)
    s"${if (days > 0) days + " суток" else ""} ${if (hours > 0) hours + " часов" else " часа"}"
  }
}
