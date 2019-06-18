package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Widget implements PostSourceType {

  public PostSourceTypes what() {
    return PostSourceTypes.WIDGET;
  }

  public String getDiscriminator() {
    return "widget";
  }

  public Widget() {}

  public boolean isVk() {
    return false;
  }

  public Vk asVk() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isWidget() {
    return true;
  }

  public Widget asWidget() {
    return this;
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

    return (thatObj instanceof Widget);
  }

  @Override
  public String toString() {
    return "Widget{" + +'}';
  }
}
