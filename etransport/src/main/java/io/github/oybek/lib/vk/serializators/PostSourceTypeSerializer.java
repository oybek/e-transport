package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.PostSourceType;
;

import java.io.IOException;

public class PostSourceTypeSerializer extends StdSerializer<PostSourceType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public PostSourceTypeSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PostSourceTypeSerializer(Class<PostSourceType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(PostSourceType value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case SMS:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asSms().getDiscriminator());

        jgen.writeEndObject();
        break;

      case WIDGET:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asWidget().getDiscriminator());

        jgen.writeEndObject();
        break;

      case API:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asApi().getDiscriminator());

        jgen.writeEndObject();
        break;

      case VK:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asVk().getDiscriminator());

        jgen.writeEndObject();
        break;

      case RSS:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asRss().getDiscriminator());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
