package io.github.oybek.etrambot

import doobie.util.transactor.Transactor
import io.github.oybek.etrambot.repo.DoobieRepo
import telegramium.bots.client.ApiHttp4sImp
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.middleware.Logger
import org.http4s.client.blaze.BlazeClientBuilder

import scala.concurrent.duration.Duration

object Application extends App {

  val botApiToken = "973439502:AAGA38qy3mXUPiV6Aq6Mh5hd5GmxourkUpU"
  val baseUrl = s"https://api.telegram.org/bot$botApiToken"

  val transactor = Transactor.fromDriverManager[Task](
    "org.postgresql.Driver",
    "jdbc:postgresql:etramdb",
    "hashimov",
    ""
  )

  BlazeClientBuilder[Task](global).resource.use { httpClient =>
    val http = Logger(logBody = false, logHeaders = false)(httpClient)
    val bot = new ApiHttp4sImp(http, baseUrl = baseUrl)
    val repo = new DoobieRepo[Task](transactor)
    val echoBot = new TramBot(bot, repo)
    echoBot.start()
  }.runSyncUnsafe(Duration.Inf)

}
