package io.github.oybek.etrambot

import cats.effect.Sync
import io.github.oybek.etrambot.vk.{LongPollBot, MessageNew, SendMessageReq, VkApi, WallPostNew}
import org.http4s.client.Client

class EchoBot[F[_]: Sync](httpClient: Client[F], vkApi: VkApi[F]) extends LongPollBot[F](httpClient, vkApi) {
  override def onMessageNew(message: MessageNew): F[Unit] = Sync[F].delay {
    println(s"$message")
  }

  override def onWallPostNew(wallPostNew: WallPostNew): F[Unit] = Sync[F].delay {
    println(s"$wallPostNew")
  }
}
