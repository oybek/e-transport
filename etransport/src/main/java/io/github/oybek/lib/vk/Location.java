package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Location implements Action {

  private String payload;

  public Actions what() {
    return Actions.LOCATION;
  }

  public String getDiscriminator() {
    return "location";
  }

  public Location() {}

  public Location(String payload) {
    this.payload = payload;
  }

  public String getPayload() {
    return this.payload;
  }

  public Location setPayload(String v) {
    this.payload = v;
    return this;
  }

  public boolean isText() {
    return false;
  }

  public Text asText() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isLocation() {
    return true;
  }

  public Location asLocation() {
    return this;
  }

  public boolean isVkPay() {
    return false;
  }

  public VkPay asVkPay() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isVkApps() {
    return false;
  }

  public VkApps asVkApps() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Location)) return false;

    Location that = (Location) thatObj;

    return this.payload.equals(that.payload);
  }

  @Override
  public String toString() {
    return "Location{" + "payload=" + '\'' + this.payload + '\'' + '}';
  }
}
