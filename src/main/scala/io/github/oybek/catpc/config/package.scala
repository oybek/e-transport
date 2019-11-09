import java.io.File

import cats.effect.Sync
import cats.implicits._
import com.typesafe.config.ConfigFactory
import io.github.oybek.catpc.vk.api.GetLongPollServerReq
import pureconfig.error.ConfigReaderException

package object config {
  case class Model(dict: Map[String, List[String]]) {
    def findError: Option[String]= {
      Seq(
        dict.values.exists(_.isEmpty) -> "dict values must be nonempty lists",
        dict.values.exists { synonyms =>
          synonyms.exists(x => x.startsWith("/") && !dict.keySet.contains(x.tail))
        } -> "synonyms starting with '/' must be in keys"
      ).find(_._1).map(_._2)
    }
  }

  case class DatabaseConfig(driver: String, url: String, user: String, password: String)

  case class Config(getLongPollServerReq: GetLongPollServerReq, database: DatabaseConfig, model: Model, serviceKey: String)

  object Config {
    import pureconfig.generic.auto._
    import pureconfig._

    def load[F[_]: Sync](configFileName: Option[String]): F[Config] = {
      Sync[F].delay {
        configFileName
          .map(x => loadConfig[Config](ConfigFactory.parseFile(new File(x))))
          .getOrElse(loadConfig[Config](ConfigFactory.load("application.conf")))
      }.flatMap {
        case Left(e) => Sync[F].raiseError[Config](new ConfigReaderException[Config](e))
        case Right(config) =>
          config.model.findError
            .map(x => Sync[F].raiseError[Config](new Throwable(x)))
            .getOrElse(Sync[F].delay(config))
      }
    }
  }
}
