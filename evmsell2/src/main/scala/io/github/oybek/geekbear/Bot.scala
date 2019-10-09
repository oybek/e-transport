package io.github.oybek.geekbear

import cats.implicits._
import cats.effect.{Async, Concurrent, Sync, Timer}
import cats.effect.syntax.all._
import cats.effect.concurrent.Ref
import io.github.oybek.geekbear.db.repository.{OfferRepository, OfferRepositoryAlgebra, StatsRepositoryAlgebra, UserRepository, UserRepositoryAlgebra}
import io.github.oybek.geekbear.model.Offer
import io.github.oybek.geekbear.service.{Jaw, WallPostHandler}
import io.github.oybek.geekbear.vk._
import io.github.oybek.geekbear.vk.api.{Action, Button, GetLongPollServerReq, Keyboard, SendMessageReq, VkApi, WallCommentReq}
import org.http4s.client.Client
import org.slf4j.LoggerFactory

case class Bot[F[_]: Async: Timer: Concurrent](httpClient: Client[F],
                                              userStates: Ref[F, Map[Long, List[Offer]]],
                                              vkApi: VkApi[F],
                                              offerRepository: OfferRepositoryAlgebra[F],
                                              userRepository: UserRepositoryAlgebra[F],
                                              statsRepository: StatsRepositoryAlgebra[F],
                                              getLongPollServerReq: GetLongPollServerReq,
                                              jaw: Jaw[F],
                                              wallPostHandler: WallPostHandler)
  extends LongPollBot[F](httpClient, vkApi, getLongPollServerReq) {

  private val adminIds = List(213461412L)

  private val log = LoggerFactory.getLogger("bot")

  override def onMessageNew(message: MessageNew): F[Unit] =
    for {
      _ <- Sync[F].delay { log.info(s"Got message: $message") }

      _ <- "жри -?[0-9]+".r
        .findFirstIn(message.text.toLowerCase)
        .traverse { q =>
          val groupId = q.split(' ')(1).toLong
          for {
            _ <- sendMessage(message.fromId, "Начинаю пожирать данную группу...")
            res <- jaw.breakfast(List(groupId), adminIds)
            _ <- sendMessage(message.fromId, s"Сожрал ${res.length} постов\nПереварил ${res.count(_.isRight)}")
          } yield ()
        }.whenA(adminIds.contains(message.fromId)).start.void

      _ <- message.geo.map { geo =>
        for {
          _ <- Sync[F].delay { log.info(s"Message has geo, updating user_info's geo: $geo") }
          _ <- userRepository.upsert(message.fromId -> geo.coordinates)
          _ <- sendMessage(message.fromId,
            s"""
              |Отлично! Я обновил твое местоположение 📍
              |${geo.place.map(_.title).getOrElse("")}
              |""".stripMargin)
        } yield ()
      }.getOrElse {
        if (message.text.toLowerCase == "начать") {
          sendMessage(message.fromId,
            """
              |Привет - Я Гик Медведь 🐻!
              |В своей группе ВК я помогаю людям продать или купить компьютерную технику
              |Я запомнил все объявления на стене и быстро найду нужное тебе
              |Напиши 'помощь' и я подскажу что умею
              |""".stripMargin)
        } else {
          wallPostHandler.getTType(message.text.toLowerCase) match {
            case Some(thing) => for {
              user <- userRepository.selectById(message.fromId)
              _ <- user.map(usr => whenNewSearch(message)(thing, usr._2)).getOrElse(
                sendMessage(message.fromId,
                  """
                    |Перед тем как начать поиск - отправь геолокацию
                    |Чтобы я знал в каком городе искать объявления
                    |""".stripMargin,
                  None,
                  Keyboard(true, List(List(Button(Action("location"))))).some
                )
              )
            } yield ()
            case None => whenNotSearch(message)
          }
        }
      }
    } yield ()

  def whenNewSearch(message: MessageNew)(thing: String, userPos: Coord): F[Unit] =
    for {
      offers <- offerRepository.selectByTType(thing).map { offs =>
        val from = "от[ ]+\\d+".r.findFirstIn(message.text).map(_.split(' ')(1).toLong).getOrElse(0L)
        val to = "до[ ]+\\d+".r.findFirstIn(message.text).map(_.split(' ')(1).toLong).getOrElse(1000000L)
        offs.filter(offer =>
          offer.price.exists(x => x >= from && x <= to) &&
          /* TODO: Uncomment, when expaned to several citites offer.coord.exists(_.distKmTo(userPos) < 50) && */
          offer.sold.isEmpty
        ).sortWith {
          case (off1, off2) => off1.date > off2.date
        }
      }
      _ <- offers match {
        case Nil =>
          sendMessage(message.fromId, s"Не нашел объявлений по твоему запросу")
        case offersNonEmpty =>
          def word(n: Int): String = n match {
            case 1 => "объявление"
            case 2|3|4 => "объявления"
            case _ => "объявлений"
          }
          sendMessage(
            message.fromId,
            s"""
               |Я нашел ${offers.length} ${word(offers.length)}
               |${if (offers.length > 1) "Вот первое. Напиши 'еще' я скину следующее" else "Вот оно:" }
               |""".stripMargin,
            attachment = s"wall${offersNonEmpty.head.groupId}_${offersNonEmpty.head.id}".some,
            keyboard =
              if (offers.length > 1)
                Keyboard(true, List(List(Button(Action("text", "еще".some))))).some
              else
                None
          )
      }
      _ <- userStates.modify {
        states => (states + (message.fromId -> offers), states)
      }
    } yield ()

  def whenNotSearch(message: MessageNew): F[Unit] =
    for {
      states <- userStates.get
      offers = states.getOrElse(message.fromId, List())
      _ <- message.text.toLowerCase match {
        case "еще" if offers.length >= 2 =>
          val rest = offers.tail
          for {
            _ <- userStates.modify { x =>
              (x + (message.fromId -> rest), x)
            }.void
            _ <- sendMessage(
              message.fromId,
              if (rest.length == 1) "" else s"Еще ${rest.length-1} в списке",
              Some(s"wall${rest.head.groupId}_${rest.head.id}"),
              if (rest.length > 1)
                Keyboard(true, List(List(Button(Action("text", "еще".some))))).some
              else None
            )
          } yield ()

        case "помощь" =>
          sendMessage(message.fromId,
            s"""
               |Я нахожу объявления по названию товара, например:
               |Ноут, Системник или Видяха и т. д.
               |
               |Еще я умею фильтровать объявления по цене
               |Ты можешь написать:
               |Ноут от 5000 до 10000
               |Или просто:
               |Системник до 20000
               |
               |Объявления я ищу в твоем городе
               |""".stripMargin
          )

        case "еще" =>
          sendMessage(message.fromId, s"Какой товар ищешь? (Моник, мышку, блок питания и т. д.)")

        case "мед" =>
          sendMessage(message.fromId, s"Где?!")

        case "привет" =>
          sendMessage(message.fromId, "Привет - Я Гик Медведь)")

        case "статистика" =>
          for {
            stats <- statsRepository.stats
            _ <- sendMessage(message.fromId, s"Всего предложений ${stats._1}\nЗа последние 24 часа ${stats._2}")
          } yield ()

        case _ =>
          sendMessage(message.fromId,
            s"""
               |Не понял что ты ищешь!
               |Напиши 'помощь' - я напишу что умею
               |""".stripMargin,
            None,
            Keyboard(true, List(List(Button(Action("text", "помощь".some))))).some)
      }
    } yield ()

  private def sendMessage(to: Long, text: String,
                          attachment: Option[String] = None,
                          keyboard: Option[Keyboard] = None): F[Unit] = {
    val sendMessageReq = SendMessageReq(
      userId = to,
      message = text,
      version = getLongPollServerReq.version,
      accessToken = getLongPollServerReq.accessToken,
      attachment = attachment,
      keyboard = keyboard
    )
    for {
      _ <- Sync[F].delay { log.info(s"Sending message: $sendMessageReq") }
      _ <- vkApi.sendMessage(sendMessageReq).void
    } yield ()
  }

  override def onWallPostNew(wallPostNew: WallPostNew): F[Unit] =
    wallPostNew.postType match {
      case Some("suggest") =>
        for {
          _ <- Sync[F].delay { log.info(s"Got new wallpost suggestion: $wallPostNew") }
          _ <- adminIds.traverse(id =>
            sendMessage(
              id,
              "Новая запись на модерацию",
              Some(s"wall-${getLongPollServerReq.groupId}_${wallPostNew.id}")
          )).void
        } yield ()

      case Some("post") =>
        for {
          _ <- Sync[F].delay { log.info(s"Posting new wallpost: ${wallPostNew.toString}") }

          userInfo = for {
            userId <- wallPostNew.signerId
            geo <- wallPostNew.geo
          } yield userId -> geo.coordinates

          _ <- userInfo.traverse(x => userRepository.upsert(x))
          coord <- wallPostNew.signerId.flatTraverse(userRepository.coordById)

          offer = wallPostHandler.wallPostToOffer(wallPostNew)
            .copy(
              latitude = coord.map(_.latitude),
              longitude = coord.map(_.longitude)
            )
          _ <- offerRepository.insert(offer)
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
        offerOpt <- offerRepository.selectById(wallReplyNew.postId)
        _ <- offerOpt.filter { offer =>
          offer.fromId == wallReplyNew.fromId && offer.sold.isEmpty
        }.traverse { offer =>
          for {
            _ <- offerRepository.sold(offer.id, wallReplyNew.date)
            _ <- vkApi.wallComment(
              WallCommentReq(
                ownerId = -getLongPollServerReq.groupId.toLong,
                postId = offer.id,
                message = s"Продан за ${wallReplyNew.date - offer.date} секунд",
                version = getLongPollServerReq.version,
                accessToken = getLongPollServerReq.accessToken,
              )
            )
          } yield ()
        }
      } yield()
      case _ => Sync[F].delay().void
    }
}
