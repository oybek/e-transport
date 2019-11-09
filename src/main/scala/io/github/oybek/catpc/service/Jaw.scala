package io.github.oybek.catpc.service

import cats.instances.option._
import cats.instances.list._
import cats.syntax.all._
import cats.effect._
import io.github.oybek.catpc.db.repository.{OfferRepositoryAlgebra, Repositories, UserRepositoryAlgebra}
import io.github.oybek.catpc.vk.Coord
import io.github.oybek.catpc.vk.api.{VkApi, WallGetReq}

import scala.concurrent.duration._

case class Jaw[F[_]: Sync: Timer](repository: Repositories[F],
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
              _ <- offer.city.traverse(cityId => repository.ofUser.upsert(offer.fromId -> cityId))
              res <- repository.ofOffer.insert(offer).attempt
            } yield res
          }
          _ <- Timer[F].sleep(1 second)
        } yield result
      }
    }
}
