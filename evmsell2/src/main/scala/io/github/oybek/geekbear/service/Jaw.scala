package io.github.oybek.geekbear.service

import cats.instances.option._
import cats.instances.list._
import cats.syntax.all._
import cats.effect._
import io.github.oybek.geekbear.db.repository.{OfferRepositoryAlgebra, UserRepositoryAlgebra}
import io.github.oybek.geekbear.vk.Coord
import io.github.oybek.geekbear.vk.api.{VkApi, WallGetReq}

import scala.concurrent.duration._

case class Jaw[F[_]: Sync: Timer](userRepositoryAlgebra: UserRepositoryAlgebra[F],
                                  offerRepositoryAlgebra: OfferRepositoryAlgebra[F],
                                  wallPostHandler: WallPostHandler,
                                  vkApi: VkApi[F],
                                  cityService: CityServiceAlg[F],
                                  serviceKey: String) {

  def breakfast(groupIds: List[Long], adminIds: List[Long], coord: Option[Coord]): F[List[Either[Throwable, Int]]] =
    groupIds.flatTraverse { groupId =>
      val count = 100
      (0 to 900 by count).toList.flatTraverse { offset =>
        for {
          wallGetRes <- vkApi.wallGet(
            WallGetReq(
              ownerId = groupId,
              offset = offset,
              count = count,
              version = "5.101",
              accessToken = serviceKey))
          result <- wallGetRes.response.items.filter(_.markedAsAds.contains(0L)).traverse { wallPost =>
            for {
              offer <- coord.traverse(cityService.findByCoord).map { city =>
                wallPostHandler.wallPostToOffer(wallPost)
                  .copy(city = city.map(_.id))
              }
              _ <- offer.city.traverse(cityId => userRepositoryAlgebra.upsert(offer.fromId -> cityId))
              res <- offerRepositoryAlgebra.insert(offer).attempt
            } yield res
          }
          _ <- Timer[F].sleep(1 second)
        } yield result
      }
    }
}
