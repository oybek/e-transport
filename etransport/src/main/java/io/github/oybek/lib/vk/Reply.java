package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Reply implements PostType {

  public PostTypes what() {
    return PostTypes.REPLY;
  }

  public String getDiscriminator() {
    return "reply";
  }

  public Reply() {}

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
    return true;
  }

  public Reply asReply() {
    return this;
  }

  public boolean isPostpone() {
    return false;
  }

  public Postpone asPostpone() {
    throw new IllegalStateException("Not a $stName: " + this);
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

    return (thatObj instanceof Reply);
  }

  @Override
  public String toString() {
    return "Reply{" + +'}';
  }
}
