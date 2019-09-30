package io.github.oybek.etrambot

import config.Config
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import io.github.oybek.etrambot.vk.{GetLongPollServerReq, VkApiImpl}

object Application extends App {

  val root =
    for {
      config <- Config.load[Task]()
      _ <- BlazeClientBuilder[Task](global).resource.use { client =>
        val api = new VkApiImpl[Task](client)
        new EchoBot[Task](client, api, config.getLongPollServerReq).start
      }
    } yield ()

  root.runSyncUnsafe()
}
