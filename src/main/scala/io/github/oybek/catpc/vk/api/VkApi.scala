package io.github.oybek.catpc.vk.api

trait VkApi[F[_]] {
  def getLongPollServer(getLongPollServerReq: GetLongPollServerReq): F[GetLongPollServerRes]
  def poll(pollReq: PollReq): F[PollRes]

  def sendMessage(sendMessageReq: SendMessageReq): F[SendMessageRes]
  def wallComment(wallCommentReq: WallCommentReq): F[WallCommentRes]
  def wallGet(wallGetReq: WallGetReq): F[WallGetRes]
}
