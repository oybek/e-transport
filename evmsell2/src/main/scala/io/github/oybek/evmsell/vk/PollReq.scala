package io.github.oybek.evmsell.vk

case class PollReq(server: String,
                   act: String = "a_check",
                   key: String,
                   ts: Long,
                   waitt: Long) {
  def toRequestStr: String = {
    server + "?" +
    Seq(
      "act" -> act,
      "key" -> key,
      "ts" -> ts,
      "wait" -> waitt
    ).map {
      case (k, v) => k + "=" + v
    }.mkString("&")
  }
}

case class PollRes(ts: Long, updates: List[Event])
