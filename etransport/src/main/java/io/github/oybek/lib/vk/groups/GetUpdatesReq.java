package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetUpdatesReq {

  private String act;

  private String key;

  private int ts;

  private int wait;

  public GetUpdatesReq() {}

  public GetUpdatesReq(String act, String key, int ts, int wait) {
    this.act = act;
    this.key = key;
    this.ts = ts;
    this.wait = wait;
  }

  public String getAct() {
    return this.act;
  }

  public GetUpdatesReq setAct(String v) {
    this.act = v;
    return this;
  }

  public String getKey() {
    return this.key;
  }

  public GetUpdatesReq setKey(String v) {
    this.key = v;
    return this;
  }

  public int getTs() {
    return this.ts;
  }

  public GetUpdatesReq setTs(int v) {
    this.ts = v;
    return this;
  }

  public int getWait() {
    return this.wait;
  }

  public GetUpdatesReq setWait(int v) {
    this.wait = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof GetUpdatesReq)) return false;

    GetUpdatesReq that = (GetUpdatesReq) thatObj;

    return this.act.equals(that.act)
        && this.key.equals(that.key)
        && this.ts == that.ts
        && this.wait == that.wait;
  }

  @Override
  public String toString() {
    return "GetUpdatesReq{"
        + "act="
        + '\''
        + this.act
        + '\''
        + ','
        + "key="
        + '\''
        + this.key
        + '\''
        + ','
        + "ts="
        + this.ts
        + ','
        + "wait="
        + this.wait
        + '}';
  }
}
