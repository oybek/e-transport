package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttachmentVideo implements Attachment {

  private Video video;

  public Attachments what() {
    return Attachments.ATTACHMENT_VIDEO;
  }

  public String getDiscriminator() {
    return "video";
  }

  public AttachmentVideo() {}

  public AttachmentVideo(Video video) {
    this.video = video;
  }

  public Video getVideo() {
    return this.video;
  }

  public AttachmentVideo setVideo(Video v) {
    this.video = v;
    return this;
  }

  public boolean isAttachmentPhoto() {
    return false;
  }

  public AttachmentPhoto asAttachmentPhoto() {
    throw new IllegalStateException("Not a $stName: " + this);
  }

  public boolean isAttachmentVideo() {
    return true;
  }

  public AttachmentVideo asAttachmentVideo() {
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof AttachmentVideo)) return false;

    AttachmentVideo that = (AttachmentVideo) thatObj;

    return this.video.equals(that.video);
  }

  @Override
  public String toString() {
    return "AttachmentVideo{" + "video=" + this.video + '}';
  }
}
