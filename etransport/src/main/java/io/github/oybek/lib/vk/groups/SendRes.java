package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SendRes {

  private int id;

  public SendRes() {}

  public SendRes(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public SendRes setId(int v) {
    this.id = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof SendRes)) return false;

    SendRes that = (SendRes) thatObj;

    return this.id == that.id;
  }

  @Override
  public String toString() {
    return "SendRes{" + "id=" + this.id + '}';
  }
}
