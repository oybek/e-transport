package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VkApps implements Action {

  private String payload;

  private int appId;

  private int ownerId;

  private String label;

  private String hash;

  public Actions what() {
    return Actions.VK_APPS;
  }

  public String getDiscriminator() {
    return "open_app";
  }

  public VkApps() {}

  public VkApps(String payload, int appId, int ownerId, String label, String hash) {
    this.payload = payload;
    this.appId = appId;
    this.ownerId = ownerId;
    this.label = label;
    this.hash = hash;
  }

  public String getPayload() {
    return this.payload;
  }

  public VkApps setPayload(String v) {
    this.payload = v;
    return this;
  }

  public int getAppId() {
    return this.appId;
  }

  public VkApps setAppId(int v) {
    this.appId = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public VkApps setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public String getLabel() {
    return this.label;
  }

  public VkApps setLabel(String v) {
    this.label = v;
    return this;
  }

  public String getHash() {
    return this.hash;
  }

  public VkApps setHash(String v) {
    this.hash = v;
    return this;
  }

  public boolean isText() {
    return false;
  }

  public Text asText() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isLocation() {
    return false;
  }

  public Location asLocation() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isVkPay() {
    return false;
  }

  public VkPay asVkPay() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isVkApps() {
    return true;
  }

  public VkApps asVkApps() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof VkApps)) return false;

    VkApps that = (VkApps) thatObj;

    return this.payload.equals(that.payload)
        && this.appId == that.appId
        && this.ownerId == that.ownerId
        && this.label.equals(that.label)
        && this.hash.equals(that.hash);
  }

  @Override
  public String toString() {
    return "VkApps{"
        + "payload="
        + '\''
        + this.payload
        + '\''
        + ','
        + "appId="
        + this.appId
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "label="
        + '\''
        + this.label
        + '\''
        + ','
        + "hash="
        + '\''
        + this.hash
        + '\''
        + '}';
  }
}
