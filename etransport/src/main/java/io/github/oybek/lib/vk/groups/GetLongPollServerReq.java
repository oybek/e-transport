package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetLongPollServerReq {

  private int groupId;

  private String accessToken;

  private String v;

  public GetLongPollServerReq() {}

  public GetLongPollServerReq(int groupId, String accessToken, String v) {
    this.groupId = groupId;
    this.accessToken = accessToken;
    this.v = v;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public GetLongPollServerReq setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public String getAccessToken() {
    return this.accessToken;
  }

  public GetLongPollServerReq setAccessToken(String v) {
    this.accessToken = v;
    return this;
  }

  public String getV() {
    return this.v;
  }

  public GetLongPollServerReq setV(String v) {
    this.v = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof GetLongPollServerReq)) return false;

    GetLongPollServerReq that = (GetLongPollServerReq) thatObj;

    return this.groupId == that.groupId
        && this.accessToken.equals(that.accessToken)
        && this.v.equals(that.v);
  }

  @Override
  public String toString() {
    return "GetLongPollServerReq{"
        + "groupId="
        + this.groupId
        + ','
        + "accessToken="
        + '\''
        + this.accessToken
        + '\''
        + ','
        + "v="
        + '\''
        + this.v
        + '\''
        + '}';
  }
}
