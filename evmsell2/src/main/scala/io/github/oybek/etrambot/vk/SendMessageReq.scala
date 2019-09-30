package io.github.oybek.etrambot.vk

import java.net.URLEncoder

case class SendMessageReq(userId: Long,
                          message: String,
                          version: String,
                          accessToken: String,
                          attachment: Option[String] = None) {
  def toRequestStr: String = {
    Seq(
      "user_id" -> userId,
      "access_token" -> accessToken,
      "v" -> version,
      "message" -> URLEncoder.encode(message, "UTF-8"),
      "attachment" -> attachment
    ).filter(_._2 != None).map {
      case (k, Some(v)) => k + "=" + v
      case (k, v) => k + "=" + v
    }.mkString("&")
  }
}

case class SendMessageRes(response: Long)

