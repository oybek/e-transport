package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Poll implements DataType {

  public DataTypes what() {
    return DataTypes.POLL;
  }

  public String getDiscriminator() {
    return "poll";
  }

  public Poll() {}

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
    return false;
  }

  public Like asLike() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isPoll() {
    return true;
  }

  public Poll asPoll() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Poll);
  }

  @Override
  public String toString() {
    return "Poll{" + +'}';
  }
}
