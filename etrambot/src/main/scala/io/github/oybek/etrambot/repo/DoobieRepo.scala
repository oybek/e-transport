package io.github.oybek.etrambot.repo

import cats.effect._
import doobie._
import doobie.implicits._
import io.github.oybek.etrambot.model.Stop
import fs2.Stream

class DoobieRepo[F[_]: Sync](xa: Transactor[F]) extends Repo[F] {

  override def findAll: Stream[F, Stop] =
    readAllStopsQ.stream.transact(xa).map{
      case (id, names, direction, lat, lon, typ) => Stop(id, names.split('|').toList, direction, lat, lon, typ)
    }

  def readAllStopsQ =
    sql"select * from stop".query[(String, String, String, Float, Float, String)]
}
