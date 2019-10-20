package io.github.oybek.geekbear.service

import cats.instances.list._
import cats.syntax.all._
import cats.effect._
import io.github.oybek.geekbear.db.repository.OfferRepositoryAlgebra
import io.github.oybek.geekbear.vk.Coord
import io.github.oybek.geekbear.vk.api.{VkApi, WallGetReq}

import scala.concurrent.duration._

case class Jaw[F[_]: Sync: Timer](offerRepositoryAlgebra: OfferRepositoryAlgebra[F],
                                  wallPostHandler: WallPostHandler,
                                  vkApi: VkApi[F],
                                  serviceKey: String) {

  val ekb = Coord(56.8519f, 60.6122f)

  def breakfast(groupIds: List[Long], adminIds: List[Long]): F[List[Either[Throwable, Int]]] =
    groupIds.flatTraverse { groupId =>
      val count = 100
      (0 to 500 by count).toList.flatTraverse { offset =>
        for {
          wallGetRes <- vkApi.wallGet(
            WallGetReq(
              ownerId = groupId,
              offset = offset,
              count = count,
              version = "5.101",
              accessToken = serviceKey))
          result <- wallGetRes.response.items.filter(_.markedAsAds.contains(0L)).traverse { wallPost =>
            val offer = wallPostHandler.wallPostToOffer(wallPost)
              .copy(latitude = ekb.latitude.some, longitude = ekb.longitude.some)
            offerRepositoryAlgebra.insert(offer).attempt
          }
          _ <- Timer[F].sleep(1 second)
        } yield result
      }
    }
}
