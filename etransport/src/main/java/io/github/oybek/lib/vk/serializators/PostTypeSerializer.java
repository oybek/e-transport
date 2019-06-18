package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.PostType;
;

import java.io.IOException;

public class PostTypeSerializer extends StdSerializer<PostType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public PostTypeSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PostTypeSerializer(Class<PostType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(PostType value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case POST:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asPost().getDiscriminator());

        jgen.writeEndObject();
        break;

      case SUGGEST:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asSuggest().getDiscriminator());

        jgen.writeEndObject();
        break;

      case REPLY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asReply().getDiscriminator());

        jgen.writeEndObject();
        break;

      case POSTPONE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asPostpone().getDiscriminator());

        jgen.writeEndObject();
        break;

      case COPY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asCopy().getDiscriminator());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
