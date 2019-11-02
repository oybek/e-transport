package io.github.oybek.geekbear.service

import cats.implicits._
import cats.Monad
import cats.effect.Sync
import io.github.oybek.geekbear.db.repository.Repositories
import io.github.oybek.geekbear.model.City
import io.github.oybek.geekbear.vk.Coord

trait CityServiceAlg[F[_]] {

  def findByCoord(coord: Coord): F[City]
}

case class CityService[F[_]: Sync: Monad](repo: Repositories[F]) extends CityServiceAlg[F] {

  override def findByCoord(coord: Coord): F[City] =
    for {
      cities <- repo.ofCity.selectAll
      (dist, city) = cities.map {
        x => Coord(x.latitude, x.longitude).distKmTo(coord) -> x
      }.minBy(_._1)
    } yield city
}
