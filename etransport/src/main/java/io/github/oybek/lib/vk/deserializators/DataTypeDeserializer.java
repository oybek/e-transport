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

public class DataTypeDeserializer extends StdDeserializer<DataType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public DataTypeDeserializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public DataTypeDeserializer(Class<DataType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public DataType deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);

    String type = node.get("type").asText();

    switch (type) {
      case "poll":
        return new Poll();

      case "profile_photo":
        return new ProfilePhoto();

      case "profile_activity":
        return new ProfileActivity();

      case "like":
        return new Like();

      case "comments":
        return new Comments();

      default:
        return null;
    }
  }
}
