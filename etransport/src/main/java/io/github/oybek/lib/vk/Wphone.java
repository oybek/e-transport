package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Wphone implements PlatformType {

  public PlatformTypes what() {
    return PlatformTypes.WPHONE;
  }

  public String getDiscriminator() {
    return "wphone";
  }

  public Wphone() {}

  public boolean isAndroid() {
    return false;
  }

  public Android asAndroid() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isIphone() {
    return false;
  }

  public Iphone asIphone() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isWphone() {
    return true;
  }

  public Wphone asWphone() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Wphone);
  }

  @Override
  public String toString() {
    return "Wphone{" + +'}';
  }
}
