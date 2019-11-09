package io.github.oybek.catpc

import cats.implicits._
import cats.effect.concurrent.Ref
import config.Config
import io.github.oybek.catpc.db.DB
import io.github.oybek.catpc.db.repository._
import io.github.oybek.catpc.model.Offer
import io.github.oybek.catpc.service.{CityService, Jaw, WallPostHandler}
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.{BlazeClientBuilder, BlazeClientConfig}

import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

import io.github.oybek.catpc.vk.api.{GetLongPollServerReq, VkApiHttp4s}
import org.http4s.client.middleware.Logger

object Application extends App {

  val root =
    for {
      userStates <- Ref.of[Task, Map[Long, List[Offer]]](Map())
      configFile <- Task.delay { Option(System.getProperty("application.conf")) }
      config <- Config.load[Task](configFile)
      transactor <- DB.transactor[Task](config.database)
      wallPostHandler = WallPostHandler(config.model)
      repos = Repositories(
        OfferRepository(transactor),
        StatsRepository(transactor),
        CityRepository(transactor),
        UserRepository(transactor)
      )
      cityService = CityService[Task](repos)
      _ <- DB.initialize(transactor)
      _ <- BlazeClientBuilder[Task](global)
        .withResponseHeaderTimeout(FiniteDuration(60, TimeUnit.SECONDS))
        .resource.use { httpClient =>
          val client = Logger(logHeaders = false, logBody = true)(httpClient)
          val vkApi = VkApiHttp4s[Task](client)
          val jaw = Jaw(repos, wallPostHandler, vkApi, cityService, config.serviceKey)

          for {
            _ <- Bot[Task](
              client,
              userStates,
              vkApi,
              cityService,
              repos,
              config.getLongPollServerReq,
              jaw,
              wallPostHandler
            ).start
          } yield ()
        }
    } yield ()

  root.runSyncUnsafe()
}
