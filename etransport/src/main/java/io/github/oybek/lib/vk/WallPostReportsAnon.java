package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WallPostReportsAnon {

  private int count;

  private boolean userReposted;

  public WallPostReportsAnon() {}

  public WallPostReportsAnon(int count, boolean userReposted) {
    this.count = count;
    this.userReposted = userReposted;
  }

  public int getCount() {
    return this.count;
  }

  public WallPostReportsAnon setCount(int v) {
    this.count = v;
    return this;
  }

  public boolean getUserReposted() {
    return this.userReposted;
  }

  public WallPostReportsAnon setUserReposted(boolean v) {
    this.userReposted = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof WallPostReportsAnon)) return false;

    WallPostReportsAnon that = (WallPostReportsAnon) thatObj;

    return this.count == that.count && this.userReposted == that.userReposted;
  }

  @Override
  public String toString() {
    return "WallPostReportsAnon{"
        + "count="
        + this.count
        + ','
        + "userReposted="
        + this.userReposted
        + '}';
  }
}
