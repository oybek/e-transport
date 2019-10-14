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
      groupId = wallPost.ownerId,
      fromId = wallPost.signerId.getOrElse(0),
      date = wallPost.date,
      text = wallPost.text,
      price = getPrice(text),
      ttype = getTType(text),
      latitude = wallPost.geo.map(_.coordinates.latitude),
      longitude = wallPost.geo.map(_.coordinates.longitude)
    )
  }

  def getPrice(text: String): Option[Long] =
    "[0-9][0-9. ]+[ ]?(руб|р.)".r.findFirstIn(text)
      .orElse("цен[^0-9]*[0-9][0-9. ]+".r.findFirstIn(text))
      .orElse("[0-9][0-9. ]*(k|к)".r.findAllIn(text).toList.lastOption)
      .orElse("[0-9][0-9. ]*".r.findAllIn(text).toList.lastOption)
      .map(_
        .replaceFirst("(k|к)", "000")
        .filter(_.isDigit).toLong
      )

  def getTType(text: String): Option[String] = {
    val wordsPos = namesToTypes
      .map { case (name, ttype) => (text indexOf name, ttype) }
      .filter { case (i, _) => i != -1 }

    // try to detect thing by components
    model.thingsRelations.find {
      case (_, components) =>
        wordsPos.values.toSet subsetOf components.toSet
    }.map {
      case (thing, _) => thing
    }.orElse {
      wordsPos
        .foldLeft(Option.empty[(Int, String)]) {
          case (None, (i, v)) => Some(i -> v)
          case (Some(x), y) => Some(Seq(x, y).minBy(_._1))
        }
        .map(_._2)
    }
  }
}
