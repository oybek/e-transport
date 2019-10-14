import cats.effect.Sync
import cats.implicits._
import com.typesafe.config.ConfigFactory
import io.github.oybek.geekbear.vk.api.GetLongPollServerReq
import pureconfig.error.ConfigReaderException

package object config {
  case class Model(thingsNames: Map[String, List[String]], thingsRelations: Map[String, List[String]]) {
    def isGood: Option[String] =
      List(
        if (thingsNames.values.forall(_.nonEmpty)) None else Some("things-names values must be non-empty arrays"),
        if (thingsRelations.keys.forall(thingsNames.contains)) None else Some("things-relations keys must be subset of things-names keys"),
        if (thingsRelations.values.forall(_.forall(thingsNames.contains))) None else Some("things-relations values must be subset of things-names keys")
      ).flatten.headOption
  }

  case class DatabaseConfig(driver: String, url: String, user: String, password: String)

  case class Config(getLongPollServerReq: GetLongPollServerReq, database: DatabaseConfig, model: Model, serviceKey: String)

  object Config {
    import pureconfig._
    import pureconfig.generic.auto._

    def load[F[_]: Sync](configFile: String = "application.conf"): F[Config] = {
      Sync[F].delay {
        loadConfig[Config](ConfigFactory.load(configFile))
      }.flatMap {
        case Left(e) => Sync[F].raiseError[Config](new ConfigReaderException[Config](e))
        case Right(config) =>
          config.model
            .isGood.map(x => Sync[F].raiseError[Config](new Throwable(x)))
            .getOrElse(Sync[F].delay(config))
      }
    }
  }
}
