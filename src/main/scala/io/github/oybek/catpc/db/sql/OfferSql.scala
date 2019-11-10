package io.github.oybek.catpc.db.sql

import doobie.implicits._
import doobie.util.update.Update0
import io.github.oybek.catpc.model.Offer

object OfferSql {

  def insert(offer: Offer): Update0 = sql"""
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
