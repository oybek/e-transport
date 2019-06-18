package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.AttachmentDeserializer;
import io.github.oybek.lib.vk.serializators.AttachmentSerializer;

@JsonSerialize(using = AttachmentSerializer.class)
@JsonDeserialize(using = AttachmentDeserializer.class)
public interface Attachment {

  enum Attachments {
    ATTACHMENT_PHOTO,
    ATTACHMENT_VIDEO
  }

  Attachments what();

  boolean isAttachmentPhoto();

  AttachmentPhoto asAttachmentPhoto();

  boolean isAttachmentVideo();

  AttachmentVideo asAttachmentVideo();
}
