package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.PostSourceTypeDeserializer;
import io.github.oybek.lib.vk.serializators.PostSourceTypeSerializer;

@JsonSerialize(using = PostSourceTypeSerializer.class)
@JsonDeserialize(using = PostSourceTypeDeserializer.class)
public interface PostSourceType {

  enum PostSourceTypes {
    VK,
    WIDGET,
    API,
    RSS,
    SMS
  }

  PostSourceTypes what();

  boolean isVk();

  Vk asVk();

  boolean isWidget();

  Widget asWidget();

  boolean isApi();

  Api asApi();

  boolean isRss();

  Rss asRss();

  boolean isSms();

  Sms asSms();
}
