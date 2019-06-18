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

public class PostTypeDeserializer extends StdDeserializer<PostType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public PostTypeDeserializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PostTypeDeserializer(Class<PostType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public PostType deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);

    String type = node.get("type").asText();

    switch (type) {
      case "post":
        return new Post();

      case "suggest":
        return new Suggest();

      case "reply":
        return new Reply();

      case "postpone":
        return new Postpone();

      case "copy":
        return new Copy();

      default:
        return null;
    }
  }
}
