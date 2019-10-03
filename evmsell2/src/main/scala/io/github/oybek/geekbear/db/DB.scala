package io.github.oybek.geekbear.db

import cats.effect.{Async, Sync}
import config.DatabaseConfig
import doobie.hikari.HikariTransactor
import org.flywaydb.core.Flyway

object DB {
  def transactor[F[_]: Sync: Async](config: DatabaseConfig): F[HikariTransactor[F]] = {
    HikariTransactor.newHikariTransactor[F](config.driver, config.url, config.user, config.password)
  }

  def initialize[F[_]: Sync](transactor: HikariTransactor[F]): F[Unit] = {
    transactor.configure { dataSource =>
      Sync[F].delay {
        val flyWay = Flyway.configure().dataSource(dataSource).load()
        flyWay.migrate()
        ()
      }
    }
  }
}
