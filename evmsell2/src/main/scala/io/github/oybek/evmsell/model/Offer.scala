package io.github.oybek.evmsell.model

import io.github.oybek.evmsell.vk.Coord

case class Offer(id: Long,
                 fromId: Long,
                 date: Long,
                 ttype: Option[String],
                 text: String,
                 price: Option[Long],
                 latitude: Option[Float] = None,
                 longitude: Option[Float] = None,
                 sold: Boolean = false) {
  lazy val coord: Option[Coord] =
    for {
      lat <- latitude
      lon <- longitude
    } yield Coord(lat, lon)
}
