package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Response {

  private String key;

  private String server;

  private int ts;

  public Response() {}

  public Response(String key, String server, int ts) {
    this.key = key;
    this.server = server;
    this.ts = ts;
  }

  public String getKey() {
    return this.key;
  }

  public Response setKey(String v) {
    this.key = v;
    return this;
  }

  public String getServer() {
    return this.server;
  }

  public Response setServer(String v) {
    this.server = v;
    return this;
  }

  public int getTs() {
    return this.ts;
  }

  public Response setTs(int v) {
    this.ts = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Response)) return false;

    Response that = (Response) thatObj;

    return this.key.equals(that.key) && this.server.equals(that.server) && this.ts == that.ts;
  }

  @Override
  public String toString() {
    return "Response{"
        + "key="
        + '\''
        + this.key
        + '\''
        + ','
        + "server="
        + '\''
        + this.server
        + '\''
        + ','
        + "ts="
        + this.ts
        + '}';
  }
}
