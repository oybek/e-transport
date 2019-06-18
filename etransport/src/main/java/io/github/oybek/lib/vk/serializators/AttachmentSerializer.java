package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.Attachment;

import java.io.IOException;

public class AttachmentSerializer extends StdSerializer<Attachment> {

  ObjectMapper objectMapper = new ObjectMapper();

  public AttachmentSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public AttachmentSerializer(Class<Attachment> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(Attachment value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case ATTACHMENT_PHOTO:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asAttachmentPhoto().getDiscriminator());

        jgen.writeObjectField("photo", value.asAttachmentPhoto().getPhoto());

        jgen.writeEndObject();
        break;

      case ATTACHMENT_VIDEO:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asAttachmentVideo().getDiscriminator());

        jgen.writeObjectField("video", value.asAttachmentVideo().getVideo());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
