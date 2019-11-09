package io.github.oybek.catpc.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.catpc.model.Offer

trait OfferRepositoryAlgebra[F[_]] {
  def insert(offer: Offer): F[Int]
  def sold(id: Long, date: Long): F[Int]
  def selectByTTypeAndCity(ttype: String, cityId: Int): F[List[Offer]]
  def selectById(id: Long): F[Option[Offer]]
  def changeTType(id: Long, groupId: Long, ttype: String): F[Int]
}

case class OfferRepository[F[_]: Monad](transactor: Transactor[F]) extends OfferRepositoryAlgebra[F] {

  override def changeTType(id: Long, groupId: Long, ttype: String): F[Int] =
    sql"""
    update offer set ttype = $ttype where id = $id and group_id = $groupId
    """.update.run.transact(transactor)

  override def sold(id: Long, date: Long): F[Int] =
    sql"""
    update offer set sold = $date where id = $id
    """.update.run.transact(transactor)

  override def selectById(id: Long): F[Option[Offer]] =
    selectByIdSql(id).option.transact(transactor)

  override def insert(offer: Offer): F[Int] =
    insertSql(offer).run.transact(transactor)

  override def selectByTTypeAndCity(ttype: String, cityId: Int): F[List[Offer]] =
    selectByTTypeSql(ttype, cityId).to[List].transact(transactor)

  private def selectByTTypeSql(ttype: String, cityId: Int): Query0[Offer] = sql"""
    select
      id,
      group_id,
      from_id,
      date,
      ttype,
      text,
      price,
      city,
      sold
    from offer where ttype = $ttype and city = $cityId
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
      city,
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
      city,
      sold
    ) values (
      ${offer.id},
      ${offer.groupId},
      ${offer.fromId},
      ${offer.date},
      ${offer.ttype},
      ${offer.text},
      ${offer.price},
      ${offer.city},
      ${offer.sold}
    );
  """.update
}
