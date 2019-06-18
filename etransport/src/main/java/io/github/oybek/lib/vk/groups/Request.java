package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Request {

  private String accessToken;

  private String v;

  public Request() {}

  public Request(String accessToken, String v) {
    this.accessToken = accessToken;
    this.v = v;
  }

  public String getAccessToken() {
    return this.accessToken;
  }

  public Request setAccessToken(String v) {
    this.accessToken = v;
    return this;
  }

  public String getV() {
    return this.v;
  }

  public Request setV(String v) {
    this.v = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Request)) return false;

    Request that = (Request) thatObj;

    return this.accessToken.equals(that.accessToken) && this.v.equals(that.v);
  }

  @Override
  public String toString() {
    return "Request{"
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
