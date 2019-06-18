package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProfilePhoto implements DataType {

  public DataTypes what() {
    return DataTypes.PROFILE_PHOTO;
  }

  public String getDiscriminator() {
    return "profile_photo";
  }

  public ProfilePhoto() {}

  public boolean isProfileActivity() {
    return false;
  }

  public ProfileActivity asProfileActivity() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isProfilePhoto() {
    return true;
  }

  public ProfilePhoto asProfilePhoto() {
    return this;
  }

  public boolean isComments() {
    return false;
  }

  public Comments asComments() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isLike() {
    return false;
  }

  public Like asLike() {
    throw new IllegalStateException("Not a $stName: " + this);
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

    return (thatObj instanceof ProfilePhoto);
  }

  @Override
  public String toString() {
    return "ProfilePhoto{" + +'}';
  }
}
