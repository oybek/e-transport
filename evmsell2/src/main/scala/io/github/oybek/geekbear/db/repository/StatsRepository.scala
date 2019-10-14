package io.github.oybek.geekbear.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.geekbear.model.Offer

trait StatsRepositoryAlgebra[F[_]] {
  def stats: F[(Int, Int)]
}

case class StatsRepository[F[_]: Monad](transactor: Transactor[F]) extends StatsRepositoryAlgebra[F] {

  override def stats: F[(Int, Int)] = (
    for {
      total <- offerNum
      in24 <- offerNumLast24h
    } yield (total, in24)
  ).transact(transactor)

  private def offerNum =
    sql"select count(id) from offer"
      .query[Int]
      .unique

  private def offerNumLast24h =
    sql"select count(id) from offer where extract(epoch from now()) - date < 24*60*60"
      .query[Int]
      .unique
}
