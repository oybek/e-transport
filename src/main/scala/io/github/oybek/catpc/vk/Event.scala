package io.github.oybek.catpc.vk

import io.circe.{Decoder, DecodingFailure, HCursor}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import io.circe.generic.extras.auto._
import org.http4s.DecodeFailure


sealed trait Event

// --
case class MessageNew(id: Long,
                      date: Long,
                      peerId: Long,
                      fromId: Long,
                      text: String,
                      geo: Option[Geo]) extends Event

case class Geo(coordinates: Coord, place: Option[Place])
case class Place(country: String, city: String, title: String)
case class Coord(latitude: Float, longitude: Float) {
  private val AVERAGE_RADIUS_OF_EARTH_KM = 6371

  def distKmTo(p: Coord): Int = distKm(this, p)

  private def distKm(p1: Coord, p2: Coord): Int = {
    val latDistance = Math.toRadians(p1.latitude - p2.latitude)
    val lngDistance = Math.toRadians(p1.longitude - p2.longitude)
    val sinLat = Math.sin(latDistance / 2)
    val sinLng = Math.sin(lngDistance / 2)
    val a = sinLat * sinLat +
      (Math.cos(Math.toRadians(p1.latitude)) *
        Math.cos(Math.toRadians(p2.latitude)) *
        sinLng * sinLng)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    (AVERAGE_RADIUS_OF_EARTH_KM * c).toInt
  }
}

// --
case class WallPostNew(id: Long,
                       fromId: Long,
                       ownerId: Long,
                       date: Long,
                       markedAsAds: Option[Long],
                       text: String,
                       signerId: Option[Long],
                       postType: Option[String],
                       geo: Option[Geo]) extends Event

case class WallReplyNew(id: Long,
                        fromId: Long,
                        text: String,
                        postId: Long,
                        date: Long) extends Event

object Event {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val decodeMessageNew: Decoder[MessageNew] = deriveConfiguredDecoder[MessageNew]

  implicit val decodeGeo: Decoder[Geo] =
    (c: HCursor) =>
      for {
        coordEither <- c.downField("coordinates").as[Either[String, Coord]]
        coord = coordEither match {
          case Left(s) =>
            val coords = s.split(' ')
            Coord(coords(0).toFloat, coords(1).toFloat)
          case Right(coord) => coord
        }
        place <- c.downField("place").as[Option[Place]]
        res = Geo(coord, place)
      } yield res

  implicit val decodeEvent: Decoder[Event] =
    (c: HCursor) =>
      for {
        typee <- c.downField("type").as[String]
        res <- typee match {
          case "message_new" => c.downField("object").as[MessageNew]
          case "wall_post_new" => c.downField("object").as[WallPostNew]
          case "wall_reply_new" => c.downField("object").as[WallReplyNew]
          case eventType => Left(DecodingFailure(s"Unknown event type: $eventType", List()))
        }
      } yield res

  implicit def decodeEither[A,B](implicit a: Decoder[A], b: Decoder[B]): Decoder[Either[A,B]] = {
    val l: Decoder[Either[A,B]]= a.map(Left.apply)
    val r: Decoder[Either[A,B]]= b.map(Right.apply)
    l or r
  }
}
