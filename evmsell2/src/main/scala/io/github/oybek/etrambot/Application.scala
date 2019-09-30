package io.github.oybek.etrambot

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import io.github.oybek.etrambot.vk.{GetLongPollServerReq, VkApiImpl}

object Application extends App {

  val getLongPollServerReq = GetLongPollServerReq(
    groupId = "177134356",
    accessToken = "19253825dedcd7679631e409d2ce8b5e65fa017c0c03bde069ccfc2424fe1480d3c5d8d92f3f138cefd50",
    version = "5.80"
  )

  BlazeClientBuilder[Task](global).resource.use { client =>
    val api = new VkApiImpl[Task](client)
    new EchoBot[Task](client, api, getLongPollServerReq).start
  }.runSyncUnsafe()
}
