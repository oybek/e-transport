package io.github.oybek.evmsell

import cats.implicits._
import cats.effect.concurrent.Ref
import config.Config
import io.github.oybek.evmsell.db.DB
import io.github.oybek.evmsell.db.repository.OfferRepository
import io.github.oybek.evmsell.model.Offer
import io.github.oybek.evmsell.service.WallPostHandler
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.http4s.client.blaze.BlazeClientBuilder
import io.github.oybek.evmsell.vk.{GetLongPollServerReq, VkApiImpl}

object Application extends App {

  val root =
    for {
      userStates <- Ref.of[Task, Map[Long, List[Offer]]](Map())
      config <- Config.load[Task]()
      transactor <- DB.transactor[Task](config.database)
      wallPostHandler = WallPostHandler(config.model)
      offerRepository = OfferRepository(transactor)
      _ <- DB.initialize(transactor)
      _ <- BlazeClientBuilder[Task](global).resource.use { client =>
        Bot[Task](
          client,
          userStates,
          new VkApiImpl[Task](client),
          offerRepository,
          config.getLongPollServerReq,
          wallPostHandler
        ).start
      }
    } yield ()

  root.runSyncUnsafe()
}
