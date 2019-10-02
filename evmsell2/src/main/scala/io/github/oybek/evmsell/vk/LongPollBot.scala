package io.github.oybek.evmsell.vk

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
      _ <- pollRes match {
        case PollWithUpdates(ts, updates) =>
          for {
            _ <- updates.traverse(onEvent)
            _ <- poll(pollReq.copy(ts = ts))
          } yield ()

        case PollFailed(Some(ts), _) => start
      }
    } yield ()

  final def start: F[Unit] =
    for {
      getLongPollServerRes <- getLongPollServer
      longPollServer = getLongPollServerRes.response
      pollReq = PollReq(
        server = longPollServer.server,
        key = longPollServer.key,
        ts = longPollServer.ts,
        waitt = 20)
      _ <- poll(pollReq)
    } yield ()

  final def onEvent(event: Event): F[Unit] = event match {
    case messageNew: MessageNew => onMessageNew(messageNew.copy(text = messageNew.text.take(40).toLowerCase))
    case wallPostNew: WallPostNew => onWallPostNew(wallPostNew)
  }

  def onMessageNew(message: MessageNew): F[Unit]
  def onWallPostNew(wallPostNew: WallPostNew): F[Unit]
}
