package io.github.oybek.catpc.vk.api

import java.net.URLEncoder

import io.circe.Encoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import io.circe.syntax._
import io.github.oybek.catpc.vk.Util._

case class SendMessageReq(peerId: Long,
                          message: String,
                          version: String,
                          accessToken: String,
                          attachment: Option[String] = None,
                          keyboard: Option[Keyboard] = None) extends Req {
  def toRequestStr: String = {
    Seq(
      "peer_id" -> peerId,
      "access_token" -> accessToken,
      "v" -> version,
      "message" -> URLEncoder.encode(message, "UTF-8"),
      "attachment" -> attachment,
      "keyboard" -> keyboard.map(x => URLEncoder.encode(x.asJson.removeNulls.noSpaces, "UTF-8"))
    ).filter(_._2 != None).map {
      case (k, Some(v)) => k + "=" + v
      case (k, v) => k + "=" + v
    }.mkString("&")
  }
}

case class Keyboard(oneTime: Boolean, buttons: List[List[Button]])
case class Button(action: Action, color: Option[String] = None)
case class Action(`type`: String, label: Option[String] = None, payload: Option[String] = None, hash: Option[String] = None)

object Keyboard {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val encodeKeyboard: Encoder[Keyboard] = deriveConfiguredEncoder
  implicit val encodeButton: Encoder[Button] = deriveConfiguredEncoder
  implicit val encodeAction: Encoder[Action] = deriveConfiguredEncoder
}

case class SendMessageRes(response: Option[Long])

