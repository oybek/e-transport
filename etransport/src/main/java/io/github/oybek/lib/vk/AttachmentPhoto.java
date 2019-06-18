package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttachmentPhoto implements Attachment {

  private Photo photo;

  public Attachments what() {
    return Attachments.ATTACHMENT_PHOTO;
  }

  public String getDiscriminator() {
    return "photo";
  }

  public AttachmentPhoto() {}

  public AttachmentPhoto(Photo photo) {
    this.photo = photo;
  }

  public Photo getPhoto() {
    return this.photo;
  }

  public AttachmentPhoto setPhoto(Photo v) {
    this.photo = v;
    return this;
  }

  public boolean isAttachmentPhoto() {
    return true;
  }

  public AttachmentPhoto asAttachmentPhoto() {
    return this;
  }

  public boolean isAttachmentVideo() {
    return false;
  }

  public AttachmentVideo asAttachmentVideo() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof AttachmentPhoto)) return false;

    AttachmentPhoto that = (AttachmentPhoto) thatObj;

    return this.photo.equals(that.photo);
  }

  @Override
  public String toString() {
    return "AttachmentPhoto{" + "photo=" + this.photo + '}';
  }
}
