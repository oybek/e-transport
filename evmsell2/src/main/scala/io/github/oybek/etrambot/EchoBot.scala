package io.github.oybek.etrambot

import cats.implicits._
import cats.effect.Sync
import io.github.oybek.etrambot.vk._
import org.http4s.client.Client

class EchoBot[F[_]: Sync](httpClient: Client[F],
                          vkApi: VkApi[F],
                          getLongPollServerReq: GetLongPollServerReq)
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
          _ <- vkApi.sendMessage(SendMessageReq(
            userId = wallPostNew.signerId.get,
            message = "Ваша запись одобрена",
            version = getLongPollServerReq.version,
            accessToken = getLongPollServerReq.accessToken,
            attachment = Some(s"wall${wallPostNew.ownerId}_${wallPostNew.id}")
          ))
        } yield ()

      case _ =>
        Sync[F].delay().void
    }
}
