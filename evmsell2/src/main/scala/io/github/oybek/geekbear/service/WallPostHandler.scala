package io.github.oybek.geekbear.service

import config.Model
import io.github.oybek.geekbear.model.Offer
import io.github.oybek.geekbear.vk.{Coord, WallPostNew}

case class WallPostHandler(model: Model) {

  private val namesToTypes = model.thingsNames.flatMap {
    case (k, v) => v.map(_.toLowerCase -> k)
  }

  def wallPostToOffer(wallPost: WallPostNew): Offer = {
    val text = wallPost.text.toLowerCase
    Offer(
      id = wallPost.id,
      fromId = wallPost.signerId.getOrElse(0),
      date = wallPost.date,
      text = wallPost.text,
      price = getPrice(text),
      ttype = getTType(text),
      latitude = wallPost.geo.map(_.coordinates.latitude),
      longitude = wallPost.geo.map(_.coordinates.longitude)
    )
  }

  private def getPrice(text: String): Option[Long] =
    "[0-9]+".r.findFirstIn(text).map(_.toLong)

  def getTType(text: String): Option[String] =
    namesToTypes.find {
      case (name, _) => text.contains(name)
    }.map(_._2)
}
