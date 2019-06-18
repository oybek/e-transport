package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.oybek.lib.vk.deserializators.DataTypeDeserializer;
import io.github.oybek.lib.vk.serializators.DataTypeSerializer;

@JsonSerialize(using = DataTypeSerializer.class)
@JsonDeserialize(using = DataTypeDeserializer.class)
public interface DataType {

  enum DataTypes {
    PROFILE_ACTIVITY,
    PROFILE_PHOTO,
    COMMENTS,
    LIKE,
    POLL
  }

  DataTypes what();

  boolean isProfileActivity();

  ProfileActivity asProfileActivity();

  boolean isProfilePhoto();

  ProfilePhoto asProfilePhoto();

  boolean isComments();

  Comments asComments();

  boolean isLike();

  Like asLike();

  boolean isPoll();

  Poll asPoll();
}
