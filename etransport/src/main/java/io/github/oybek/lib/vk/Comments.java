package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Comments implements DataType {

  public DataTypes what() {
    return DataTypes.COMMENTS;
  }

  public String getDiscriminator() {
    return "comments";
  }

  public Comments() {}

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
    return true;
  }

  public Comments asComments() {
    return this;
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

    return (thatObj instanceof Comments);
  }

  @Override
  public String toString() {
    return "Comments{" + +'}';
  }
}
