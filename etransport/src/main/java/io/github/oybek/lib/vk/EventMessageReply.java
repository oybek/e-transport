package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventMessageReply implements Event {

  private int groupId;

  private Message object;

  public Events what() {
    return Events.EVENT_MESSAGE_REPLY;
  }

  public String getDiscriminator() {
    return "message_reply";
  }

  public EventMessageReply() {}

  public EventMessageReply(int groupId, Message object) {
    this.groupId = groupId;
    this.object = object;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public EventMessageReply setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public Message getObject() {
    return this.object;
  }

  public EventMessageReply setObject(Message v) {
    this.object = v;
    return this;
  }

  public boolean isEventMessageNew() {
    return false;
  }

  public EventMessageNew asEventMessageNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMessageReply() {
    return true;
  }

  public EventMessageReply asEventMessageReply() {
    return this;
  }

  public boolean isEventMessageEdit() {
    return false;
  }

  public EventMessageEdit asEventMessageEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMessageAllow() {
    return false;
  }

  public EventMessageAllow asEventMessageAllow() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMessageDeny() {
    return false;
  }

  public EventMessageDeny asEventMessageDeny() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPhotoNew() {
    return false;
  }

  public EventPhotoNew asEventPhotoNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPhotoCommentNew() {
    return false;
  }

  public EventPhotoCommentNew asEventPhotoCommentNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPhotoCommentEdit() {
    return false;
  }

  public EventPhotoCommentEdit asEventPhotoCommentEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPhotoCommentRestore() {
    return false;
  }

  public EventPhotoCommentRestore asEventPhotoCommentRestore() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPhotoCommentDelete() {
    return false;
  }

  public EventPhotoCommentDelete asEventPhotoCommentDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventAudioNew() {
    return false;
  }

  public EventAudioNew asEventAudioNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVideoNew() {
    return false;
  }

  public EventVideoNew asEventVideoNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVideoCommentNew() {
    return false;
  }

  public EventVideoCommentNew asEventVideoCommentNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVideoCommentEdit() {
    return false;
  }

  public EventVideoCommentEdit asEventVideoCommentEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVideoCommentRestore() {
    return false;
  }

  public EventVideoCommentRestore asEventVideoCommentRestore() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVideoCommentDelete() {
    return false;
  }

  public EventVideoCommentDelete asEventVideoCommentDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallPostNew() {
    return false;
  }

  public EventWallPostNew asEventWallPostNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallRepost() {
    return false;
  }

  public EventWallRepost asEventWallRepost() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallReplyNew() {
    return false;
  }

  public EventWallReplyNew asEventWallReplyNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallReplyEdit() {
    return false;
  }

  public EventWallReplyEdit asEventWallReplyEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallReplyRestore() {
    return false;
  }

  public EventWallReplyRestore asEventWallReplyRestore() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallReplyDelete() {
    return false;
  }

  public EventWallReplyDelete asEventWallReplyDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventBoardPostNew() {
    return false;
  }

  public EventBoardPostNew asEventBoardPostNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventBoardPostEdit() {
    return false;
  }

  public EventBoardPostEdit asEventBoardPostEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventBoardPostRestore() {
    return false;
  }

  public EventBoardPostRestore asEventBoardPostRestore() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventBoardPostDelete() {
    return false;
  }

  public EventBoardPostDelete asEventBoardPostDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMarketCommentNew() {
    return false;
  }

  public EventMarketCommentNew asEventMarketCommentNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMarketCommentEdit() {
    return false;
  }

  public EventMarketCommentEdit asEventMarketCommentEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMarketCommentRestore() {
    return false;
  }

  public EventMarketCommentRestore asEventMarketCommentRestore() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMarketCommentDelete() {
    return false;
  }

  public EventMarketCommentDelete asEventMarketCommentDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventGroupLeave() {
    return false;
  }

  public EventGroupLeave asEventGroupLeave() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventGroupJoin() {
    return false;
  }

  public EventGroupJoin asEventGroupJoin() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventUserBlock() {
    return false;
  }

  public EventUserBlock asEventUserBlock() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventUserUnblock() {
    return false;
  }

  public EventUserUnblock asEventUserUnblock() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventPollVoteNew() {
    return false;
  }

  public EventPollVoteNew asEventPollVoteNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventGroupOfficersEdit() {
    return false;
  }

  public EventGroupOfficersEdit asEventGroupOfficersEdit() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventGroupChangeSettings() {
    return false;
  }

  public EventGroupChangeSettings asEventGroupChangeSettings() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventGroupChangePhoto() {
    return false;
  }

  public EventGroupChangePhoto asEventGroupChangePhoto() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventVkPayTransaction() {
    return false;
  }

  public EventVkPayTransaction asEventVkPayTransaction() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof EventMessageReply)) return false;

    EventMessageReply that = (EventMessageReply) thatObj;

    return this.groupId == that.groupId && this.object.equals(that.object);
  }

  @Override
  public String toString() {
    return "EventMessageReply{" + "groupId=" + this.groupId + ',' + "object=" + this.object + '}';
  }
}
