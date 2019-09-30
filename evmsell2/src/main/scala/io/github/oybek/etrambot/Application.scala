package io.github.oybek.etrambot

import cats.implicits._
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s._
import io.github.oybek.etrambot.vk.{GetLongPollServerReq, GetLongPollServerRes, PollReq, PollRes, VkApiImpl}

object Application extends App {

  BlazeClientBuilder[Task](global).resource.use { client =>
    val api = new VkApiImpl[Task](client)

    // TODO: can we use val here?
    def getLongPollServer: Task[GetLongPollServerRes] =
      api.getLongPollServer(GetLongPollServerReq(
        groupId = "177134356",
        accessToken = "19253825dedcd7679631e409d2ce8b5e65fa017c0c03bde069ccfc2424fe1480d3c5d8d92f3f138cefd50",
        version = "5.80"
      ))

    def poll(pollReq: PollReq): Task[Unit] =
      for {
        pollRes <- api.poll(pollReq)
        _ <- Task.delay { println(s"hello $pollRes") }
        _ <- poll(pollReq.copy(ts = pollRes.ts))
      } yield ()

    for {
      getLongPollServerRes <- getLongPollServer
      longPollServer = getLongPollServerRes.response
      pollReq = PollReq(
        server = longPollServer.server,
        key = longPollServer.key,
        ts = longPollServer.ts,
        waitt = 5)
      _ <- poll(pollReq)
    } yield ()

  }.runSyncUnsafe()

}
