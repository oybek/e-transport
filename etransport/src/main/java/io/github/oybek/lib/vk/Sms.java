package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Sms implements PostSourceType {

  public PostSourceTypes what() {
    return PostSourceTypes.SMS;
  }

  public String getDiscriminator() {
    return "sms";
  }

  public Sms() {}

  public boolean isVk() {
    return false;
  }

  public Vk asVk() {
    throw new IllegalStateException("Not a $stName: " + this);
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
    return true;
  }

  public Sms asSms() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Sms);
  }

  @Override
  public String toString() {
    return "Sms{" + +'}';
  }
}
