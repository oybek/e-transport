package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WallPostViewsAnon {

  private int count;

  public WallPostViewsAnon() {}

  public WallPostViewsAnon(int count) {
    this.count = count;
  }

  public int getCount() {
    return this.count;
  }

  public WallPostViewsAnon setCount(int v) {
    this.count = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof WallPostViewsAnon)) return false;

    WallPostViewsAnon that = (WallPostViewsAnon) thatObj;

    return this.count == that.count;
  }

  @Override
  public String toString() {
    return "WallPostViewsAnon{" + "count=" + this.count + '}';
  }
}
