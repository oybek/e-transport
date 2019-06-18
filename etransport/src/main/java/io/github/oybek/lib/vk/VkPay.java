package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VkPay implements Action {

  private String payload;

  private String hash;

  public Actions what() {
    return Actions.VK_PAY;
  }

  public String getDiscriminator() {
    return "vkpay";
  }

  public VkPay() {}

  public VkPay(String payload, String hash) {
    this.payload = payload;
    this.hash = hash;
  }

  public String getPayload() {
    return this.payload;
  }

  public VkPay setPayload(String v) {
    this.payload = v;
    return this;
  }

  public String getHash() {
    return this.hash;
  }

  public VkPay setHash(String v) {
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
    return true;
  }

  public VkPay asVkPay() {
    return this;
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

    if (!(thatObj instanceof VkPay)) return false;

    VkPay that = (VkPay) thatObj;

    return this.payload.equals(that.payload) && this.hash.equals(that.hash);
  }

  @Override
  public String toString() {
    return "VkPay{"
        + "payload="
        + '\''
        + this.payload
        + '\''
        + ','
        + "hash="
        + '\''
        + this.hash
        + '\''
        + '}';
  }
}
