package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Rss implements PostSourceType {

  public PostSourceTypes what() {
    return PostSourceTypes.RSS;
  }

  public String getDiscriminator() {
    return "rss";
  }

  public Rss() {}

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
    return true;
  }

  public Rss asRss() {
    return this;
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

    return (thatObj instanceof Rss);
  }

  @Override
  public String toString() {
    return "Rss{" + +'}';
  }
}
