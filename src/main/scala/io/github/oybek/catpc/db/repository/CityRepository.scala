package io.github.oybek.catpc.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import io.github.oybek.catpc.model.City

trait CityRepositoryAlgebra[F[_]] {
  def selectById(id: Int): F[Option[City]]
  def selectAll: F[List[City]]
}

case class CityRepository[F[_]: Monad](transactor: Transactor[F]) extends CityRepositoryAlgebra[F] {
  override def selectById(id: Int): F[Option[City]] =
    selectByIdSql(id).option.transact(transactor)

  private def selectByIdSql(id: Long): Query0[City] =
    sql"select id, name, latitude, longitude, pop from city where id = $id".query[City]

  override def selectAll: F[List[City]] =
    selectAllSql.to[List].transact(transactor)

  private def selectAllSql: Query0[City] =
    sql"select id, name, latitude, longitude, pop from city".query[City]
}
