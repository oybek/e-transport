package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Vk implements PostSourceType {

  public PostSourceTypes what() {
    return PostSourceTypes.VK;
  }

  public String getDiscriminator() {
    return "vk";
  }

  public Vk() {}

  public boolean isVk() {
    return true;
  }

  public Vk asVk() {
    return this;
  }

  public boolean isWidget() {
    return false;
  }

  public Widget asWidget() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isApi() {
    return false;
  }

  public Api asApi() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isRss() {
    return false;
  }

  public Rss asRss() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isSms() {
    return false;
  }

  public Sms asSms() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Vk);
  }

  @Override
  public String toString() {
    return "Vk{" + +'}';
  }
}
