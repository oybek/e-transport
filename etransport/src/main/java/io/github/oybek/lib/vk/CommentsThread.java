package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentsThread {

  private int count;

  private List<Comment> items;

  private boolean canPost;

  private boolean showReplyButton;

  private boolean groupsCanPost;

  public CommentsThread() {}

  public CommentsThread(
      int count,
      List<Comment> items,
      boolean canPost,
      boolean showReplyButton,
      boolean groupsCanPost) {
    this.count = count;
    this.items = items;
    this.canPost = canPost;
    this.showReplyButton = showReplyButton;
    this.groupsCanPost = groupsCanPost;
  }

  public int getCount() {
    return this.count;
  }

  public CommentsThread setCount(int v) {
    this.count = v;
    return this;
  }

  public List<Comment> getItems() {
    return this.items;
  }

  public CommentsThread setItems(List<Comment> v) {
    this.items = v;
    return this;
  }

  public boolean getCanPost() {
    return this.canPost;
  }

  public CommentsThread setCanPost(boolean v) {
    this.canPost = v;
    return this;
  }

  public boolean getShowReplyButton() {
    return this.showReplyButton;
  }

  public CommentsThread setShowReplyButton(boolean v) {
    this.showReplyButton = v;
    return this;
  }

  public boolean getGroupsCanPost() {
    return this.groupsCanPost;
  }

  public CommentsThread setGroupsCanPost(boolean v) {
    this.groupsCanPost = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof CommentsThread)) return false;

    CommentsThread that = (CommentsThread) thatObj;

    return this.count == that.count
        && this.items.equals(that.items)
        && this.canPost == that.canPost
        && this.showReplyButton == that.showReplyButton
        && this.groupsCanPost == that.groupsCanPost;
  }

  @Override
  public String toString() {
    return "CommentsThread{"
        + "count="
        + this.count
        + ','
        + "items="
        + this.items
        + ','
        + "canPost="
        + this.canPost
        + ','
        + "showReplyButton="
        + this.showReplyButton
        + ','
        + "groupsCanPost="
        + this.groupsCanPost
        + '}';
  }
}
