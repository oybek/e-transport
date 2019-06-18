package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.PostTypeDeserializer;
import io.github.oybek.lib.vk.serializators.PostTypeSerializer;

@JsonSerialize(using = PostTypeSerializer.class)
@JsonDeserialize(using = PostTypeDeserializer.class)
public interface PostType {

  enum PostTypes {
    POST,
    COPY,
    REPLY,
    POSTPONE,
    SUGGEST
  }

  PostTypes what();

  boolean isPost();

  Post asPost();

  boolean isCopy();

  Copy asCopy();

  boolean isReply();

  Reply asReply();

  boolean isPostpone();

  Postpone asPostpone();

  boolean isSuggest();

  Suggest asSuggest();
}
