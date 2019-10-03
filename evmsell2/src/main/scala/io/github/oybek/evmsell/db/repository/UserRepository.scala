package io.github.oybek.evmsell.db.repository

import cats.implicits._
import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.evmsell.vk.Coord

trait UserRepositoryAlgebra[F[_]] {
  def upsert(user: (Long, Coord)): F[Int]
  def selectById(id: Long): F[Option[(Long, Coord)]]
}

case class UserRepository[F[_]: Monad](transactor: Transactor[F]) extends UserRepositoryAlgebra[F] {

  override def upsert(user: (Long, Coord)): F[Int] =
    upsertSql(user).run.transact(transactor)

  override def selectById(id: Long): F[Option[(Long, Coord)]] =
    selectByIdSql(id).option.transact(transactor).map { xs =>
      xs.map {
        case (id, latitude, longitude) => id -> Coord(latitude, longitude)
      }
    }

  private def selectByIdSql(id: Long): Query0[(Long, Float, Float)] = sql"""
    select
      id,
      latitude,
      longitude
    from user_info where id = $id
  """.query[(Long, Float, Float)]

  private def upsertSql(user: (Long, Coord)): Update0 = sql"""
    insert into user_info (
      id,
      latitude,
      longitude
    ) values (
      ${user._1},
      ${user._2.latitude},
      ${user._2.longitude}
    ) on conflict (id) do
    update set latitude = ${user._2.latitude}, longitude = ${user._2.longitude}
  """.update
}
