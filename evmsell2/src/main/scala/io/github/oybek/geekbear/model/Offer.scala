package io.github.oybek.geekbear.model

import io.github.oybek.geekbear.vk.Coord

case class Offer(id: Long,
                 groupId: Long,
                 fromId: Long,
                 date: Long,
                 ttype: Option[String],
                 text: String,
                 price: Option[Long],
                 latitude: Option[Float] = None,
                 longitude: Option[Float] = None,
                 sold: Option[Long] = None) {
  lazy val coord: Option[Coord] =
    for {
      lat <- latitude
      lon <- longitude
    } yield Coord(lat, lon)
}
