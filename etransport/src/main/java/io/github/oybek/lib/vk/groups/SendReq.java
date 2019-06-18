package io.github.oybek.lib.vk.groups;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.oybek.lib.vk.Keyboard;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SendReq {

  private int userId;

  private int randomId;

  private int peerId;

  private String domain;

  private int chatId;

  private String userIds;

  private String message;

  private double lat;

  private double Long;

  private String attachment;

  private int replyTo;

  private String forwardMessages;

  private int stickerId;

  private int groupId;

  private String payload;

  private int dontParseLinks;

  private int disableMentions;

  private Keyboard keyboard;

  private String accessToken;

  private String v;

  public SendReq() {}

  public SendReq(
      int userId,
      int randomId,
      int peerId,
      String domain,
      int chatId,
      String userIds,
      String message,
      double lat,
      double Long,
      String attachment,
      int replyTo,
      String forwardMessages,
      int stickerId,
      int groupId,
      String payload,
      int dontParseLinks,
      int disableMentions,
      Keyboard keyboard,
      String accessToken,
      String v) {
    this.userId = userId;
    this.randomId = randomId;
    this.peerId = peerId;
    this.domain = domain;
    this.chatId = chatId;
    this.userIds = userIds;
    this.message = message;
    this.lat = lat;
    this.Long = Long;
    this.attachment = attachment;
    this.replyTo = replyTo;
    this.forwardMessages = forwardMessages;
    this.stickerId = stickerId;
    this.groupId = groupId;
    this.payload = payload;
    this.dontParseLinks = dontParseLinks;
    this.disableMentions = disableMentions;
    this.keyboard = keyboard;
    this.accessToken = accessToken;
    this.v = v;
  }

  public int getUserId() {
    return this.userId;
  }

  public SendReq setUserId(int v) {
    this.userId = v;
    return this;
  }

  public int getRandomId() {
    return this.randomId;
  }

  public SendReq setRandomId(int v) {
    this.randomId = v;
    return this;
  }

  public int getPeerId() {
    return this.peerId;
  }

  public SendReq setPeerId(int v) {
    this.peerId = v;
    return this;
  }

  public String getDomain() {
    return this.domain;
  }

  public SendReq setDomain(String v) {
    this.domain = v;
    return this;
  }

  public int getChatId() {
    return this.chatId;
  }

  public SendReq setChatId(int v) {
    this.chatId = v;
    return this;
  }

  public String getUserIds() {
    return this.userIds;
  }

  public SendReq setUserIds(String v) {
    this.userIds = v;
    return this;
  }

  public String getMessage() {
    return this.message;
  }

  public SendReq setMessage(String v) {
    this.message = v;
    return this;
  }

  public double getLat() {
    return this.lat;
  }

  public SendReq setLat(double v) {
    this.lat = v;
    return this;
  }

  public double getLong() {
    return this.Long;
  }

  public SendReq setLong(double v) {
    this.Long = v;
    return this;
  }

  public String getAttachment() {
    return this.attachment;
  }

  public SendReq setAttachment(String v) {
    this.attachment = v;
    return this;
  }

  public int getReplyTo() {
    return this.replyTo;
  }

  public SendReq setReplyTo(int v) {
    this.replyTo = v;
    return this;
  }

  public String getForwardMessages() {
    return this.forwardMessages;
  }

  public SendReq setForwardMessages(String v) {
    this.forwardMessages = v;
    return this;
  }

  public int getStickerId() {
    return this.stickerId;
  }

  public SendReq setStickerId(int v) {
    this.stickerId = v;
    return this;
  }

  public int getGroupId() {
    return this.groupId;
  }

  public SendReq setGroupId(int v) {
    this.groupId = v;
    return this;
  }

  public String getPayload() {
    return this.payload;
  }

  public SendReq setPayload(String v) {
    this.payload = v;
    return this;
  }

  public int getDontParseLinks() {
    return this.dontParseLinks;
  }

  public SendReq setDontParseLinks(int v) {
    this.dontParseLinks = v;
    return this;
  }

  public int getDisableMentions() {
    return this.disableMentions;
  }

  public SendReq setDisableMentions(int v) {
    this.disableMentions = v;
    return this;
  }

  public Keyboard getKeyboard() {
    return this.keyboard;
  }

  public SendReq setKeyboard(Keyboard v) {
    this.keyboard = v;
    return this;
  }

  public String getAccessToken() {
    return this.accessToken;
  }

  public SendReq setAccessToken(String v) {
    this.accessToken = v;
    return this;
  }

  public String getV() {
    return this.v;
  }

  public SendReq setV(String v) {
    this.v = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof SendReq)) return false;

    SendReq that = (SendReq) thatObj;

    return this.userId == that.userId
        && this.randomId == that.randomId
        && this.peerId == that.peerId
        && this.domain.equals(that.domain)
        && this.chatId == that.chatId
        && this.userIds.equals(that.userIds)
        && this.message.equals(that.message)
        && this.lat == that.lat
        && this.Long == that.Long
        && this.attachment.equals(that.attachment)
        && this.replyTo == that.replyTo
        && this.forwardMessages.equals(that.forwardMessages)
        && this.stickerId == that.stickerId
        && this.groupId == that.groupId
        && this.payload.equals(that.payload)
        && this.dontParseLinks == that.dontParseLinks
        && this.disableMentions == that.disableMentions
        && this.keyboard.equals(that.keyboard)
        && this.accessToken.equals(that.accessToken)
        && this.v.equals(that.v);
  }

  @Override
  public String toString() {
    return "SendReq{"
        + "userId="
        + this.userId
        + ','
        + "randomId="
        + this.randomId
        + ','
        + "peerId="
        + this.peerId
        + ','
        + "domain="
        + '\''
        + this.domain
        + '\''
        + ','
        + "chatId="
        + this.chatId
        + ','
        + "userIds="
        + '\''
        + this.userIds
        + '\''
        + ','
        + "message="
        + '\''
        + this.message
        + '\''
        + ','
        + "lat="
        + this.lat
        + ','
        + "Long="
        + this.Long
        + ','
        + "attachment="
        + '\''
        + this.attachment
        + '\''
        + ','
        + "replyTo="
        + this.replyTo
        + ','
        + "forwardMessages="
        + '\''
        + this.forwardMessages
        + '\''
        + ','
        + "stickerId="
        + this.stickerId
        + ','
        + "groupId="
        + this.groupId
        + ','
        + "payload="
        + '\''
        + this.payload
        + '\''
        + ','
        + "dontParseLinks="
        + this.dontParseLinks
        + ','
        + "disableMentions="
        + this.disableMentions
        + ','
        + "keyboard="
        + this.keyboard
        + ','
        + "accessToken="
        + '\''
        + this.accessToken
        + '\''
        + ','
        + "v="
        + '\''
        + this.v
        + '\''
        + '}';
  }
}
