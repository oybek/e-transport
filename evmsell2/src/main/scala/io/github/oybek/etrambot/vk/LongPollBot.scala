package io.github.oybek.etrambot.vk

import cats.implicits._
import cats.effect.Sync
import org.http4s.client.Client

abstract class LongPollBot[F[_]: Sync](httpClient: Client[F],
                                       vkApi: VkApi[F],
                                       getLongPollServerReq: GetLongPollServerReq) {

  // TODO: can we use val here?
  final private def getLongPollServer: F[GetLongPollServerRes] =
    vkApi.getLongPollServer(getLongPollServerReq)

  final def poll(pollReq: PollReq): F[Unit] =
    for {
      pollRes <- vkApi.poll(pollReq)
      _ <- pollRes.updates.traverse(onEvent) // TODO: use fibers
      _ <- poll(pollReq.copy(ts = pollRes.ts))
    } yield ()

  final def start: F[Unit] =
    for {
      getLongPollServerRes <- getLongPollServer
      longPollServer = getLongPollServerRes.response
      pollReq = PollReq(
        server = longPollServer.server,
        key = longPollServer.key,
        ts = longPollServer.ts,
        waitt = 9)
      _ <- poll(pollReq)
    } yield ()

  final def onEvent(event: Event): F[Unit] = event match {
    case messageNew: MessageNew => onMessageNew(messageNew)
    case wallPostNew: WallPostNew => onWallPostNew(wallPostNew)
  }

  def onMessageNew(message: MessageNew): F[Unit]
  def onWallPostNew(wallPostNew: WallPostNew): F[Unit]
}