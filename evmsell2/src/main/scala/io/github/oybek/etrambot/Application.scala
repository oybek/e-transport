package io.github.oybek.etrambot

import config.Config
import io.github.oybek.etrambot.db.DB
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import io.github.oybek.etrambot.vk.{GetLongPollServerReq, VkApiImpl}

object Application extends App {

  val root =
    for {
      config <- Config.load[Task]()
      transactor <- DB.transactor[Task](config.database)
      _ <- DB.initialize(transactor)
      _ <- BlazeClientBuilder[Task](global).resource.use { client =>
        val api = new VkApiImpl[Task](client)
        new EchoBot[Task](client, api, config.getLongPollServerReq).start
      }
    } yield ()

  root.runSyncUnsafe()
}
