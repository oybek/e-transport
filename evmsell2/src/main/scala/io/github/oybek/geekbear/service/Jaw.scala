package io.github.oybek.geekbear.service

import cats.implicits._
import cats.effect._
import io.github.oybek.geekbear.db.repository.OfferRepositoryAlgebra
import io.github.oybek.geekbear.vk.Coord
import io.github.oybek.geekbear.vk.api.{VkApi, WallGetReq}

case class Jaw[F[_]: Sync](offerRepositoryAlgebra: OfferRepositoryAlgebra[F],
                           wallPostHandler: WallPostHandler,
                           vkApi: VkApi[F],
                           serviceKey: String) {

  val ekb = Coord(56.8519f, 60.6122f)

  def breakfast: F[Unit] =
    for {
      wallGetRes <- vkApi.wallGet(
        WallGetReq(
          ownerId = -134326485,
          offset = 0,
          count = 1,
          version = "5.101",
          accessToken = serviceKey))
      _ <- wallGetRes.response.items.traverse { wallPost =>
        Sync[F].delay { println(s"$wallPost") }
      }
    } yield ()
}
