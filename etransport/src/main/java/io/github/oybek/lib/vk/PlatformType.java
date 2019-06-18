package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.PlatformTypeDeserializer;
import io.github.oybek.lib.vk.serializators.PlatformTypeSerializer;

@JsonSerialize(using = PlatformTypeSerializer.class)
@JsonDeserialize(using = PlatformTypeDeserializer.class)
public interface PlatformType {

  enum PlatformTypes {
    ANDROID,
    IPHONE,
    WPHONE
  }

  PlatformTypes what();

  boolean isAndroid();

  Android asAndroid();

  boolean isIphone();

  Iphone asIphone();

  boolean isWphone();

  Wphone asWphone();
}
