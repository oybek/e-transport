package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
;

import java.util.List;
import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventWallPostNew implements Event {

  private int groupId;

  private int postponedId;

  private int id;

  private int ownerId;

  private int fromId;

  private int createdBy;

  private long date;

  private String text;

  private int replyOwnerId;

  private int replyPostId;

  private Integer friendsOnly;

  private WallPostCommentsAnon comments;

  private WallPostLikesAnon likes;

  private WallPostReportsAnon reposts;

  private WallPostViewsAnon views;

  private PostType postType;

  private PostSource postSource;

  private List<Attachment> attachments;

  private Geo geo;

  private int signerId;

  private List<WallPost> copyHistory;

  private boolean canPin;

  private boolean canDelete;

  private boolean canEdit;

  private boolean isPinned;

  private boolean markedAsAds;

  private boolean isFavorite;

  public Events what() {
    return Events.EVENT_WALL_POST_NEW;
  }

  public String getDiscriminator() {
    return "wall_post_new";
  }

  public EventWallPostNew() {}

  public EventWallPostNew(
      int groupId,
      int postponedId,
      int id,
      int ownerId,
      int fromId,
      int createdBy,
      long date,
      String text,
      int replyOwnerId,
      int replyPostId,
      Integer friendsOnly,
      WallPostCommentsAnon comments,
      WallPostLikesAnon likes,
      WallPostReportsAnon reposts,
      WallPostViewsAnon views,
      PostType postType,
      PostSource postSource,
      List<Attachment> attachments,
      Geo geo,
      int signerId,
      List<WallPost> copyHistory,
      boolean canPin,
      boolean canDelete,
      boolean canEdit,
      boolean isPinned,
      boolean markedAsAds,
      boolean isFavorite) {
    this.groupId = groupId;
    this.postponedId = postponedId;
    this.id = id;
    this.ownerId = ownerId;
    this.fromId = fromId;
    this.createdBy = createdBy;
    this.date = date;
    this.text = text;
    this.replyOwnerId = replyOwnerId;
    this.replyPostId = replyPostId;
    this.friendsOnly = friendsOnly;
    this.comments = comments;
    this.likes = likes;
    this.reposts = reposts;
    this.views = views;
    this.postType = postType;
    this.postSource = postSource;
    this.attachments = attachments;
    this.geo = geo;
    this.signerId = signerId;
    this.copyHistory = copyHistory;
    this.canPin = canPin;
    this.canDelete = canDelete;
    this.canEdit = canEdit;
    this.isPinned = isPinned;
    this.markedAsAds = markedAsAds;
    this.isFavorite = isFavorite;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public EventWallPostNew setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public int getPostponedId() {
    return this.postponedId;
  }

  public EventWallPostNew setPostponedId(int v) {
    this.postponedId = v;
    return this;
  }

  public int getId() {
    return this.id;
  }

  public EventWallPostNew setId(int v) {
    this.id = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public EventWallPostNew setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public int getFromId() {
    return this.fromId;
  }

  public EventWallPostNew setFromId(int v) {
    this.fromId = v;
    return this;
  }

  public int getCreatedBy() {
    return this.createdBy;
  }

  public EventWallPostNew setCreatedBy(int v) {
    this.createdBy = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public EventWallPostNew setDate(long v) {
    this.date = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public EventWallPostNew setText(String v) {
    this.text = v;
    return this;
  }

  public int getReplyOwnerId() {
    return this.replyOwnerId;
  }

  public EventWallPostNew setReplyOwnerId(int v) {
    this.replyOwnerId = v;
    return this;
  }

  public int getReplyPostId() {
    return this.replyPostId;
  }

  public EventWallPostNew setReplyPostId(int v) {
    this.replyPostId = v;
    return this;
  }

  public Optional<Integer> getFriendsOnly() {
    return Optional.ofNullable(friendsOnly);
  }

  public EventWallPostNew setFriendsOnly(Integer v) {
    this.friendsOnly = v;
    return this;
  }

  public WallPostCommentsAnon getComments() {
    return this.comments;
  }

  public EventWallPostNew setComments(WallPostCommentsAnon v) {
    this.comments = v;
    return this;
  }

  public WallPostLikesAnon getLikes() {
    return this.likes;
  }

  public EventWallPostNew setLikes(WallPostLikesAnon v) {
    this.likes = v;
    return this;
  }

  public WallPostReportsAnon getReposts() {
    return this.reposts;
  }

  public EventWallPostNew setReposts(WallPostReportsAnon v) {
    this.reposts = v;
    return this;
  }

  public WallPostViewsAnon getViews() {
    return this.views;
  }

  public EventWallPostNew setViews(WallPostViewsAnon v) {
    this.views = v;
    return this;
  }

  public PostType getPostType() {
    return this.postType;
  }

  public EventWallPostNew setPostType(PostType v) {
    this.postType = v;
    return this;
  }

  public PostSource getPostSource() {
    return this.postSource;
  }

  public EventWallPostNew setPostSource(PostSource v) {
    this.postSource = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public EventWallPostNew setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  public Geo getGeo() {
    return this.geo;
  }

  public EventWallPostNew setGeo(Geo v) {
    this.geo = v;
    return this;
  }

  public int getSignerId() {
    return this.signerId;
  }

  public EventWallPostNew setSignerId(int v) {
    this.signerId = v;
    return this;
  }

  public List<WallPost> getCopyHistory() {
    return this.copyHistory;
  }

  public EventWallPostNew setCopyHistory(List<WallPost> v) {
    this.copyHistory = v;
    return this;
  }

  public boolean getCanPin() {
    return this.canPin;
  }

  public EventWallPostNew setCanPin(boolean v) {
    this.canPin = v;
    return this;
  }

  public boolean getCanDelete() {
    return this.canDelete;
  }

  public EventWallPostNew setCanDelete(boolean v) {
    this.canDelete = v;
    return this;
  }

  public boolean getCanEdit() {
    return this.canEdit;
  }

  public EventWallPostNew setCanEdit(boolean v) {
    this.canEdit = v;
    return this;
  }

  public boolean getIsPinned() {
    return this.isPinned;
  }

  public EventWallPostNew setIsPinned(boolean v) {
    this.isPinned = v;
    return this;
  }

  public boolean getMarkedAsAds() {
    return this.markedAsAds;
  }

  public EventWallPostNew setMarkedAsAds(boolean v) {
    this.markedAsAds = v;
    return this;
  }

  public boolean getIsFavorite() {
    return this.isFavorite;
  }

  public EventWallPostNew setIsFavorite(boolean v) {
    this.isFavorite = v;
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
    return false;
  }

  public EventVideoCommentDelete asEventVideoCommentDelete() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isEventWallPostNew() {
    return true;
  }

  public EventWallPostNew asEventWallPostNew() {
    return this;
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

    if (!(thatObj instanceof EventWallPostNew)) return false;

    EventWallPostNew that = (EventWallPostNew) thatObj;

    return this.groupId == that.groupId
        && this.postponedId == that.postponedId
        && this.id == that.id
        && this.ownerId == that.ownerId
        && this.fromId == that.fromId
        && this.createdBy == that.createdBy
        && this.date == that.date
        && this.text.equals(that.text)
        && this.replyOwnerId == that.replyOwnerId
        && this.replyPostId == that.replyPostId
        && this.friendsOnly.equals(that.friendsOnly)
        && this.comments.equals(that.comments)
        && this.likes.equals(that.likes)
        && this.reposts.equals(that.reposts)
        && this.views.equals(that.views)
        && this.postType.equals(that.postType)
        && this.postSource.equals(that.postSource)
        && this.attachments.equals(that.attachments)
        && this.geo.equals(that.geo)
        && this.signerId == that.signerId
        && this.copyHistory.equals(that.copyHistory)
        && this.canPin == that.canPin
        && this.canDelete == that.canDelete
        && this.canEdit == that.canEdit
        && this.isPinned == that.isPinned
        && this.markedAsAds == that.markedAsAds
        && this.isFavorite == that.isFavorite;
  }

  @Override
  public String toString() {
    return "EventWallPostNew{"
        + "groupId="
        + this.groupId
        + ','
        + "postponedId="
        + this.postponedId
        + ','
        + "id="
        + this.id
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "fromId="
        + this.fromId
        + ','
        + "createdBy="
        + this.createdBy
        + ','
        + "date="
        + this.date
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "replyOwnerId="
        + this.replyOwnerId
        + ','
        + "replyPostId="
        + this.replyPostId
        + ','
        + "friendsOnly="
        + this.friendsOnly
        + ','
        + "comments="
        + this.comments
        + ','
        + "likes="
        + this.likes
        + ','
        + "reposts="
        + this.reposts
        + ','
        + "views="
        + this.views
        + ','
        + "postType="
        + this.postType
        + ','
        + "postSource="
        + this.postSource
        + ','
        + "attachments="
        + this.attachments
        + ','
        + "geo="
        + this.geo
        + ','
        + "signerId="
        + this.signerId
        + ','
        + "copyHistory="
        + this.copyHistory
        + ','
        + "canPin="
        + this.canPin
        + ','
        + "canDelete="
        + this.canDelete
        + ','
        + "canEdit="
        + this.canEdit
        + ','
        + "isPinned="
        + this.isPinned
        + ','
        + "markedAsAds="
        + this.markedAsAds
        + ','
        + "isFavorite="
        + this.isFavorite
        + '}';
  }
}
