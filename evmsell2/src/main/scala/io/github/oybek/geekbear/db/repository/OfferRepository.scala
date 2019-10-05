package io.github.oybek.geekbear.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.geekbear.model.Offer

trait OfferRepositoryAlgebra[F[_]] {
  def insert(offer: Offer): F[Int]
  def sold(id: Long, date: Long): F[Int]
  def selectByTType(ttype: String): F[List[Offer]]
  def selectById(id: Long): F[Option[Offer]]
}

case class OfferRepository[F[_]: Monad](transactor: Transactor[F]) extends OfferRepositoryAlgebra[F] {

  override def sold(id: Long, date: Long): F[Int] =
    sql"""
    update offer set sold = $date where id = $id
    """.update.run.transact(transactor)

  override def selectById(id: Long): F[Option[Offer]] =
    selectByIdSql(id).option.transact(transactor)

  override def insert(offer: Offer): F[Int] =
    insertSql(offer).run.transact(transactor)

  override def selectByTType(ttype: String): F[List[Offer]] =
    selectByTTypeSql(ttype).to[List].transact(transactor)

  private def selectByTTypeSql(ttype: String): Query0[Offer] = sql"""
    select
      id,
      group_id,
      from_id,
      date,
      ttype,
      text,
      price,
      latitude,
      longitude,
      sold
    from offer where ttype = $ttype
  """.query[Offer]

  private def selectByIdSql(id: Long): Query0[Offer] = sql"""
    select
      id,
      group_id,
      from_id,
      date,
      ttype,
      text,
      price,
      latitude,
      longitude,
      sold
    from offer where id = $id
  """.query[Offer]

  private def insertSql(offer: Offer): Update0 = sql"""
    insert into offer (
      id,
      group_id,
      from_id,
      date,
      ttype,
      text,
      price,
      latitude,
      longitude,
      sold
    ) values (
      ${offer.id},
      ${offer.groupId},
      ${offer.fromId},
      ${offer.date},
      ${offer.ttype},
      ${offer.text},
      ${offer.price},
      ${offer.latitude},
      ${offer.longitude},
      ${offer.sold}
    );
  """.update
}
