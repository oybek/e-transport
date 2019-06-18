package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Comment {

  private int id;

  private int userId;

  private long date;

  private String text;

  private int replyToUser;

  private int replyToComment;

  private List<Attachment> attachments;

  private List<Integer> parentsStack;

  private CommentsThread thread;

  public Comment() {}

  public Comment(
      int id,
      int userId,
      long date,
      String text,
      int replyToUser,
      int replyToComment,
      List<Attachment> attachments,
      List<Integer> parentsStack,
      CommentsThread thread) {
    this.id = id;
    this.userId = userId;
    this.date = date;
    this.text = text;
    this.replyToUser = replyToUser;
    this.replyToComment = replyToComment;
    this.attachments = attachments;
    this.parentsStack = parentsStack;
    this.thread = thread;
  }

  public int getId() {
    return this.id;
  }

  public Comment setId(int v) {
    this.id = v;
    return this;
  }

  public int getUserId() {
    return this.userId;
  }

  public Comment setUserId(int v) {
    this.userId = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public Comment setDate(long v) {
    this.date = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public Comment setText(String v) {
    this.text = v;
    return this;
  }

  public int getReplyToUser() {
    return this.replyToUser;
  }

  public Comment setReplyToUser(int v) {
    this.replyToUser = v;
    return this;
  }

  public int getReplyToComment() {
    return this.replyToComment;
  }

  public Comment setReplyToComment(int v) {
    this.replyToComment = v;
    return this;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }

  public Comment setAttachments(List<Attachment> v) {
    this.attachments = v;
    return this;
  }

  public List<Integer> getParentsStack() {
    return this.parentsStack;
  }

  public Comment setParentsStack(List<Integer> v) {
    this.parentsStack = v;
    return this;
  }

  public CommentsThread getThread() {
    return this.thread;
  }

  public Comment setThread(CommentsThread v) {
    this.thread = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Comment)) return false;

    Comment that = (Comment) thatObj;

    return this.id == that.id
        && this.userId == that.userId
        && this.date == that.date
        && this.text.equals(that.text)
        && this.replyToUser == that.replyToUser
        && this.replyToComment == that.replyToComment
        && this.attachments.equals(that.attachments)
        && this.parentsStack.equals(that.parentsStack)
        && this.thread.equals(that.thread);
  }

  @Override
  public String toString() {
    return "Comment{"
        + "id="
        + this.id
        + ','
        + "userId="
        + this.userId
        + ','
        + "date="
        + this.date
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "replyToUser="
        + this.replyToUser
        + ','
        + "replyToComment="
        + this.replyToComment
        + ','
        + "attachments="
        + this.attachments
        + ','
        + "parentsStack="
        + this.parentsStack
        + ','
        + "thread="
        + this.thread
        + '}';
  }
}
