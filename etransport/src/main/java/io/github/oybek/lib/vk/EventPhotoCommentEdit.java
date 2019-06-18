package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventPhotoCommentEdit implements Event {

  private int groupId;

  private int photoId;

  private int photoOwnerId;

  private int id;

  private int userId;

  private long date;

  private String text;

  private int replyToUser;

  private int replyToComment;

  private List<Attachment> attachments;

  private List<Integer> parentsStack;

  private CommentsThread thread;

  public Events what() {
    return Events.EVENT_PHOTO_COMMENT_EDIT;
  }

  public String getDiscriminator() {
    return "photo_comment_edit";
  }

  public EventPhotoCommentEdit() {}

  public EventPhotoCommentEdit(
      int groupId,
      int photoId,
      int photoOwnerId,
      int id,
      int userId,
      long date,
      String text,
      int replyToUser,
      int replyToComment,
      List<Attachment> attachments,
      List<Integer> parentsStack,
      CommentsThread thread) {
    this.groupId = groupId;
    this.photoId = photoId;
    this.photoOwnerId = photoOwnerId;
    this.id = id;
    this.userId = userId;
    this.date = date;
    this.text = text;
    this.replyToUser = replyToUser;
    this.replyToComment = replyToComment;
    this.attachments = attachments;
    this.parentsStack = parentsStack;
    this.thread = thread;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public EventPhotoCommentEdit setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public int getPhotoId() {
    return this.photoId;
  }

  public EventPhotoCommentEdit setPhotoId(int v) {
    this.photoId = v;
    return this;
  }

  public int getPhotoOwnerId() {
    return this.photoOwnerId;
  }

  public EventPhotoCommentEdit setPhotoOwnerId(int v) {
    this.photoOwnerId = v;
    return this;
  }

  public int getId() {
    return this.id;
  }

  public EventPhotoCommentEdit setId(int v) {
    this.id = v;
    return this;
  }

  public int getUserId() {
    return this.userId;
  }

  public EventPhotoCommentEdit setUserId(int v) {
    this.userId = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public EventPhotoCommentEdit setDate(long v) {
    this.date = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public EventPhotoCommentEdit setText(String v) {
    this.text = v;
    return this;
  }

  public int getReplyToUser() {
    return this.replyToUser;
  }

  public EventPhotoCommentEdit setReplyToUser(int v) {
    this.replyToUser = v;
    return this;
  }

  public int getReplyToComment() {
    return this.replyToComment;
  }

  public EventPhotoCommentEdit setReplyToComment(int v) {
    this.replyToComment = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public EventPhotoCommentEdit setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  public List<Integer> getParentsStack() {
    return this.parentsStack;
  }

  public EventPhotoCommentEdit setParentsStack(List<Integer> v) {
    this.parentsStack = v;
    return this;
  }

  public CommentsThread getThread() {
    return this.thread;
  }

  public EventPhotoCommentEdit setThread(CommentsThread v) {
    this.thread = v;
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
    return true;
  }

  public EventPhotoCommentEdit asEventPhotoCommentEdit() {
    return this;
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

    if (!(thatObj instanceof EventPhotoCommentEdit)) return false;

    EventPhotoCommentEdit that = (EventPhotoCommentEdit) thatObj;

    return this.groupId == that.groupId
        && this.photoId == that.photoId
        && this.photoOwnerId == that.photoOwnerId
        && this.id == that.id
        && this.userId == that.userId
        && this.date == that.date
        && this.text.equals(that.text)
        && this.replyToUser == that.replyToUser
        && this.replyToComment == that.replyToComment
        && this.attachments.equals(that.attachments)
        && this.parentsStack.equals(that.parentsStack)
        && this.thread.equals(that.thread);
  }

  @Override
  public String toString() {
    return "EventPhotoCommentEdit{"
        + "groupId="
        + this.groupId
        + ','
        + "photoId="
        + this.photoId
        + ','
        + "photoOwnerId="
        + this.photoOwnerId
        + ','
        + "id="
        + this.id
        + ','
        + "userId="
        + this.userId
        + ','
        + "date="
        + this.date
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "replyToUser="
        + this.replyToUser
        + ','
        + "replyToComment="
        + this.replyToComment
        + ','
        + "attachments="
        + this.attachments
        + ','
        + "parentsStack="
        + this.parentsStack
        + ','
        + "thread="
        + this.thread
        + '}';
  }
}
