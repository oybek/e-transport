package io.github.oybek.etrambot.vk

case class SendMessageReq(userId: Long,
                          message: String,
                          version: String,
                          accessToken: String,
                          attachment: String) {
  def toRequestStr: String = {
    Seq(
      "user_id" -> userId,
      "access_token" -> accessToken,
      "v" -> version,
      "message" -> message,
      "attachment" -> attachment
    ).map {
      case (k, v) => k + "=" + v
    }.mkString("&")
  }
}

case class SendMessageRes(response: Long)

