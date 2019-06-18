package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BoardComment {

  private int id;

  private int fromId;

  private long date;

  private String text;

  private List<Attachment> attachments;

  public BoardComment() {}

  public BoardComment(int id, int fromId, long date, String text, List<Attachment> attachments) {
    this.id = id;
    this.fromId = fromId;
    this.date = date;
    this.text = text;
    this.attachments = attachments;
  }

  public int getId() {
    return this.id;
  }

  public BoardComment setId(int v) {
    this.id = v;
    return this;
  }

  public int getFromId() {
    return this.fromId;
  }

  public BoardComment setFromId(int v) {
    this.fromId = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public BoardComment setDate(long v) {
    this.date = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public BoardComment setText(String v) {
    this.text = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public BoardComment setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof BoardComment)) return false;

    BoardComment that = (BoardComment) thatObj;

    return this.id == that.id
        && this.fromId == that.fromId
        && this.date == that.date
        && this.text.equals(that.text)
        && this.attachments.equals(that.attachments);
  }

  @Override
  public String toString() {
    return "BoardComment{"
        + "id="
        + this.id
        + ','
        + "fromId="
        + this.fromId
        + ','
        + "date="
        + this.date
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "attachments="
        + this.attachments
        + '}';
  }
}
