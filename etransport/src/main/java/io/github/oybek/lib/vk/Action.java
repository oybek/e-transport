package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.ActionDeserializer;
import io.github.oybek.lib.vk.serializators.ActionSerializer;

@JsonSerialize(using = ActionSerializer.class)
@JsonDeserialize(using = ActionDeserializer.class)
public interface Action {

  enum Actions {
    TEXT,
    LOCATION,
    VK_PAY,
    VK_APPS
  }

  Actions what();

  String getDiscriminator();

  String getPayload();

  Action setPayload(String v);

  boolean isText();

  Text asText();

  boolean isLocation();

  Location asLocation();

  boolean isVkPay();

  VkPay asVkPay();

  boolean isVkApps();

  VkApps asVkApps();
}
