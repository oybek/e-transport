package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WallPostCommentsAnon {

  private int count;

  private boolean canPost;

  private boolean groupsCanPost;

  private boolean canClose;

  private boolean canOpen;

  public WallPostCommentsAnon() {}

  public WallPostCommentsAnon(
      int count, boolean canPost, boolean groupsCanPost, boolean canClose, boolean canOpen) {
    this.count = count;
    this.canPost = canPost;
    this.groupsCanPost = groupsCanPost;
    this.canClose = canClose;
    this.canOpen = canOpen;
  }

  public int getCount() {
    return this.count;
  }

  public WallPostCommentsAnon setCount(int v) {
    this.count = v;
    return this;
  }

  public boolean getCanPost() {
    return this.canPost;
  }

  public WallPostCommentsAnon setCanPost(boolean v) {
    this.canPost = v;
    return this;
  }

  public boolean getGroupsCanPost() {
    return this.groupsCanPost;
  }

  public WallPostCommentsAnon setGroupsCanPost(boolean v) {
    this.groupsCanPost = v;
    return this;
  }

  public boolean getCanClose() {
    return this.canClose;
  }

  public WallPostCommentsAnon setCanClose(boolean v) {
    this.canClose = v;
    return this;
  }

  public boolean getCanOpen() {
    return this.canOpen;
  }

  public WallPostCommentsAnon setCanOpen(boolean v) {
    this.canOpen = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof WallPostCommentsAnon)) return false;

    WallPostCommentsAnon that = (WallPostCommentsAnon) thatObj;

    return this.count == that.count
        && this.canPost == that.canPost
        && this.groupsCanPost == that.groupsCanPost
        && this.canClose == that.canClose
        && this.canOpen == that.canOpen;
  }

  @Override
  public String toString() {
    return "WallPostCommentsAnon{"
        + "count="
        + this.count
        + ','
        + "canPost="
        + this.canPost
        + ','
        + "groupsCanPost="
        + this.groupsCanPost
        + ','
        + "canClose="
        + this.canClose
        + ','
        + "canOpen="
        + this.canOpen
        + '}';
  }
}
