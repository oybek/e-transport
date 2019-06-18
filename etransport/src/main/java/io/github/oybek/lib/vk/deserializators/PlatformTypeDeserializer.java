package io.github.oybek.lib.vk.deserializators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.Android;
import io.github.oybek.lib.vk.Iphone;
import io.github.oybek.lib.vk.PlatformType;
import io.github.oybek.lib.vk.Wphone;
;

import java.io.IOException;

public class PlatformTypeDeserializer extends StdDeserializer<PlatformType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public PlatformTypeDeserializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PlatformTypeDeserializer(Class<PlatformType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public PlatformType deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);

    String type = node.get("type").asText();

    switch (type) {
      case "iphone":
        return new Iphone();

      case "android":
        return new Android();

      case "wphone":
        return new Wphone();

      default:
        return null;
    }
  }
}
