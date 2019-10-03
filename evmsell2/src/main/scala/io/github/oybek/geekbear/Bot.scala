package io.github.oybek.geekbear

import cats.implicits._
import cats.effect.Sync
import cats.effect.concurrent.Ref
import io.github.oybek.geekbear.db.repository.{OfferRepository, UserRepository}
import io.github.oybek.geekbear.model.Offer
import io.github.oybek.geekbear.service.WallPostHandler
import io.github.oybek.geekbear.vk._
import org.http4s.client.Client
import org.slf4j.LoggerFactory

case class Bot[F[_]: Sync](httpClient: Client[F],
                      userStates: Ref[F, Map[Long, List[Offer]]],
                      vkApi: VkApi[F],
                      offerRepository: OfferRepository[F],
                      userRepository: UserRepository[F],
                      getLongPollServerReq: GetLongPollServerReq,
                      wallPostHandler: WallPostHandler)
  extends LongPollBot[F](httpClient, vkApi, getLongPollServerReq) {

  private val log = LoggerFactory.getLogger("bot")

  override def onMessageNew(message: MessageNew): F[Unit] =
    for {
      _ <- Sync[F].delay {
        log.info(s"Got message: $message")
      }
      _ <- message.geo.map { geo =>
        for {
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
                    |""".stripMargin)
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
          offer.coord.exists(_.distKmTo(userPos) < 50)
        )
      }
      _ <- offers match {
        case Nil =>
          vkApi.sendMessage(SendMessageReq(
            userId = message.fromId,
            message = s"Не нашел объявлений по твоему запросу",
            version = getLongPollServerReq.version,
            accessToken = getLongPollServerReq.accessToken,
          ))
        case offersNonEmpty =>
          def word(n: Int): String = n match {
            case 1 => "объявление"
            case 2|3|4 => "объявления"
            case _ => "объявлений"
          }
          vkApi.sendMessage(SendMessageReq(
            userId = message.fromId,
            message =
              s"""
                 |Я нашел ${offers.length} ${word(offers.length)}
                 |${if (offers.length > 1) "Вот первое. Напиши 'еще' я скину следующее" else "Вот оно:" }
                 |""".stripMargin,
            version = getLongPollServerReq.version,
            accessToken = getLongPollServerReq.accessToken,
            attachment = Some(s"wall-${getLongPollServerReq.groupId}_${offersNonEmpty.head.id}")
          ))
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
              Some(s"wall-${getLongPollServerReq.groupId}_${rest.head.id}")
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

        case _ =>
          sendMessage(message.fromId,
            s"""
               |Не понял что ты ищешь!
               |Напиши 'помощь' - я напишу что умею
               |""".stripMargin)
      }
    } yield ()

  private def sendMessage(to: Long, text: String, attachment: Option[String] = None): F[Unit] = {
    val sendMessageReq = SendMessageReq(
      userId = to,
      message = text,
      version = getLongPollServerReq.version,
      accessToken = getLongPollServerReq.accessToken,
      attachment = attachment
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
          _ <- Sync[F].delay { log.info(s"Got new wallpost: $wallPostNew") }
          _ <- vkApi.sendMessage(SendMessageReq(
            userId = 213461412,
            message = "Новая запись на модерацию",
            version = getLongPollServerReq.version,
            accessToken = getLongPollServerReq.accessToken,
            attachment = Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")
          ))
        } yield ()

      case Some("post") =>
        for {
          _ <- Sync[F].delay { println(wallPostNew.toString) }

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
            vkApi.sendMessage(SendMessageReq(
              userId = signerId,
              message = "Твое предложение опубликовано",
              version = getLongPollServerReq.version,
              accessToken = getLongPollServerReq.accessToken,
              attachment = Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")
            ))
          } getOrElse {
            vkApi.sendMessage(SendMessageReq(
              userId = 213461412,
              message = "Ты опубликовал предложение без подписи! Поправь",
              version = getLongPollServerReq.version,
              accessToken = getLongPollServerReq.accessToken,
              attachment = Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")
            ))
          }
        } yield ()

      case _ =>
        Sync[F].delay().void
    }

}
