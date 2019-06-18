package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Text implements Action {

  private String payload;

  private String label;

  public Actions what() {
    return Actions.TEXT;
  }

  public String getDiscriminator() {
    return "text";
  }

  public Text() {}

  public Text(String payload, String label) {
    this.payload = payload;
    this.label = label;
  }

  public String getPayload() {
    return this.payload;
  }

  public Text setPayload(String v) {
    this.payload = v;
    return this;
  }

  public String getLabel() {
    return this.label;
  }

  public Text setLabel(String v) {
    this.label = v;
    return this;
  }

  public boolean isText() {
    return true;
  }

  public Text asText() {
    return this;
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
    return false;
  }

  public VkApps asVkApps() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Text)) return false;

    Text that = (Text) thatObj;

    return this.payload.equals(that.payload) && this.label.equals(that.label);
  }

  @Override
  public String toString() {
    return "Text{"
        + "payload="
        + '\''
        + this.payload
        + '\''
        + ','
        + "label="
        + '\''
        + this.label
        + '\''
        + '}';
  }
}
