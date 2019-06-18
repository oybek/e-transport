package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.oybek.lib.vk.Event;

import java.util.List;
import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetUpdatesRes {

  private int ts;

  private List<Event> updates;

  private Integer failed;

  public GetUpdatesRes() {}

  public GetUpdatesRes(int ts, List<Event> updates, Integer failed) {
    this.ts = ts;
    this.updates = updates;
    this.failed = failed;
  }

  public int getTs() {
    return this.ts;
  }

  public GetUpdatesRes setTs(int v) {
    this.ts = v;
    return this;
  }

  public List<Event> getUpdates() {
    return this.updates;
  }

  public GetUpdatesRes setUpdates(List<Event> v) {
    this.updates = v;
    return this;
  }

  public Optional<Integer> getFailed() {
    return Optional.ofNullable(failed);
  }

  public GetUpdatesRes setFailed(Integer v) {
    this.failed = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof GetUpdatesRes)) return false;

    GetUpdatesRes that = (GetUpdatesRes) thatObj;

    return this.ts == that.ts
        && this.updates.equals(that.updates)
        && this.failed.equals(that.failed);
  }

  @Override
  public String toString() {
    return "GetUpdatesRes{"
        + "ts="
        + this.ts
        + ','
        + "updates="
        + this.updates
        + ','
        + "failed="
        + this.failed
        + '}';
  }
}
