package io.github.oybek.evmsell.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.evmsell.model.Offer

trait OfferRepositoryAlgebra[F[_]] {
  def insert(offer: Offer): F[Int]
  def selectByTType(ttype: String): F[List[Offer]]
}

case class OfferRepository[F[_]: Monad](transactor: Transactor[F]) extends OfferRepositoryAlgebra[F] {

  override def insert(offer: Offer): F[Int] =
    insertSql(offer).run.transact(transactor)

  override def selectByTType(ttype: String): F[List[Offer]] =
    selectByTTypeSql(ttype).to[List].transact(transactor)

  private def selectByTTypeSql(ttype: String): Query0[Offer] = sql"""
    select
      id,
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

  private def insertSql(offer: Offer): Update0 = sql"""
    insert into offer (
      id,
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
