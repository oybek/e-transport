import cats.effect.Sync
import cats.implicits._
import com.typesafe.config.ConfigFactory
import io.github.oybek.etrambot.vk.GetLongPollServerReq
import pureconfig.error.ConfigReaderException

package object config {
  case class Model(thingsNames: Map[String, List[String]]) {
    def isGood: Boolean =
      thingsNames.values.forall(_.nonEmpty)
  }

  case class DatabaseConfig(driver: String, url: String, user: String, password: String)

  case class Config(getLongPollServerReq: GetLongPollServerReq, database: DatabaseConfig, model: Model)

  object Config {
    import pureconfig._
    import pureconfig.generic.auto._

    def load[F[_]: Sync](configFile: String = "application.conf"): F[Config] = {
      Sync[F].delay {
        loadConfig[Config](ConfigFactory.load(configFile))
      }.flatMap {
        case Left(e) => Sync[F].raiseError[Config](new ConfigReaderException[Config](e))
        case Right(config) =>
          if (!config.model.isGood) Sync[F].raiseError[Config](new Throwable("things-names must contains nonempty lists"))
          else Sync[F].delay(config)
      }
    }
  }
}
