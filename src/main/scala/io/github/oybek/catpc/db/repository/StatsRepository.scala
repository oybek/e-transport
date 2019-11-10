package io.github.oybek.catpc.db.repository

import cats.Monad
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.transactor.Transactor
import doobie.Fragments._

trait StatsRepositoryAlgebra[F[_]] {
  def stats(cityId: Option[Int] = None): F[(Int, Int, List[(String, Int)])]
}

case class StatsRepository[F[_] : Monad](transactor: Transactor[F]) extends StatsRepositoryAlgebra[F] {

  override def stats(cityId: Option[Int] = None): F[(Int, Int, List[(String, Int)])] = (
    for {
      total <- offerNum(cityId)
      in24 <- offerNumLast24h(cityId)
      countByType <- countByTType(cityId)
    } yield (total, in24, countByType)
    ).transact(transactor)

  private def offerNum(cityId: Option[Int] = None): ConnectionIO[Int] =
    (fr"select count(id) from offer" ++ whereAndOpt(cityId.map(x => fr"city = $x")))
      .query[Int]
      .unique

  private def countByTType(cityId: Option[Int] = None): ConnectionIO[List[(String, Int)]] =
    (fr"select ttype, count(ttype) from offer" ++
      whereAndOpt(
        Some(fr"sold is null"),
        Some(fr"ttype is not null"),
        cityId.map(x => fr"city = $x")) ++ fr"group by ttype")
      .query[(String, Int)]
      .to[List]

  private def offerNumLast24h(cityId: Option[Int] = None): ConnectionIO[Int] =
    (fr"select count(id) from offer" ++
      whereAndOpt(
        Some(fr"extract(epoch from now()) - date < 24*60*60"),
        cityId.map(x => fr"city = $x")))
      .query[Int]
      .unique
}
