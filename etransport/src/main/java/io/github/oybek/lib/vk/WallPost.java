package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WallPost {

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

  public WallPost() {}

  public WallPost(
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

  public int getId() {
    return this.id;
  }

  public WallPost setId(int v) {
    this.id = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public WallPost setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public int getFromId() {
    return this.fromId;
  }

  public WallPost setFromId(int v) {
    this.fromId = v;
    return this;
  }

  public int getCreatedBy() {
    return this.createdBy;
  }

  public WallPost setCreatedBy(int v) {
    this.createdBy = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public WallPost setDate(long v) {
    this.date = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public WallPost setText(String v) {
    this.text = v;
    return this;
  }

  public int getReplyOwnerId() {
    return this.replyOwnerId;
  }

  public WallPost setReplyOwnerId(int v) {
    this.replyOwnerId = v;
    return this;
  }

  public int getReplyPostId() {
    return this.replyPostId;
  }

  public WallPost setReplyPostId(int v) {
    this.replyPostId = v;
    return this;
  }

  public Optional<Integer> getFriendsOnly() {
    return Optional.ofNullable(friendsOnly);
  }

  public WallPost setFriendsOnly(Integer v) {
    this.friendsOnly = v;
    return this;
  }

  public WallPostCommentsAnon getComments() {
    return this.comments;
  }

  public WallPost setComments(WallPostCommentsAnon v) {
    this.comments = v;
    return this;
  }

  public WallPostLikesAnon getLikes() {
    return this.likes;
  }

  public WallPost setLikes(WallPostLikesAnon v) {
    this.likes = v;
    return this;
  }

  public WallPostReportsAnon getReposts() {
    return this.reposts;
  }

  public WallPost setReposts(WallPostReportsAnon v) {
    this.reposts = v;
    return this;
  }

  public WallPostViewsAnon getViews() {
    return this.views;
  }

  public WallPost setViews(WallPostViewsAnon v) {
    this.views = v;
    return this;
  }

  public PostType getPostType() {
    return this.postType;
  }

  public WallPost setPostType(PostType v) {
    this.postType = v;
    return this;
  }

  public PostSource getPostSource() {
    return this.postSource;
  }

  public WallPost setPostSource(PostSource v) {
    this.postSource = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public WallPost setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  public Geo getGeo() {
    return this.geo;
  }

  public WallPost setGeo(Geo v) {
    this.geo = v;
    return this;
  }

  public int getSignerId() {
    return this.signerId;
  }

  public WallPost setSignerId(int v) {
    this.signerId = v;
    return this;
  }

  public List<WallPost> getCopyHistory() {
    return this.copyHistory;
  }

  public WallPost setCopyHistory(List<WallPost> v) {
    this.copyHistory = v;
    return this;
  }

  public boolean getCanPin() {
    return this.canPin;
  }

  public WallPost setCanPin(boolean v) {
    this.canPin = v;
    return this;
  }

  public boolean getCanDelete() {
    return this.canDelete;
  }

  public WallPost setCanDelete(boolean v) {
    this.canDelete = v;
    return this;
  }

  public boolean getCanEdit() {
    return this.canEdit;
  }

  public WallPost setCanEdit(boolean v) {
    this.canEdit = v;
    return this;
  }

  public boolean getIsPinned() {
    return this.isPinned;
  }

  public WallPost setIsPinned(boolean v) {
    this.isPinned = v;
    return this;
  }

  public boolean getMarkedAsAds() {
    return this.markedAsAds;
  }

  public WallPost setMarkedAsAds(boolean v) {
    this.markedAsAds = v;
    return this;
  }

  public boolean getIsFavorite() {
    return this.isFavorite;
  }

  public WallPost setIsFavorite(boolean v) {
    this.isFavorite = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof WallPost)) return false;

    WallPost that = (WallPost) thatObj;

    return this.id == that.id
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
    return "WallPost{"
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
