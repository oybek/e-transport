package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Iphone implements PlatformType {

  public PlatformTypes what() {
    return PlatformTypes.IPHONE;
  }

  public String getDiscriminator() {
    return "iphone";
  }

  public Iphone() {}

  public boolean isAndroid() {
    return false;
  }

  public Android asAndroid() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isIphone() {
    return true;
  }

  public Iphone asIphone() {
    return this;
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

    return (thatObj instanceof Iphone);
  }

  @Override
  public String toString() {
    return "Iphone{" + +'}';
  }
}
