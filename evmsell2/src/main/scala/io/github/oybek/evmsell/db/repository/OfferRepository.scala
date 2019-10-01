package io.github.oybek.evmsell.db.repository

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import io.github.oybek.evmsell.model.Offer

trait OfferRepositoryAlgebra[F[_]] {
  def insert(offer: Offer): F[Int]
}

case class OfferRepository[F[_]: Monad](transactor: Transactor[F]) extends OfferRepositoryAlgebra[F] {

  override def insert(offer: Offer): F[Int] =
    insertSql(offer).run.transact(transactor)

  private def insertSql(offer: Offer): Update0 = sql"""
    insert into offer (
      id,
      from_id,
      date,
      ttype,
      text,
      price,
      latitude,
      longitude
    ) values (
      ${offer.id},
      ${offer.fromId},
      ${offer.date},
      ${offer.ttype},
      ${offer.text},
      ${offer.price},
      ${offer.latitude},
      ${offer.longitude}
    );
  """.update
}
