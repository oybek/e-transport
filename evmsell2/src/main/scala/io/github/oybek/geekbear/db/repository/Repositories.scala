package io.github.oybek.geekbear.db.repository

case class Repositories[F[_]](ofOffer: OfferRepositoryAlgebra[F],
                              ofStat: StatsRepositoryAlgebra[F],
                              ofCity: CityRepositoryAlgebra[F],
                              ofUser: UserRepositoryAlgebra[F])
