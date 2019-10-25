package io.github.oybek.geekbear.service

import config.Model
import io.github.oybek.geekbear.model.Offer
import io.github.oybek.geekbear.vk.{Coord, WallPostNew}

case class WallPostHandler(model: Model) {

  private val dict = model.dict.map {
    case (ttype, synonyms) =>
      ttype -> synonyms.flatMap { x =>
        if (x.startsWith("/"))
          model.dict.getOrElse(x.tail, List())
        else
          List(x)
      }
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

  def getRussianName(ttype: String): String =
    model.dict.get(ttype).flatMap(_.headOption).getOrElse("Нечто")

  def getTType(text: String): Option[String] =
    dict
      .map {
        case (ttype, synonyms) =>
          val score = synonyms.count(text.contains)
          ttype -> (score, score * 100 / synonyms.length)
      }
      .toList
      .filter {
        case (_, (score, _)) => score > 0
      }
      .sortWith {
        case ((_, (score1, cover1)), (_, (score2, cover2))) =>
          if (score1 == score2) cover1 > cover2 else score1 > score2
      }
      .headOption
      .map(_._1)
}
