package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WallPostLikesAnon {

  private int count;

  private boolean userLikes;

  private boolean canLike;

  private boolean canPublish;

  public WallPostLikesAnon() {}

  public WallPostLikesAnon(int count, boolean userLikes, boolean canLike, boolean canPublish) {
    this.count = count;
    this.userLikes = userLikes;
    this.canLike = canLike;
    this.canPublish = canPublish;
  }

  public int getCount() {
    return this.count;
  }

  public WallPostLikesAnon setCount(int v) {
    this.count = v;
    return this;
  }

  public boolean getUserLikes() {
    return this.userLikes;
  }

  public WallPostLikesAnon setUserLikes(boolean v) {
    this.userLikes = v;
    return this;
  }

  public boolean getCanLike() {
    return this.canLike;
  }

  public WallPostLikesAnon setCanLike(boolean v) {
    this.canLike = v;
    return this;
  }

  public boolean getCanPublish() {
    return this.canPublish;
  }

  public WallPostLikesAnon setCanPublish(boolean v) {
    this.canPublish = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof WallPostLikesAnon)) return false;

    WallPostLikesAnon that = (WallPostLikesAnon) thatObj;

    return this.count == that.count
        && this.userLikes == that.userLikes
        && this.canLike == that.canLike
        && this.canPublish == that.canPublish;
  }

  @Override
  public String toString() {
    return "WallPostLikesAnon{"
        + "count="
        + this.count
        + ','
        + "userLikes="
        + this.userLikes
        + ','
        + "canLike="
        + this.canLike
        + ','
        + "canPublish="
        + this.canPublish
        + '}';
  }
}
