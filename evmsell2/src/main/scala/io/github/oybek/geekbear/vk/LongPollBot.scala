package io.github.oybek.geekbear.vk

import cats.implicits._
import cats.effect.Sync
import org.http4s.client.Client
import org.slf4j.LoggerFactory

abstract class LongPollBot[F[_]: Sync](httpClient: Client[F],
                                       vkApi: VkApi[F],
                                       getLongPollServerReq: GetLongPollServerReq) {

  private val log = LoggerFactory.getLogger(this.getClass)

  // TODO: can we use val here?
  final private def getLongPollServer: F[GetLongPollServerRes] =
    vkApi.getLongPollServer(getLongPollServerReq)

  final def poll(pollReq: PollReq): F[Unit] =
    for {
      pollRes <- vkApi.poll(pollReq).attempt
      _ <- Sync[F].delay { log.debug(s"$pollRes") }
      _ <- pollRes match {
        case Right(PollWithUpdates(ts, updates)) =>
          for {
            _ <- updates.traverse(onEvent)
            _ <- poll(pollReq.copy(ts = ts))
          } yield ()

        case Right(PollFailed(_, _))
             | Left(_: java.io.IOException) => start
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
    case wallReplyNew: WallReplyNew => onWallReplyNew(wallReplyNew)
  }

  def onMessageNew(message: MessageNew): F[Unit]
  def onWallPostNew(wallPostNew: WallPostNew): F[Unit]
  def onWallReplyNew(wallReplyNew: WallReplyNew): F[Unit]
}
