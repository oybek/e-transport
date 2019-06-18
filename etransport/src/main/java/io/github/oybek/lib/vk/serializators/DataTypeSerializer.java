package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.DataType;
;

import java.io.IOException;

public class DataTypeSerializer extends StdSerializer<DataType> {

  ObjectMapper objectMapper = new ObjectMapper();

  public DataTypeSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public DataTypeSerializer(Class<DataType> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(DataType value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case POLL:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asPoll().getDiscriminator());

        jgen.writeEndObject();
        break;

      case PROFILE_PHOTO:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asProfilePhoto().getDiscriminator());

        jgen.writeEndObject();
        break;

      case PROFILE_ACTIVITY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asProfileActivity().getDiscriminator());

        jgen.writeEndObject();
        break;

      case LIKE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asLike().getDiscriminator());

        jgen.writeEndObject();
        break;

      case COMMENTS:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asComments().getDiscriminator());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
