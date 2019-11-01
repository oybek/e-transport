package io.github.oybek.geekbear.service

import cats.effect.Sync
import io.github.oybek.geekbear.model.City
import io.github.oybek.geekbear.vk.Coord

trait CityServiceAlg[F[_]] {

  def findByCoord(coord: Coord): F[City]
}

case class CityService[F[_]: Sync]() extends CityServiceAlg[F] {

  override def findByCoord(coord: Coord): F[City] =
    Sync[F].pure(City(829, "Екатеринбург", 0f, 0f, 0))
}
