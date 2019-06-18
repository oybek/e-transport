package io.github.oybek.lib.vk.deserializators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.oybek.lib.vk.*;
;

import java.io.IOException;
import java.util.List;

public class EventDeserializer extends StdDeserializer<Event> {

  ObjectMapper objectMapper = new ObjectMapper();

  public EventDeserializer() {
    this(null);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public EventDeserializer(Class<Event> t) {
    super(t);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public Event deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);

    String type = node.get("type").asText();

    switch (type) {
      case "photo_comment_new":
        return new EventPhotoCommentNew(
            node.get("group_id").asInt(),
            node.get("photo_id").asInt(),
            node.get("photo_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "board_post_delete":
        return new EventBoardPostDelete(
            node.get("group_id").asInt(),
            node.get("topic_id").asInt(),
            node.get("topic_owner_id").asInt(),
            node.get("id").asInt());

      case "video_comment_new":
        return new EventVideoCommentNew(
            node.get("group_id").asInt(),
            node.get("video_id").asInt(),
            node.get("video_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "market_comment_restore":
        return new EventMarketCommentRestore(
            node.get("group_id").asInt(),
            node.get("market_owner_id").asInt(),
            node.get("item_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "video_comment_delete":
        return new EventVideoCommentDelete(
            node.get("group_id").asInt(),
            node.get("owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("deleter_id").asInt(),
            node.get("video_id").asInt());

      case "wall_repost":
        return new EventWallRepost(
            node.get("group_id").asInt(),
            node.get("postponed_id").asInt(),
            node.get("id").asInt(),
            node.get("owner_id").asInt(),
            node.get("from_id").asInt(),
            node.get("created_by").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_owner_id").asInt(),
            node.get("reply_post_id").asInt(),
            objectMapper.readValue(
                node.get("friends_only").traverse(jp.getCodec()), new TypeReference<Integer>() {}),
            objectMapper.readValue(
                node.get("comments").traverse(jp.getCodec()), WallPostCommentsAnon.class),
            objectMapper.readValue(
                node.get("likes").traverse(jp.getCodec()), WallPostLikesAnon.class),
            objectMapper.readValue(
                node.get("reposts").traverse(jp.getCodec()), WallPostReportsAnon.class),
            objectMapper.readValue(
                node.get("views").traverse(jp.getCodec()), WallPostViewsAnon.class),
            objectMapper.readValue(node.get("post_type").traverse(jp.getCodec()), PostType.class),
            objectMapper.readValue(
                node.get("post_source").traverse(jp.getCodec()), PostSource.class),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(node.get("geo").traverse(jp.getCodec()), Geo.class),
            node.get("signer_id").asInt(),
            objectMapper.readValue(
                node.get("copy_history").traverse(jp.getCodec()),
                new TypeReference<List<WallPost>>() {}),
            node.get("can_pin").asBoolean(),
            node.get("can_delete").asBoolean(),
            node.get("can_edit").asBoolean(),
            node.get("is_pinned").asBoolean(),
            node.get("marked_as_ads").asBoolean(),
            node.get("is_favorite").asBoolean());

      case "wall_reply_new":
        return new EventWallReplyNew(
            node.get("group_id").asInt(),
            node.get("post_id").asInt(),
            node.get("post_ownder_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "market_comment_edit":
        return new EventMarketCommentEdit(
            node.get("group_id").asInt(),
            node.get("market_owner_id").asInt(),
            node.get("item_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "video_comment_edit":
        return new EventVideoCommentEdit(
            node.get("group_id").asInt(),
            node.get("video_id").asInt(),
            node.get("video_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "board_post_restore":
        return new EventBoardPostRestore(
            node.get("group_id").asInt(),
            node.get("topic_id").asInt(),
            node.get("topic_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("from_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}));

      case "poll_vote_new":
        return new EventPollVoteNew(
            node.get("group_id").asInt(),
            node.get("owner_id").asInt(),
            node.get("poll_id").asInt(),
            node.get("option_id").asInt(),
            node.get("user_id").asInt());

      case "photo_comment_restore":
        return new EventPhotoCommentRestore(
            node.get("group_id").asInt(),
            node.get("photo_id").asInt(),
            node.get("photo_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "video_new":
        return new EventVideoNew(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Video.class));

      case "group_change_photo":
        return new EventGroupChangePhoto(
            node.get("group_id").asInt(),
            node.get("user_id").asInt(),
            objectMapper.readValue(node.get("photo").traverse(jp.getCodec()), Photo.class));

      case "message_allow":
        return new EventMessageAllow(
            node.get("group_id").asInt(),
            node.get("user_id").asInt(),
            objectMapper.readValue(node.get("key").traverse(jp.getCodec()), String.class));

      case "wall_post_new":
        return new EventWallPostNew(
            node.get("group_id").asInt(),
            node.get("postponed_id").asInt(),
            node.get("id").asInt(),
            node.get("owner_id").asInt(),
            node.get("from_id").asInt(),
            node.get("created_by").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_owner_id").asInt(),
            node.get("reply_post_id").asInt(),
            objectMapper.readValue(
                node.get("friends_only").traverse(jp.getCodec()), new TypeReference<Integer>() {}),
            objectMapper.readValue(
                node.get("comments").traverse(jp.getCodec()), WallPostCommentsAnon.class),
            objectMapper.readValue(
                node.get("likes").traverse(jp.getCodec()), WallPostLikesAnon.class),
            objectMapper.readValue(
                node.get("reposts").traverse(jp.getCodec()), WallPostReportsAnon.class),
            objectMapper.readValue(
                node.get("views").traverse(jp.getCodec()), WallPostViewsAnon.class),
            objectMapper.readValue(node.get("post_type").traverse(jp.getCodec()), PostType.class),
            objectMapper.readValue(
                node.get("post_source").traverse(jp.getCodec()), PostSource.class),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(node.get("geo").traverse(jp.getCodec()), Geo.class),
            node.get("signer_id").asInt(),
            objectMapper.readValue(
                node.get("copy_history").traverse(jp.getCodec()),
                new TypeReference<List<WallPost>>() {}),
            node.get("can_pin").asBoolean(),
            node.get("can_delete").asBoolean(),
            node.get("can_edit").asBoolean(),
            node.get("is_pinned").asBoolean(),
            node.get("marked_as_ads").asBoolean(),
            node.get("is_favorite").asBoolean());

      case "vkpay_transaction":
        return new EventVkPayTransaction(
            node.get("group_id").asInt(),
            node.get("from_id").asInt(),
            node.get("amount").asInt(),
            objectMapper.readValue(node.get("description").traverse(jp.getCodec()), String.class),
            node.get("date").asLong());

      case "wall_reply_edit":
        return new EventWallReplyEdit(
            node.get("group_id").asInt(),
            node.get("post_id").asInt(),
            node.get("post_ownder_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "photo_comment_edit":
        return new EventPhotoCommentEdit(
            node.get("group_id").asInt(),
            node.get("photo_id").asInt(),
            node.get("photo_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "message_new":
        return new EventMessageNew(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Message.class));

      case "group_leave":
        return new EventGroupLeave(
            node.get("group_id").asInt(),
            node.get("user_id").asInt(),
            node.get("self").asBoolean());

      case "wall_reply_delete":
        return new EventWallReplyDelete(
            node.get("group_id").asInt(),
            node.get("owner_id").asInt(),
            node.get("id").asInt(),
            node.get("deleter_id").asInt(),
            node.get("post_id").asInt());

      case "group_change_settings":
        return new EventGroupChangeSettings(
            node.get("group_id").asInt(), node.get("user_id").asInt());

      case "user_unblock":
        return new EventUserUnblock(
            node.get("group_id").asInt(),
            node.get("admin_id").asInt(),
            node.get("user_id").asInt(),
            node.get("by_end_date").asBoolean());

      case "group_officers_edit":
        return new EventGroupOfficersEdit(
            node.get("group_id").asInt(),
            node.get("admin_id").asInt(),
            node.get("user_id").asInt());

      case "user_block":
        return new EventUserBlock(
            node.get("group_id").asInt(),
            node.get("admin_id").asInt(),
            node.get("user_id").asInt(),
            node.get("unblock_date").asLong(),
            objectMapper.readValue(node.get("comment").traverse(jp.getCodec()), String.class));

      case "market_comment_new":
        return new EventMarketCommentNew(
            node.get("group_id").asInt(),
            node.get("market_owner_id").asInt(),
            node.get("item_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "audio_new":
        return new EventAudioNew(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Audio.class));

      case "photo_comment_delete":
        return new EventPhotoCommentDelete(
            node.get("group_id").asInt(),
            node.get("owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("deleter_id").asInt(),
            node.get("photo_id").asInt());

      case "board_post_edit":
        return new EventBoardPostEdit(
            node.get("group_id").asInt(),
            node.get("topic_id").asInt(),
            node.get("topic_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("from_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}));

      case "group_join":
        return new EventGroupJoin(node.get("group_id").asInt(), node.get("user_id").asInt());

      case "message_deny":
        return new EventMessageDeny(node.get("group_id").asInt(), node.get("user_id").asInt());

      case "message_edit":
        return new EventMessageEdit(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Message.class));

      case "market_comment_delete":
        return new EventMarketCommentDelete(
            node.get("group_id").asInt(),
            node.get("owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("deleter_id").asInt(),
            node.get("item_id").asInt());

      case "photo_new":
        return new EventPhotoNew(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Photo.class));

      case "video_comment_restore":
        return new EventVideoCommentRestore(
            node.get("group_id").asInt(),
            node.get("video_id").asInt(),
            node.get("video_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "wall_reply_restore":
        return new EventWallReplyRestore(
            node.get("group_id").asInt(),
            node.get("post_id").asInt(),
            node.get("post_ownder_id").asInt(),
            node.get("id").asInt(),
            node.get("user_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            node.get("reply_to_user").asInt(),
            node.get("reply_to_comment").asInt(),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}),
            objectMapper.readValue(
                node.get("parents_stack").traverse(jp.getCodec()),
                new TypeReference<List<Integer>>() {}),
            objectMapper.readValue(
                node.get("thread").traverse(jp.getCodec()), CommentsThread.class));

      case "message_reply":
        return new EventMessageReply(
            node.get("group_id").asInt(),
            objectMapper.readValue(node.get("object").traverse(jp.getCodec()), Message.class));

      case "board_post_new":
        return new EventBoardPostNew(
            node.get("group_id").asInt(),
            node.get("topic_id").asInt(),
            node.get("topic_owner_id").asInt(),
            node.get("id").asInt(),
            node.get("from_id").asInt(),
            node.get("date").asLong(),
            objectMapper.readValue(node.get("text").traverse(jp.getCodec()), String.class),
            objectMapper.readValue(
                node.get("attachments").traverse(jp.getCodec()),
                new TypeReference<List<Attachment>>() {}));

      default:
        return null;
    }
  }
}
