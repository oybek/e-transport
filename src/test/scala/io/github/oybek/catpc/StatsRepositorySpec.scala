package io.github.oybek.catpc

import cats.syntax.all._
import cats.instances.list._
import doobie.implicits._
import config.DatabaseConfig
import doobie.hikari.HikariTransactor
import io.github.oybek.catpc.db.DB
import io.github.oybek.catpc.db.repository.{OfferRepository, StatsRepository}
import io.github.oybek.catpc.db.sql.OfferSql
import io.github.oybek.catpc.model.Offer
import monix.eval.Task
import org.scalatest.{FlatSpec, Matchers}
import monix.execution.Scheduler.Implicits.global

class StatsRepositorySpec extends FlatSpec with Matchers {

  def initDb(name: String): Task[HikariTransactor[Task]] =
    for {
      tx <- DB.transactor[Task](
        DatabaseConfig(
          driver = "org.h2.Driver",
          url = s"jdbc:h2:mem:$name",
          user = "",
          password = ""))
      _ <- DB.initialize(tx)
    } yield tx

  "stats method" should "return correct statistics" in {
    val offers = List(
      Offer(1L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(1), None),
      Offer(2L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(2), None),
      Offer(3L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(1), None),
      Offer(4L, 1L, 1L, 1L, Some("B"), "foo", Some(1L), Some(2), None),
    )
    val test =
      for {
        tx <- initDb("test1")
        offerRepo = OfferRepository(tx)
        statsRepo = StatsRepository(tx)
        _ <- offers.traverse(offerRepo.insert)
        res <- statsRepo.stats()
      } yield res

    test.runSyncUnsafe() should be (4, 0, List("A" -> 3, "B" -> 1))
  }

  "stats by city" should "return correct city statistics" in {
    val offers = List(
      Offer(1L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(1), None),
      Offer(2L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(2), None),
      Offer(3L, 1L, 1L, 1L, Some("A"), "foo", Some(1L), Some(1), None),
      Offer(4L, 1L, 1L, 1L, Some("B"), "foo", Some(1L), Some(2), None),
    )
    val test =
      for {
        tx <- initDb("test2")
        offerRepo = OfferRepository(tx)
        statsRepo = StatsRepository(tx)
        _ <- offers.traverse(offerRepo.insert)
        res <- statsRepo.stats(Some(1))
      } yield res

    test.runSyncUnsafe() should be (2, 0, List("A" -> 2))
  }

}
