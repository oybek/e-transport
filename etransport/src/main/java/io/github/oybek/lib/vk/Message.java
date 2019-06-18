package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Message {

  private int id;

  private long date;

  private int peerId;

  private int fromId;

  private String text;

  private int randomId;

  private String ref;

  private String refSource;

  private List<Attachment> attachments;

  private boolean important;

  private Geo geo;

  private String payload;

  private int out;

  private boolean isHidden;

  private int conversationMessageId;

  private List<Message> fwdMessages;

  private Message replyMessage;

  public Message() {}

  public Message(
      int id,
      long date,
      int peerId,
      int fromId,
      String text,
      int randomId,
      String ref,
      String refSource,
      List<Attachment> attachments,
      boolean important,
      Geo geo,
      String payload,
      int out,
      boolean isHidden,
      int conversationMessageId,
      List<Message> fwdMessages,
      Message replyMessage) {
    this.id = id;
    this.date = date;
    this.peerId = peerId;
    this.fromId = fromId;
    this.text = text;
    this.randomId = randomId;
    this.ref = ref;
    this.refSource = refSource;
    this.attachments = attachments;
    this.important = important;
    this.geo = geo;
    this.payload = payload;
    this.out = out;
    this.isHidden = isHidden;
    this.conversationMessageId = conversationMessageId;
    this.fwdMessages = fwdMessages;
    this.replyMessage = replyMessage;
  }

  public int getId() {
    return this.id;
  }

  public Message setId(int v) {
    this.id = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public Message setDate(long v) {
    this.date = v;
    return this;
  }

  public int getPeerId() {
    return this.peerId;
  }

  public Message setPeerId(int v) {
    this.peerId = v;
    return this;
  }

  public int getFromId() {
    return this.fromId;
  }

  public Message setFromId(int v) {
    this.fromId = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public Message setText(String v) {
    this.text = v;
    return this;
  }

  public int getRandomId() {
    return this.randomId;
  }

  public Message setRandomId(int v) {
    this.randomId = v;
    return this;
  }

  public String getRef() {
    return this.ref;
  }

  public Message setRef(String v) {
    this.ref = v;
    return this;
  }

  public String getRefSource() {
    return this.refSource;
  }

  public Message setRefSource(String v) {
    this.refSource = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public Message setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  public boolean getImportant() {
    return this.important;
  }

  public Message setImportant(boolean v) {
    this.important = v;
    return this;
  }

  public Geo getGeo() {
    return this.geo;
  }

  public Message setGeo(Geo v) {
    this.geo = v;
    return this;
  }

  public String getPayload() {
    return this.payload;
  }

  public Message setPayload(String v) {
    this.payload = v;
    return this;
  }

  public int getOut() {
    return this.out;
  }

  public Message setOut(int v) {
    this.out = v;
    return this;
  }

  public boolean getIsHidden() {
    return this.isHidden;
  }

  public Message setIsHidden(boolean v) {
    this.isHidden = v;
    return this;
  }

  public int getConversationMessageId() {
    return this.conversationMessageId;
  }

  public Message setConversationMessageId(int v) {
    this.conversationMessageId = v;
    return this;
  }

  public List<Message> getFwdMessages() {
    return this.fwdMessages;
  }

  public Message setFwdMessages(List<Message> v) {
    this.fwdMessages = v;
    return this;
  }

  public Message getReplyMessage() {
    return this.replyMessage;
  }

  public Message setReplyMessage(Message v) {
    this.replyMessage = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Message)) return false;

    Message that = (Message) thatObj;

    return this.id == that.id
        && this.date == that.date
        && this.peerId == that.peerId
        && this.fromId == that.fromId
        && this.text.equals(that.text)
        && this.randomId == that.randomId
        && this.ref.equals(that.ref)
        && this.refSource.equals(that.refSource)
        && this.attachments.equals(that.attachments)
        && this.important == that.important
        && this.geo.equals(that.geo)
        && this.payload.equals(that.payload)
        && this.out == that.out
        && this.isHidden == that.isHidden
        && this.conversationMessageId == that.conversationMessageId
        && this.fwdMessages.equals(that.fwdMessages)
        && this.replyMessage.equals(that.replyMessage);
  }

  @Override
  public String toString() {
    return "Message{"
        + "id="
        + this.id
        + ','
        + "date="
        + this.date
        + ','
        + "peerId="
        + this.peerId
        + ','
        + "fromId="
        + this.fromId
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "randomId="
        + this.randomId
        + ','
        + "ref="
        + '\''
        + this.ref
        + '\''
        + ','
        + "refSource="
        + '\''
        + this.refSource
        + '\''
        + ','
        + "attachments="
        + this.attachments
        + ','
        + "important="
        + this.important
        + ','
        + "geo="
        + this.geo
        + ','
        + "payload="
        + '\''
        + this.payload
        + '\''
        + ','
        + "out="
        + this.out
        + ','
        + "isHidden="
        + this.isHidden
        + ','
        + "conversationMessageId="
        + this.conversationMessageId
        + ','
        + "fwdMessages="
        + this.fwdMessages
        + ','
        + "replyMessage="
        + this.replyMessage
        + '}';
  }
}
