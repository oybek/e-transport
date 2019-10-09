package io.github.oybek.geekbear.vk.api

import java.net.URLEncoder

case class WallCommentReq(ownerId: Long,
                          postId: Long,
                          version: String,
                          message: String,
                          accessToken: String) {
  def toRequestStr: String = {
    Seq(
      "owner_id" -> ownerId,
      "access_token" -> accessToken,
      "v" -> version,
      "post_id" -> postId,
      "message" -> URLEncoder.encode(message, "UTF-8"),
    ).filter(_._2 != None).map {
      case (k, Some(v)) => k + "=" + v
      case (k, v) => k + "=" + v
    }.mkString("&")
  }
}

case class WallCommentRes()
