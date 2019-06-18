package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventVideoCommentDelete implements Event {

  private int groupId;

  private int ownerId;

  private int id;

  private int userId;

  private int deleterId;

  private int videoId;

  public Events what() {
    return Events.EVENT_VIDEO_COMMENT_DELETE;
  }

  public String getDiscriminator() {
    return "video_comment_delete";
  }

  public EventVideoCommentDelete() {}

  public EventVideoCommentDelete(
      int groupId, int ownerId, int id, int userId, int deleterId, int videoId) {
    this.groupId = groupId;
    this.ownerId = ownerId;
    this.id = id;
    this.userId = userId;
    this.deleterId = deleterId;
    this.videoId = videoId;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public EventVideoCommentDelete setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public EventVideoCommentDelete setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public int getId() {
    return this.id;
  }

  public EventVideoCommentDelete setId(int v) {
    this.id = v;
    return this;
  }

  public int getUserId() {
    return this.userId;
  }

  public EventVideoCommentDelete setUserId(int v) {
    this.userId = v;
    return this;
  }

  public int getDeleterId() {
    return this.deleterId;
  }

  public EventVideoCommentDelete setDeleterId(int v) {
    this.deleterId = v;
    return this;
  }

  public int getVideoId() {
    return this.videoId;
  }

  public EventVideoCommentDelete setVideoId(int v) {
    this.videoId = v;
    return this;
  }

  public boolean isEventMessageNew() {
    return false;
  }

  public EventMessageNew asEventMessageNew() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventMessageReply() {
    return false;
  }

  public EventMessageReply asEventMessageReply() {
    throw new IllegalStateException("Not a $stName: " + this);
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
    return true;
  }

  public EventVideoCommentDelete asEventVideoCommentDelete() {
    return this;
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

    if (!(thatObj instanceof EventVideoCommentDelete)) return false;

    EventVideoCommentDelete that = (EventVideoCommentDelete) thatObj;

    return this.groupId == that.groupId
        && this.ownerId == that.ownerId
        && this.id == that.id
        && this.userId == that.userId
        && this.deleterId == that.deleterId
        && this.videoId == that.videoId;
  }

  @Override
  public String toString() {
    return "EventVideoCommentDelete{"
        + "groupId="
        + this.groupId
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "id="
        + this.id
        + ','
        + "userId="
        + this.userId
        + ','
        + "deleterId="
        + this.deleterId
        + ','
        + "videoId="
        + this.videoId
        + '}';
  }
}
