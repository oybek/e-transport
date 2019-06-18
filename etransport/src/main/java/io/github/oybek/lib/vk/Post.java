package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Post implements PostType {

  public PostTypes what() {
    return PostTypes.POST;
  }

  public String getDiscriminator() {
    return "post";
  }

  public Post() {}

  public boolean isPost() {
    return true;
  }

  public Post asPost() {
    return this;
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
    return false;
  }

  public Suggest asSuggest() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    return (thatObj instanceof Post);
  }

  @Override
  public String toString() {
    return "Post{" + +'}';
  }
}
