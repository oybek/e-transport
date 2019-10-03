package io.github.oybek.geekbear

import cats.implicits._
import cats.effect.concurrent.Ref
import config.Config
import io.github.oybek.geekbear.db.DB
import io.github.oybek.geekbear.db.repository.{OfferRepository, UserRepository}
import io.github.oybek.geekbear.model.Offer
import io.github.oybek.geekbear.service.WallPostHandler
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.{BlazeClientBuilder, BlazeClientConfig}
import io.github.oybek.geekbear.vk.{GetLongPollServerReq, VkApiImpl}

import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

import org.http4s.client.middleware.Logger

object Application extends App {

  val root =
    for {
      userStates <- Ref.of[Task, Map[Long, List[Offer]]](Map())
      config <- Config.load[Task]()
      transactor <- DB.transactor[Task](config.database)
      wallPostHandler = WallPostHandler(config.model)
      offerRepository = OfferRepository(transactor)
      userRepository = UserRepository(transactor)
      _ <- DB.initialize(transactor)
      _ <- BlazeClientBuilder[Task](global)
        .withResponseHeaderTimeout(FiniteDuration(60, TimeUnit.SECONDS))
        .resource.use { httpClient =>
          val client = Logger(logHeaders = false, logBody = true)(httpClient)
          Bot[Task](
            client,
            userStates,
            new VkApiImpl[Task](client),
            offerRepository, userRepository,
            config.getLongPollServerReq,
            wallPostHandler
          ).start
        }
    } yield ()

  root.runSyncUnsafe()
}