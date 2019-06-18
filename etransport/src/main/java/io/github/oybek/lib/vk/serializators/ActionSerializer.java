package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.Action;
;

import java.io.IOException;

public class ActionSerializer extends StdSerializer<Action> {

  ObjectMapper objectMapper = new ObjectMapper();

  public ActionSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ActionSerializer(Class<Action> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(Action value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case TEXT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asText().getDiscriminator());

        jgen.writeObjectField("payload", value.asText().getPayload());

        jgen.writeObjectField("label", value.asText().getLabel());

        jgen.writeEndObject();
        break;

      case LOCATION:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asLocation().getDiscriminator());

        jgen.writeObjectField("payload", value.asLocation().getPayload());

        jgen.writeEndObject();
        break;

      case VK_APPS:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asVkApps().getDiscriminator());

        jgen.writeObjectField("payload", value.asVkApps().getPayload());

        jgen.writeObjectField("app_id", value.asVkApps().getAppId());

        jgen.writeObjectField("owner_id", value.asVkApps().getOwnerId());

        jgen.writeObjectField("label", value.asVkApps().getLabel());

        jgen.writeObjectField("hash", value.asVkApps().getHash());

        jgen.writeEndObject();
        break;

      case VK_PAY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asVkPay().getDiscriminator());

        jgen.writeObjectField("payload", value.asVkPay().getPayload());

        jgen.writeObjectField("hash", value.asVkPay().getHash());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
