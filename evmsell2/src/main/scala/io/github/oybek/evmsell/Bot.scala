package io.github.oybek.evmsell

import cats.implicits._
import cats.effect.Sync
import cats.effect.concurrent.Ref
import io.github.oybek.evmsell.db.repository.OfferRepository
import io.github.oybek.evmsell.model.Offer
import io.github.oybek.evmsell.service.WallPostHandler
import io.github.oybek.evmsell.vk._
import org.http4s.client.Client

case class Bot[F[_]: Sync](httpClient: Client[F],
                      userStates: Ref[F, Map[Long, List[Offer]]],
                      vkApi: VkApi[F],
                      offerRepository: OfferRepository[F],
                      getLongPollServerReq: GetLongPollServerReq,
                      wallPostHandler: WallPostHandler)
  extends LongPollBot[F](httpClient, vkApi, getLongPollServerReq) {

  lazy val sadSmile = "😞"

  override def onMessageNew(message: MessageNew): F[Unit] =
    for {
      _ <- wallPostHandler.getTType(message.text.toLowerCase) match {
        case Some(thing) => whenNewSearch(message)(thing)
        case None => whenNotSearch(message)
      }
    } yield ()

  def whenNewSearch(message: MessageNew)(thing: String): F[Unit] =
    for {
      offers <- offerRepository.selectByTType(thing)
      _ <- offers match {
        case Nil =>
          vkApi.sendMessage(SendMessageReq(
            userId = message.fromId,
            message = s"Нет объявлений по данному товару",
            version = getLongPollServerReq.version,
            accessToken = getLongPollServerReq.accessToken,
          ))
        case offersNonEmpty =>
          vkApi.sendMessage(SendMessageReq(
            userId = message.fromId,
            message = s"Я нашел ${offers.length} объявлений, покажу первую.\nНапиши 'еще' я скину следующие",
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
      _ <- message.text match {
        case "еще" if offers.length >= 2 =>
          val rest = offers.tail
          for {
            _ <- userStates.modify { x =>
              (x + (message.fromId -> rest), x)
            }.void
            _ <- sendMessage(
              message.fromId,
              if (rest.length == 1) "Последнее объявление" else s"Еще есть ${rest.length-1} объявлений в списке",
              Some(s"wall-${getLongPollServerReq.groupId}_${rest.head.id}")
            )
          } yield ()

        case "еще" =>
          sendMessage(message.fromId, s"Какой товар ищешь? (Моник, мышку, блок питания)")

        case _ =>
          sendMessage(message.fromId, s"Не очень понял что ты ищешь $sadSmile")
      }
    } yield ()

  private def sendMessage(to: Long, text: String, attachment: Option[String] = None): F[Unit] = {
    vkApi.sendMessage(SendMessageReq(
      userId = to,
      message = text,
      version = getLongPollServerReq.version,
      accessToken = getLongPollServerReq.accessToken,
      attachment = attachment
    )).void
  }

  override def onWallPostNew(wallPostNew: WallPostNew): F[Unit] =
    wallPostNew.postType match {
      case Some("suggest") =>
        for {
          _ <- Sync[F].delay { println(wallPostNew.toString) }
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
          offer = wallPostHandler.wallPostToOffer(wallPostNew)
          _ <- offerRepository.insert(offer)
          _ <- wallPostNew.signerId map { signerId =>
            vkApi.sendMessage(SendMessageReq(
              userId = signerId,
              message = "Ваше предложение опубликовано",
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
