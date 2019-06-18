package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Android implements PlatformType {

  public PlatformTypes what() {
    return PlatformTypes.ANDROID;
  }

  public String getDiscriminator() {
    return "android";
  }

  public Android() {}

  public boolean isAndroid() {
    return true;
  }

  public Android asAndroid() {
    return this;
  }

  public boolean isIphone() {
    return false;
  }

  public Iphone asIphone() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isWphone() {
    return false;
  }

  public Wphone asWphone() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Android);
  }

  @Override
  public String toString() {
    return "Android{" + +'}';
  }
}
