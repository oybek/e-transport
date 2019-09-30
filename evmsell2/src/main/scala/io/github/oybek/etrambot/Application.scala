package io.github.oybek.etrambot

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import io.github.oybek.etrambot.vk.VkApiImpl

object Application extends App {

  BlazeClientBuilder[Task](global).resource.use { client =>
    val api = new VkApiImpl[Task](client)
    new EchoBot[Task](client, api).start
  }.runSyncUnsafe()

}
