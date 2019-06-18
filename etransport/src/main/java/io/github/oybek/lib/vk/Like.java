package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Like implements DataType {

  public DataTypes what() {
    return DataTypes.LIKE;
  }

  public String getDiscriminator() {
    return "like";
  }

  public Like() {}

  public boolean isProfileActivity() {
    return false;
  }

  public ProfileActivity asProfileActivity() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isProfilePhoto() {
    return false;
  }

  public ProfilePhoto asProfilePhoto() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isComments() {
    return false;
  }

  public Comments asComments() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isLike() {
    return true;
  }

  public Like asLike() {
    return this;
  }

  public boolean isPoll() {
    return false;
  }

  public Poll asPoll() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Like);
  }

  @Override
  public String toString() {
    return "Like{" + +'}';
  }
}
