package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Suggest implements PostType {

  public PostTypes what() {
    return PostTypes.SUGGEST;
  }

  public String getDiscriminator() {
    return "suggest";
  }

  public Suggest() {}

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
    return false;
  }

  public Postpone asPostpone() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isSuggest() {
    return true;
  }

  public Suggest asSuggest() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Suggest);
  }

  @Override
  public String toString() {
    return "Suggest{" + +'}';
  }
}
