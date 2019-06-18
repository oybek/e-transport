package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetLongPollServerRes {

  private Response response;

  public GetLongPollServerRes() {}

  public GetLongPollServerRes(Response response) {
    this.response = response;
  }

  public Response getResponse() {
    return this.response;
  }

  public GetLongPollServerRes setResponse(Response v) {
    this.response = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof GetLongPollServerRes)) return false;

    GetLongPollServerRes that = (GetLongPollServerRes) thatObj;

    return this.response.equals(that.response);
  }

  @Override
  public String toString() {
    return "GetLongPollServerRes{" + "response=" + this.response + '}';
  }
}
