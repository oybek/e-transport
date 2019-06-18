package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProfileActivity implements DataType {

  public DataTypes what() {
    return DataTypes.PROFILE_ACTIVITY;
  }

  public String getDiscriminator() {
    return "profile_activity";
  }

  public ProfileActivity() {}

  public boolean isProfileActivity() {
    return true;
  }

  public ProfileActivity asProfileActivity() {
    return this;
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

    return (thatObj instanceof ProfileActivity);
  }

  @Override
  public String toString() {
    return "ProfileActivity{" + +'}';
  }
}
