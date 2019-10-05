package io.github.oybek.geekbear.service

import cats.implicits._
import cats.effect._
import io.circe.generic.extras.Configuration
import io.github.oybek.geekbear.db.repository.OfferRepositoryAlgebra
import io.github.oybek.geekbear.vk.{Coord, WallPostNew}
import io.circe.parser.decode
import io.circe.generic.extras.auto._

import scala.io.Source

case class Importer[F[_]: Sync](offerRepositoryAlgebra: OfferRepositoryAlgebra[F], wallPostHandler: WallPostHandler) {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  case class Data(response: Response)
  case class Response(items: List[WallPostNew])

  val ekb = Coord(56.8519f, 60.6122f)

  def importAll(): F[Unit] = {
    List(
      "/home/oybek/garage/vk/lastwall.json",
    ).traverse(fname =>
      Resource.fromAutoCloseable(
        Sync[F].delay { Source.fromFile(fname) }
      ).use(source =>
        for {
          rawJson <- Sync[F].delay { source.getLines.mkString }
          posts = decode[Data](rawJson)
          _ <- Sync[F].delay { println(posts) }
          _ <- posts.right.get.response.items.traverse { wallPost =>
            val offer = wallPostHandler.wallPostToOffer(wallPost)
            for {
              dbOffer <- offerRepositoryAlgebra.selectById(offer.id)
              _ <- offerRepositoryAlgebra
                .insert(offer.copy(latitude = Some(ekb.latitude), longitude = Some(ekb.longitude)))
                .whenA(dbOffer.isEmpty)
            } yield ()
          }.void
        } yield ()
      )
    ).void
  }
}
