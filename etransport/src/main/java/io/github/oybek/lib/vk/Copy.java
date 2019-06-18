package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Copy implements PostType {

  public PostTypes what() {
    return PostTypes.COPY;
  }

  public String getDiscriminator() {
    return "copy";
  }

  public Copy() {}

  public boolean isPost() {
    return false;
  }

  public Post asPost() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isCopy() {
    return true;
  }

  public Copy asCopy() {
    return this;
  }

  public boolean isReply() {
    return false;
  }

  public Reply asReply() {
    throw new IllegalStateException("Not a $stName: " + this);
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

    return (thatObj instanceof Copy);
  }

  @Override
  public String toString() {
    return "Copy{" + +'}';
  }
}
