package io.github.oybek.evmsell

import cats.implicits._
import cats.effect.Sync
import io.github.oybek.evmsell.service.WallPostHandler
import io.github.oybek.evmsell.vk._
import org.http4s.client.Client

class Bot[F[_]: Sync](httpClient: Client[F],
                      vkApi: VkApi[F],
                      getLongPollServerReq: GetLongPollServerReq,
                      wallPostHandler: WallPostHandler)
  extends LongPollBot[F](httpClient, vkApi, getLongPollServerReq) {

  override def onMessageNew(message: MessageNew): F[Unit] =
    for {
      _ <- vkApi.sendMessage(SendMessageReq(
        userId = message.fromId,
        message = message.text,
        version = getLongPollServerReq.version,
        accessToken = getLongPollServerReq.accessToken
      ))
    } yield ()

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
