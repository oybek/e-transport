package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.EventDeserializer;
import io.github.oybek.lib.vk.serializators.EventSerializer;

@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public interface Event {

  enum Events {
    EVENT_MESSAGE_NEW,
    EVENT_MESSAGE_REPLY,
    EVENT_MESSAGE_EDIT,
    EVENT_MESSAGE_ALLOW,
    EVENT_MESSAGE_DENY,
    EVENT_PHOTO_NEW,
    EVENT_PHOTO_COMMENT_NEW,
    EVENT_PHOTO_COMMENT_EDIT,
    EVENT_PHOTO_COMMENT_RESTORE,
    EVENT_PHOTO_COMMENT_DELETE,
    EVENT_AUDIO_NEW,
    EVENT_VIDEO_NEW,
    EVENT_VIDEO_COMMENT_NEW,
    EVENT_VIDEO_COMMENT_EDIT,
    EVENT_VIDEO_COMMENT_RESTORE,
    EVENT_VIDEO_COMMENT_DELETE,
    EVENT_WALL_POST_NEW,
    EVENT_WALL_REPOST,
    EVENT_WALL_REPLY_NEW,
    EVENT_WALL_REPLY_EDIT,
    EVENT_WALL_REPLY_RESTORE,
    EVENT_WALL_REPLY_DELETE,
    EVENT_BOARD_POST_NEW,
    EVENT_BOARD_POST_EDIT,
    EVENT_BOARD_POST_RESTORE,
    EVENT_BOARD_POST_DELETE,
    EVENT_MARKET_COMMENT_NEW,
    EVENT_MARKET_COMMENT_EDIT,
    EVENT_MARKET_COMMENT_RESTORE,
    EVENT_MARKET_COMMENT_DELETE,
    EVENT_GROUP_LEAVE,
    EVENT_GROUP_JOIN,
    EVENT_USER_BLOCK,
    EVENT_USER_UNBLOCK,
    EVENT_POLL_VOTE_NEW,
    EVENT_GROUP_OFFICERS_EDIT,
    EVENT_GROUP_CHANGE_SETTINGS,
    EVENT_GROUP_CHANGE_PHOTO,
    EVENT_VK_PAY_TRANSACTION
  }

  Events what();

  String getDiscriminator();

  int getGroupId();

  Event setGroupId(int v);

  boolean isEventMessageNew();

  EventMessageNew asEventMessageNew();

  boolean isEventMessageReply();

  EventMessageReply asEventMessageReply();

  boolean isEventMessageEdit();

  EventMessageEdit asEventMessageEdit();

  boolean isEventMessageAllow();

  EventMessageAllow asEventMessageAllow();

  boolean isEventMessageDeny();

  EventMessageDeny asEventMessageDeny();

  boolean isEventPhotoNew();

  EventPhotoNew asEventPhotoNew();

  boolean isEventPhotoCommentNew();

  EventPhotoCommentNew asEventPhotoCommentNew();

  boolean isEventPhotoCommentEdit();

  EventPhotoCommentEdit asEventPhotoCommentEdit();

  boolean isEventPhotoCommentRestore();

  EventPhotoCommentRestore asEventPhotoCommentRestore();

  boolean isEventPhotoCommentDelete();

  EventPhotoCommentDelete asEventPhotoCommentDelete();

  boolean isEventAudioNew();

  EventAudioNew asEventAudioNew();

  boolean isEventVideoNew();

  EventVideoNew asEventVideoNew();

  boolean isEventVideoCommentNew();

  EventVideoCommentNew asEventVideoCommentNew();

  boolean isEventVideoCommentEdit();

  EventVideoCommentEdit asEventVideoCommentEdit();

  boolean isEventVideoCommentRestore();

  EventVideoCommentRestore asEventVideoCommentRestore();

  boolean isEventVideoCommentDelete();

  EventVideoCommentDelete asEventVideoCommentDelete();

  boolean isEventWallPostNew();

  EventWallPostNew asEventWallPostNew();

  boolean isEventWallRepost();

  EventWallRepost asEventWallRepost();

  boolean isEventWallReplyNew();

  EventWallReplyNew asEventWallReplyNew();

  boolean isEventWallReplyEdit();

  EventWallReplyEdit asEventWallReplyEdit();

  boolean isEventWallReplyRestore();

  EventWallReplyRestore asEventWallReplyRestore();

  boolean isEventWallReplyDelete();

  EventWallReplyDelete asEventWallReplyDelete();

  boolean isEventBoardPostNew();

  EventBoardPostNew asEventBoardPostNew();

  boolean isEventBoardPostEdit();

  EventBoardPostEdit asEventBoardPostEdit();

  boolean isEventBoardPostRestore();

  EventBoardPostRestore asEventBoardPostRestore();

  boolean isEventBoardPostDelete();

  EventBoardPostDelete asEventBoardPostDelete();

  boolean isEventMarketCommentNew();

  EventMarketCommentNew asEventMarketCommentNew();

  boolean isEventMarketCommentEdit();

  EventMarketCommentEdit asEventMarketCommentEdit();

  boolean isEventMarketCommentRestore();

  EventMarketCommentRestore asEventMarketCommentRestore();

  boolean isEventMarketCommentDelete();

  EventMarketCommentDelete asEventMarketCommentDelete();

  boolean isEventGroupLeave();

  EventGroupLeave asEventGroupLeave();

  boolean isEventGroupJoin();

  EventGroupJoin asEventGroupJoin();

  boolean isEventUserBlock();

  EventUserBlock asEventUserBlock();

  boolean isEventUserUnblock();

  EventUserUnblock asEventUserUnblock();

  boolean isEventPollVoteNew();

  EventPollVoteNew asEventPollVoteNew();

  boolean isEventGroupOfficersEdit();

  EventGroupOfficersEdit asEventGroupOfficersEdit();

  boolean isEventGroupChangeSettings();

  EventGroupChangeSettings asEventGroupChangeSettings();

  boolean isEventGroupChangePhoto();

  EventGroupChangePhoto asEventGroupChangePhoto();

  boolean isEventVkPayTransaction();

  EventVkPayTransaction asEventVkPayTransaction();
}
