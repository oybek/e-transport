package io.github.oybek.catpc.db.repository

import cats.implicits._
import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0

trait UserRepositoryAlgebra[F[_]] {
  def upsert(user: (Long, Int)): F[Int]
  def selectById(id: Long): F[Option[(Long, Int)]]
  def cityOf(id: Long): F[Option[Int]]
}

case class UserRepository[F[_]: Monad](transactor: Transactor[F]) extends UserRepositoryAlgebra[F] {

  override def upsert(user: (Long, Int)): F[Int] =
    upsertSql(user).run.transact(transactor)

  override def selectById(id: Long): F[Option[(Long, Int)]] =
    selectByIdSql(id).option.transact(transactor)

  override def cityOf(id: Long): F[Option[Int]] =
    selectByIdSql(id).option.transact(transactor).map(_.map(_._2))

  private def selectByIdSql(id: Long): Query0[(Long, Int)] =
    sql"select id, city from user_info where id = $id".query[(Long, Int)]

  private def upsertSql(user: (Long, Int)): Update0 = sql"""
    insert into user_info (
      id,
      city
    ) values (
      ${user._1},
      ${user._2}
    ) on conflict (id) do
    update set city = ${user._2}
  """.update
}
