package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.PlatformType;
;

import java.io.IOException;

public class PlatformTypeSerializer extends StdSerializer<PlatformType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public PlatformTypeSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PlatformTypeSerializer(Class<PlatformType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(PlatformType value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case IPHONE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asIphone().getDiscriminator());

        jgen.writeEndObject();
        break;

      case ANDROID:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asAndroid().getDiscriminator());

        jgen.writeEndObject();
        break;

      case WPHONE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asWphone().getDiscriminator());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
