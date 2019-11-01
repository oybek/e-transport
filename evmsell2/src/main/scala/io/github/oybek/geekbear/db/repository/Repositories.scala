package io.github.oybek.geekbear.db.repository

case class Repositories[F[_]](ofOffer: OfferRepositoryAlgebra[F],
                              ofStat: StatsRepositoryAlgebra[F],
                              ofUser: UserRepositoryAlgebra[F])
