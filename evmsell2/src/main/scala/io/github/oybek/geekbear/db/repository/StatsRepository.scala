package io.github.oybek.geekbear.db.repository

import cats.Monad
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.geekbear.model.Offer

trait StatsRepositoryAlgebra[F[_]] {
  def stats: F[(Int, Int, List[(String, Int)])]
}

case class StatsRepository[F[_]: Monad](transactor: Transactor[F]) extends StatsRepositoryAlgebra[F] {

  override def stats: F[(Int, Int, List[(String, Int)])] = (
    for {
      total <- offerNum
      in24 <- offerNumLast24h
      countByType <- countByTType
    } yield (total, in24, countByType)
  ).transact(transactor)

  private def offerNum =
    sql"select count(id) from offer"
      .query[Int]
      .unique

  private def countByTType: ConnectionIO[List[(String, Int)]] =
    sql"select ttype, count(ttype) from offer where sold is null and ttype is not null group by ttype"
      .query[(String, Int)]
      .to[List]

  private def offerNumLast24h =
    sql"select count(id) from offer where extract(epoch from now()) - date < 24*60*60"
      .query[Int]
      .unique
}
