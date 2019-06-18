package io.github.oybek.lib.vk.serializators;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.Event;

import java.io.IOException;

public class EventSerializer extends StdSerializer<Event> {

  ObjectMapper objectMapper = new ObjectMapper();

  public EventSerializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public EventSerializer(Class<Event> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
  }

  @Override
  public void serialize(Event value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

    switch (value.what()) {
      case EVENT_PHOTO_COMMENT_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPhotoCommentNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPhotoCommentNew().getGroupId());

        jgen.writeObjectField("photo_id", value.asEventPhotoCommentNew().getPhotoId());

        jgen.writeObjectField("photo_owner_id", value.asEventPhotoCommentNew().getPhotoOwnerId());

        jgen.writeObjectField("id", value.asEventPhotoCommentNew().getId());

        jgen.writeObjectField("user_id", value.asEventPhotoCommentNew().getUserId());

        jgen.writeObjectField("date", value.asEventPhotoCommentNew().getDate());

        jgen.writeObjectField("text", value.asEventPhotoCommentNew().getText());

        jgen.writeObjectField("reply_to_user", value.asEventPhotoCommentNew().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventPhotoCommentNew().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventPhotoCommentNew().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventPhotoCommentNew().getParentsStack());

        jgen.writeObjectField("thread", value.asEventPhotoCommentNew().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_BOARD_POST_DELETE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventBoardPostDelete().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventBoardPostDelete().getGroupId());

        jgen.writeObjectField("topic_id", value.asEventBoardPostDelete().getTopicId());

        jgen.writeObjectField("topic_owner_id", value.asEventBoardPostDelete().getTopicOwnerId());

        jgen.writeObjectField("id", value.asEventBoardPostDelete().getId());

        jgen.writeEndObject();
        break;

      case EVENT_VIDEO_COMMENT_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVideoCommentNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVideoCommentNew().getGroupId());

        jgen.writeObjectField("video_id", value.asEventVideoCommentNew().getVideoId());

        jgen.writeObjectField("video_owner_id", value.asEventVideoCommentNew().getVideoOwnerId());

        jgen.writeObjectField("id", value.asEventVideoCommentNew().getId());

        jgen.writeObjectField("user_id", value.asEventVideoCommentNew().getUserId());

        jgen.writeObjectField("date", value.asEventVideoCommentNew().getDate());

        jgen.writeObjectField("text", value.asEventVideoCommentNew().getText());

        jgen.writeObjectField("reply_to_user", value.asEventVideoCommentNew().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventVideoCommentNew().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventVideoCommentNew().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventVideoCommentNew().getParentsStack());

        jgen.writeObjectField("thread", value.asEventVideoCommentNew().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_MARKET_COMMENT_RESTORE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMarketCommentRestore().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMarketCommentRestore().getGroupId());

        jgen.writeObjectField(
            "market_owner_id", value.asEventMarketCommentRestore().getMarketOwnerId());

        jgen.writeObjectField("item_id", value.asEventMarketCommentRestore().getItemId());

        jgen.writeObjectField("id", value.asEventMarketCommentRestore().getId());

        jgen.writeObjectField("user_id", value.asEventMarketCommentRestore().getUserId());

        jgen.writeObjectField("date", value.asEventMarketCommentRestore().getDate());

        jgen.writeObjectField("text", value.asEventMarketCommentRestore().getText());

        jgen.writeObjectField(
            "reply_to_user", value.asEventMarketCommentRestore().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventMarketCommentRestore().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventMarketCommentRestore().getAttachments());

        jgen.writeObjectField(
            "parents_stack", value.asEventMarketCommentRestore().getParentsStack());

        jgen.writeObjectField("thread", value.asEventMarketCommentRestore().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_VIDEO_COMMENT_DELETE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVideoCommentDelete().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVideoCommentDelete().getGroupId());

        jgen.writeObjectField("owner_id", value.asEventVideoCommentDelete().getOwnerId());

        jgen.writeObjectField("id", value.asEventVideoCommentDelete().getId());

        jgen.writeObjectField("user_id", value.asEventVideoCommentDelete().getUserId());

        jgen.writeObjectField("deleter_id", value.asEventVideoCommentDelete().getDeleterId());

        jgen.writeObjectField("video_id", value.asEventVideoCommentDelete().getVideoId());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_REPOST:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallRepost().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallRepost().getGroupId());

        jgen.writeObjectField("postponed_id", value.asEventWallRepost().getPostponedId());

        jgen.writeObjectField("id", value.asEventWallRepost().getId());

        jgen.writeObjectField("owner_id", value.asEventWallRepost().getOwnerId());

        jgen.writeObjectField("from_id", value.asEventWallRepost().getFromId());

        jgen.writeObjectField("created_by", value.asEventWallRepost().getCreatedBy());

        jgen.writeObjectField("date", value.asEventWallRepost().getDate());

        jgen.writeObjectField("text", value.asEventWallRepost().getText());

        jgen.writeObjectField("reply_owner_id", value.asEventWallRepost().getReplyOwnerId());

        jgen.writeObjectField("reply_post_id", value.asEventWallRepost().getReplyPostId());

        jgen.writeObjectField("friends_only", value.asEventWallRepost().getFriendsOnly());

        jgen.writeObjectField("comments", value.asEventWallRepost().getComments());

        jgen.writeObjectField("likes", value.asEventWallRepost().getLikes());

        jgen.writeObjectField("reposts", value.asEventWallRepost().getReposts());

        jgen.writeObjectField("views", value.asEventWallRepost().getViews());

        jgen.writeObjectField("post_type", value.asEventWallRepost().getPostType());

        jgen.writeObjectField("post_source", value.asEventWallRepost().getPostSource());

        jgen.writeObjectField("attachments", value.asEventWallRepost().getAttachments());

        jgen.writeObjectField("geo", value.asEventWallRepost().getGeo());

        jgen.writeObjectField("signer_id", value.asEventWallRepost().getSignerId());

        jgen.writeObjectField("copy_history", value.asEventWallRepost().getCopyHistory());

        jgen.writeObjectField("can_pin", value.asEventWallRepost().getCanPin());

        jgen.writeObjectField("can_delete", value.asEventWallRepost().getCanDelete());

        jgen.writeObjectField("can_edit", value.asEventWallRepost().getCanEdit());

        jgen.writeObjectField("is_pinned", value.asEventWallRepost().getIsPinned());

        jgen.writeObjectField("marked_as_ads", value.asEventWallRepost().getMarkedAsAds());

        jgen.writeObjectField("is_favorite", value.asEventWallRepost().getIsFavorite());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_REPLY_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallReplyNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallReplyNew().getGroupId());

        jgen.writeObjectField("post_id", value.asEventWallReplyNew().getPostId());

        jgen.writeObjectField("post_ownder_id", value.asEventWallReplyNew().getPostOwnderId());

        jgen.writeObjectField("id", value.asEventWallReplyNew().getId());

        jgen.writeObjectField("user_id", value.asEventWallReplyNew().getUserId());

        jgen.writeObjectField("date", value.asEventWallReplyNew().getDate());

        jgen.writeObjectField("text", value.asEventWallReplyNew().getText());

        jgen.writeObjectField("reply_to_user", value.asEventWallReplyNew().getReplyToUser());

        jgen.writeObjectField("reply_to_comment", value.asEventWallReplyNew().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventWallReplyNew().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventWallReplyNew().getParentsStack());

        jgen.writeObjectField("thread", value.asEventWallReplyNew().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_MARKET_COMMENT_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMarketCommentEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMarketCommentEdit().getGroupId());

        jgen.writeObjectField(
            "market_owner_id", value.asEventMarketCommentEdit().getMarketOwnerId());

        jgen.writeObjectField("item_id", value.asEventMarketCommentEdit().getItemId());

        jgen.writeObjectField("id", value.asEventMarketCommentEdit().getId());

        jgen.writeObjectField("user_id", value.asEventMarketCommentEdit().getUserId());

        jgen.writeObjectField("date", value.asEventMarketCommentEdit().getDate());

        jgen.writeObjectField("text", value.asEventMarketCommentEdit().getText());

        jgen.writeObjectField("reply_to_user", value.asEventMarketCommentEdit().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventMarketCommentEdit().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventMarketCommentEdit().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventMarketCommentEdit().getParentsStack());

        jgen.writeObjectField("thread", value.asEventMarketCommentEdit().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_VIDEO_COMMENT_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVideoCommentEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVideoCommentEdit().getGroupId());

        jgen.writeObjectField("video_id", value.asEventVideoCommentEdit().getVideoId());

        jgen.writeObjectField("video_owner_id", value.asEventVideoCommentEdit().getVideoOwnerId());

        jgen.writeObjectField("id", value.asEventVideoCommentEdit().getId());

        jgen.writeObjectField("user_id", value.asEventVideoCommentEdit().getUserId());

        jgen.writeObjectField("date", value.asEventVideoCommentEdit().getDate());

        jgen.writeObjectField("text", value.asEventVideoCommentEdit().getText());

        jgen.writeObjectField("reply_to_user", value.asEventVideoCommentEdit().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventVideoCommentEdit().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventVideoCommentEdit().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventVideoCommentEdit().getParentsStack());

        jgen.writeObjectField("thread", value.asEventVideoCommentEdit().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_BOARD_POST_RESTORE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventBoardPostRestore().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventBoardPostRestore().getGroupId());

        jgen.writeObjectField("topic_id", value.asEventBoardPostRestore().getTopicId());

        jgen.writeObjectField("topic_owner_id", value.asEventBoardPostRestore().getTopicOwnerId());

        jgen.writeObjectField("id", value.asEventBoardPostRestore().getId());

        jgen.writeObjectField("from_id", value.asEventBoardPostRestore().getFromId());

        jgen.writeObjectField("date", value.asEventBoardPostRestore().getDate());

        jgen.writeObjectField("text", value.asEventBoardPostRestore().getText());

        jgen.writeObjectField("attachments", value.asEventBoardPostRestore().getAttachments());

        jgen.writeEndObject();
        break;

      case EVENT_POLL_VOTE_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPollVoteNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPollVoteNew().getGroupId());

        jgen.writeObjectField("owner_id", value.asEventPollVoteNew().getOwnerId());

        jgen.writeObjectField("poll_id", value.asEventPollVoteNew().getPollId());

        jgen.writeObjectField("option_id", value.asEventPollVoteNew().getOptionId());

        jgen.writeObjectField("user_id", value.asEventPollVoteNew().getUserId());

        jgen.writeEndObject();
        break;

      case EVENT_PHOTO_COMMENT_RESTORE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPhotoCommentRestore().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPhotoCommentRestore().getGroupId());

        jgen.writeObjectField("photo_id", value.asEventPhotoCommentRestore().getPhotoId());

        jgen.writeObjectField(
            "photo_owner_id", value.asEventPhotoCommentRestore().getPhotoOwnerId());

        jgen.writeObjectField("id", value.asEventPhotoCommentRestore().getId());

        jgen.writeObjectField("user_id", value.asEventPhotoCommentRestore().getUserId());

        jgen.writeObjectField("date", value.asEventPhotoCommentRestore().getDate());

        jgen.writeObjectField("text", value.asEventPhotoCommentRestore().getText());

        jgen.writeObjectField("reply_to_user", value.asEventPhotoCommentRestore().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventPhotoCommentRestore().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventPhotoCommentRestore().getAttachments());

        jgen.writeObjectField(
            "parents_stack", value.asEventPhotoCommentRestore().getParentsStack());

        jgen.writeObjectField("thread", value.asEventPhotoCommentRestore().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_VIDEO_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVideoNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVideoNew().getGroupId());

        jgen.writeObjectField("object", value.asEventVideoNew().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_GROUP_CHANGE_PHOTO:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventGroupChangePhoto().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventGroupChangePhoto().getGroupId());

        jgen.writeObjectField("user_id", value.asEventGroupChangePhoto().getUserId());

        jgen.writeObjectField("photo", value.asEventGroupChangePhoto().getPhoto());

        jgen.writeEndObject();
        break;

      case EVENT_MESSAGE_ALLOW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMessageAllow().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMessageAllow().getGroupId());

        jgen.writeObjectField("user_id", value.asEventMessageAllow().getUserId());

        jgen.writeObjectField("key", value.asEventMessageAllow().getKey());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_POST_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallPostNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallPostNew().getGroupId());

        jgen.writeObjectField("postponed_id", value.asEventWallPostNew().getPostponedId());

        jgen.writeObjectField("id", value.asEventWallPostNew().getId());

        jgen.writeObjectField("owner_id", value.asEventWallPostNew().getOwnerId());

        jgen.writeObjectField("from_id", value.asEventWallPostNew().getFromId());

        jgen.writeObjectField("created_by", value.asEventWallPostNew().getCreatedBy());

        jgen.writeObjectField("date", value.asEventWallPostNew().getDate());

        jgen.writeObjectField("text", value.asEventWallPostNew().getText());

        jgen.writeObjectField("reply_owner_id", value.asEventWallPostNew().getReplyOwnerId());

        jgen.writeObjectField("reply_post_id", value.asEventWallPostNew().getReplyPostId());

        jgen.writeObjectField("friends_only", value.asEventWallPostNew().getFriendsOnly());

        jgen.writeObjectField("comments", value.asEventWallPostNew().getComments());

        jgen.writeObjectField("likes", value.asEventWallPostNew().getLikes());

        jgen.writeObjectField("reposts", value.asEventWallPostNew().getReposts());

        jgen.writeObjectField("views", value.asEventWallPostNew().getViews());

        jgen.writeObjectField("post_type", value.asEventWallPostNew().getPostType());

        jgen.writeObjectField("post_source", value.asEventWallPostNew().getPostSource());

        jgen.writeObjectField("attachments", value.asEventWallPostNew().getAttachments());

        jgen.writeObjectField("geo", value.asEventWallPostNew().getGeo());

        jgen.writeObjectField("signer_id", value.asEventWallPostNew().getSignerId());

        jgen.writeObjectField("copy_history", value.asEventWallPostNew().getCopyHistory());

        jgen.writeObjectField("can_pin", value.asEventWallPostNew().getCanPin());

        jgen.writeObjectField("can_delete", value.asEventWallPostNew().getCanDelete());

        jgen.writeObjectField("can_edit", value.asEventWallPostNew().getCanEdit());

        jgen.writeObjectField("is_pinned", value.asEventWallPostNew().getIsPinned());

        jgen.writeObjectField("marked_as_ads", value.asEventWallPostNew().getMarkedAsAds());

        jgen.writeObjectField("is_favorite", value.asEventWallPostNew().getIsFavorite());

        jgen.writeEndObject();
        break;

      case EVENT_VK_PAY_TRANSACTION:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVkPayTransaction().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVkPayTransaction().getGroupId());

        jgen.writeObjectField("from_id", value.asEventVkPayTransaction().getFromId());

        jgen.writeObjectField("amount", value.asEventVkPayTransaction().getAmount());

        jgen.writeObjectField("description", value.asEventVkPayTransaction().getDescription());

        jgen.writeObjectField("date", value.asEventVkPayTransaction().getDate());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_REPLY_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallReplyEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallReplyEdit().getGroupId());

        jgen.writeObjectField("post_id", value.asEventWallReplyEdit().getPostId());

        jgen.writeObjectField("post_ownder_id", value.asEventWallReplyEdit().getPostOwnderId());

        jgen.writeObjectField("id", value.asEventWallReplyEdit().getId());

        jgen.writeObjectField("user_id", value.asEventWallReplyEdit().getUserId());

        jgen.writeObjectField("date", value.asEventWallReplyEdit().getDate());

        jgen.writeObjectField("text", value.asEventWallReplyEdit().getText());

        jgen.writeObjectField("reply_to_user", value.asEventWallReplyEdit().getReplyToUser());

        jgen.writeObjectField("reply_to_comment", value.asEventWallReplyEdit().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventWallReplyEdit().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventWallReplyEdit().getParentsStack());

        jgen.writeObjectField("thread", value.asEventWallReplyEdit().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_PHOTO_COMMENT_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPhotoCommentEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPhotoCommentEdit().getGroupId());

        jgen.writeObjectField("photo_id", value.asEventPhotoCommentEdit().getPhotoId());

        jgen.writeObjectField("photo_owner_id", value.asEventPhotoCommentEdit().getPhotoOwnerId());

        jgen.writeObjectField("id", value.asEventPhotoCommentEdit().getId());

        jgen.writeObjectField("user_id", value.asEventPhotoCommentEdit().getUserId());

        jgen.writeObjectField("date", value.asEventPhotoCommentEdit().getDate());

        jgen.writeObjectField("text", value.asEventPhotoCommentEdit().getText());

        jgen.writeObjectField("reply_to_user", value.asEventPhotoCommentEdit().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventPhotoCommentEdit().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventPhotoCommentEdit().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventPhotoCommentEdit().getParentsStack());

        jgen.writeObjectField("thread", value.asEventPhotoCommentEdit().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_MESSAGE_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMessageNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMessageNew().getGroupId());

        jgen.writeObjectField("object", value.asEventMessageNew().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_GROUP_LEAVE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventGroupLeave().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventGroupLeave().getGroupId());

        jgen.writeObjectField("user_id", value.asEventGroupLeave().getUserId());

        jgen.writeObjectField("self", value.asEventGroupLeave().getSelf());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_REPLY_DELETE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallReplyDelete().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallReplyDelete().getGroupId());

        jgen.writeObjectField("owner_id", value.asEventWallReplyDelete().getOwnerId());

        jgen.writeObjectField("id", value.asEventWallReplyDelete().getId());

        jgen.writeObjectField("deleter_id", value.asEventWallReplyDelete().getDeleterId());

        jgen.writeObjectField("post_id", value.asEventWallReplyDelete().getPostId());

        jgen.writeEndObject();
        break;

      case EVENT_GROUP_CHANGE_SETTINGS:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventGroupChangeSettings().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventGroupChangeSettings().getGroupId());

        jgen.writeObjectField("user_id", value.asEventGroupChangeSettings().getUserId());

        jgen.writeEndObject();
        break;

      case EVENT_USER_UNBLOCK:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventUserUnblock().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventUserUnblock().getGroupId());

        jgen.writeObjectField("admin_id", value.asEventUserUnblock().getAdminId());

        jgen.writeObjectField("user_id", value.asEventUserUnblock().getUserId());

        jgen.writeObjectField("by_end_date", value.asEventUserUnblock().getByEndDate());

        jgen.writeEndObject();
        break;

      case EVENT_GROUP_OFFICERS_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventGroupOfficersEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventGroupOfficersEdit().getGroupId());

        jgen.writeObjectField("admin_id", value.asEventGroupOfficersEdit().getAdminId());

        jgen.writeObjectField("user_id", value.asEventGroupOfficersEdit().getUserId());

        jgen.writeEndObject();
        break;

      case EVENT_USER_BLOCK:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventUserBlock().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventUserBlock().getGroupId());

        jgen.writeObjectField("admin_id", value.asEventUserBlock().getAdminId());

        jgen.writeObjectField("user_id", value.asEventUserBlock().getUserId());

        jgen.writeObjectField("unblock_date", value.asEventUserBlock().getUnblockDate());

        jgen.writeObjectField("comment", value.asEventUserBlock().getComment());

        jgen.writeEndObject();
        break;

      case EVENT_MARKET_COMMENT_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMarketCommentNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMarketCommentNew().getGroupId());

        jgen.writeObjectField(
            "market_owner_id", value.asEventMarketCommentNew().getMarketOwnerId());

        jgen.writeObjectField("item_id", value.asEventMarketCommentNew().getItemId());

        jgen.writeObjectField("id", value.asEventMarketCommentNew().getId());

        jgen.writeObjectField("user_id", value.asEventMarketCommentNew().getUserId());

        jgen.writeObjectField("date", value.asEventMarketCommentNew().getDate());

        jgen.writeObjectField("text", value.asEventMarketCommentNew().getText());

        jgen.writeObjectField("reply_to_user", value.asEventMarketCommentNew().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventMarketCommentNew().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventMarketCommentNew().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventMarketCommentNew().getParentsStack());

        jgen.writeObjectField("thread", value.asEventMarketCommentNew().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_AUDIO_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventAudioNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventAudioNew().getGroupId());

        jgen.writeObjectField("object", value.asEventAudioNew().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_PHOTO_COMMENT_DELETE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPhotoCommentDelete().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPhotoCommentDelete().getGroupId());

        jgen.writeObjectField("owner_id", value.asEventPhotoCommentDelete().getOwnerId());

        jgen.writeObjectField("id", value.asEventPhotoCommentDelete().getId());

        jgen.writeObjectField("user_id", value.asEventPhotoCommentDelete().getUserId());

        jgen.writeObjectField("deleter_id", value.asEventPhotoCommentDelete().getDeleterId());

        jgen.writeObjectField("photo_id", value.asEventPhotoCommentDelete().getPhotoId());

        jgen.writeEndObject();
        break;

      case EVENT_BOARD_POST_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventBoardPostEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventBoardPostEdit().getGroupId());

        jgen.writeObjectField("topic_id", value.asEventBoardPostEdit().getTopicId());

        jgen.writeObjectField("topic_owner_id", value.asEventBoardPostEdit().getTopicOwnerId());

        jgen.writeObjectField("id", value.asEventBoardPostEdit().getId());

        jgen.writeObjectField("from_id", value.asEventBoardPostEdit().getFromId());

        jgen.writeObjectField("date", value.asEventBoardPostEdit().getDate());

        jgen.writeObjectField("text", value.asEventBoardPostEdit().getText());

        jgen.writeObjectField("attachments", value.asEventBoardPostEdit().getAttachments());

        jgen.writeEndObject();
        break;

      case EVENT_GROUP_JOIN:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventGroupJoin().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventGroupJoin().getGroupId());

        jgen.writeObjectField("user_id", value.asEventGroupJoin().getUserId());

        jgen.writeEndObject();
        break;

      case EVENT_MESSAGE_DENY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMessageDeny().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMessageDeny().getGroupId());

        jgen.writeObjectField("user_id", value.asEventMessageDeny().getUserId());

        jgen.writeEndObject();
        break;

      case EVENT_MESSAGE_EDIT:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMessageEdit().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMessageEdit().getGroupId());

        jgen.writeObjectField("object", value.asEventMessageEdit().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_MARKET_COMMENT_DELETE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMarketCommentDelete().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMarketCommentDelete().getGroupId());

        jgen.writeObjectField("owner_id", value.asEventMarketCommentDelete().getOwnerId());

        jgen.writeObjectField("id", value.asEventMarketCommentDelete().getId());

        jgen.writeObjectField("user_id", value.asEventMarketCommentDelete().getUserId());

        jgen.writeObjectField("deleter_id", value.asEventMarketCommentDelete().getDeleterId());

        jgen.writeObjectField("item_id", value.asEventMarketCommentDelete().getItemId());

        jgen.writeEndObject();
        break;

      case EVENT_PHOTO_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventPhotoNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventPhotoNew().getGroupId());

        jgen.writeObjectField("object", value.asEventPhotoNew().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_VIDEO_COMMENT_RESTORE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventVideoCommentRestore().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventVideoCommentRestore().getGroupId());

        jgen.writeObjectField("video_id", value.asEventVideoCommentRestore().getVideo_id());

        jgen.writeObjectField(
            "video_owner_id", value.asEventVideoCommentRestore().getVideoOwnerId());

        jgen.writeObjectField("id", value.asEventVideoCommentRestore().getId());

        jgen.writeObjectField("user_id", value.asEventVideoCommentRestore().getUserId());

        jgen.writeObjectField("date", value.asEventVideoCommentRestore().getDate());

        jgen.writeObjectField("text", value.asEventVideoCommentRestore().getText());

        jgen.writeObjectField("reply_to_user", value.asEventVideoCommentRestore().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventVideoCommentRestore().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventVideoCommentRestore().getAttachments());

        jgen.writeObjectField(
            "parents_stack", value.asEventVideoCommentRestore().getParentsStack());

        jgen.writeObjectField("thread", value.asEventVideoCommentRestore().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_WALL_REPLY_RESTORE:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventWallReplyRestore().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventWallReplyRestore().getGroupId());

        jgen.writeObjectField("post_id", value.asEventWallReplyRestore().getPostId());

        jgen.writeObjectField("post_ownder_id", value.asEventWallReplyRestore().getPostOwnderId());

        jgen.writeObjectField("id", value.asEventWallReplyRestore().getId());

        jgen.writeObjectField("user_id", value.asEventWallReplyRestore().getUserId());

        jgen.writeObjectField("date", value.asEventWallReplyRestore().getDate());

        jgen.writeObjectField("text", value.asEventWallReplyRestore().getText());

        jgen.writeObjectField("reply_to_user", value.asEventWallReplyRestore().getReplyToUser());

        jgen.writeObjectField(
            "reply_to_comment", value.asEventWallReplyRestore().getReplyToComment());

        jgen.writeObjectField("attachments", value.asEventWallReplyRestore().getAttachments());

        jgen.writeObjectField("parents_stack", value.asEventWallReplyRestore().getParentsStack());

        jgen.writeObjectField("thread", value.asEventWallReplyRestore().getThread());

        jgen.writeEndObject();
        break;

      case EVENT_MESSAGE_REPLY:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventMessageReply().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventMessageReply().getGroupId());

        jgen.writeObjectField("object", value.asEventMessageReply().getObject());

        jgen.writeEndObject();
        break;

      case EVENT_BOARD_POST_NEW:
        jgen.writeStartObject();
        jgen.writeStringField("type", value.asEventBoardPostNew().getDiscriminator());

        jgen.writeObjectField("group_id", value.asEventBoardPostNew().getGroupId());

        jgen.writeObjectField("topic_id", value.asEventBoardPostNew().getTopicId());

        jgen.writeObjectField("topic_owner_id", value.asEventBoardPostNew().getTopicOwnerId());

        jgen.writeObjectField("id", value.asEventBoardPostNew().getId());

        jgen.writeObjectField("from_id", value.asEventBoardPostNew().getFromId());

        jgen.writeObjectField("date", value.asEventBoardPostNew().getDate());

        jgen.writeObjectField("text", value.asEventBoardPostNew().getText());

        jgen.writeObjectField("attachments", value.asEventBoardPostNew().getAttachments());

        jgen.writeEndObject();
        break;

      default:
        // TODO: unknown discriminator
    }
  }
}
