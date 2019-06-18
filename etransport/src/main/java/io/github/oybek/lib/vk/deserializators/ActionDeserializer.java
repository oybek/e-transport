package io.github.oybek.lib.vk.deserializators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.*;
;

import java.io.IOException;

public class ActionDeserializer extends StdDeserializer<Action> {

  ObjectMapper objectMapper = new ObjectMapper();

  public ActionDeserializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ActionDeserializer(Class<Action> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public Action deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);

    String type = node.get("type").asText();

    switch (type) {
      case "text":
        return new Text(
            objectMapper.readValue(node.get("payload").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(node.get("label").traverse(jp.getCodec()), String.class));

      case "location":
        return new Location(
            objectMapper.readValue(node.get("payload").traverse(jp.getCodec()), String.class));

      case "open_app":
        return new VkApps(
            objectMapper.readValue(node.get("payload").traverse(jp.getCodec()), String.class),
            node.get("app_id").asInt(),
            node.get("owner_id").asInt(),
            objectMapper.readValue(node.get("label").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(node.get("hash").traverse(jp.getCodec()), String.class));

      case "vkpay":
        return new VkPay(
            objectMapper.readValue(node.get("payload").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(node.get("hash").traverse(jp.getCodec()), String.class));

      default:
        return null;
    }
  }
}
