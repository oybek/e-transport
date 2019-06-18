package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Postpone implements PostType {

  public PostTypes what() {
    return PostTypes.POSTPONE;
  }

  public String getDiscriminator() {
    return "postpone";
  }

  public Postpone() {}

  public boolean isPost() {
    return false;
  }

  public Post asPost() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isCopy() {
    return false;
  }

  public Copy asCopy() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isReply() {
    return false;
  }

  public Reply asReply() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isPostpone() {
    return true;
  }

  public Postpone asPostpone() {
    return this;
  }

  public boolean isSuggest() {
    return false;
  }

  public Suggest asSuggest() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Postpone);
  }

  @Override
  public String toString() {
    return "Postpone{" + +'}';
  }
}
