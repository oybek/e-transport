package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Photo {

  private int id;

  private int albumId;

  private int ownerId;

  private int userId;

  private String text;

  private long date;

  private List<SizedPhoto> sizes;

  private Integer width;

  private Integer height;

  public Photo() {}

  public Photo(
      int id,
      int albumId,
      int ownerId,
      int userId,
      String text,
      long date,
      List<SizedPhoto> sizes,
      Integer width,
      Integer height) {
    this.id = id;
    this.albumId = albumId;
    this.ownerId = ownerId;
    this.userId = userId;
    this.text = text;
    this.date = date;
    this.sizes = sizes;
    this.width = width;
    this.height = height;
  }

  public int getId() {
    return this.id;
  }

  public Photo setId(int v) {
    this.id = v;
    return this;
  }

  public int getAlbumId() {
    return this.albumId;
  }

  public Photo setAlbumId(int v) {
    this.albumId = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public Photo setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public int getUserId() {
    return this.userId;
  }

  public Photo setUserId(int v) {
    this.userId = v;
    return this;
  }

  public String getText() {
    return this.text;
  }

  public Photo setText(String v) {
    this.text = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public Photo setDate(long v) {
    this.date = v;
    return this;
  }

  public List<SizedPhoto> getSizes() {
    return this.sizes;
  }

  public Photo setSizes(List<SizedPhoto> v) {
    this.sizes = v;
    return this;
  }

  public Optional<Integer> getWidth() {
    return Optional.ofNullable(width);
  }

  public Photo setWidth(Integer v) {
    this.width = v;
    return this;
  }

  public Optional<Integer> getHeight() {
    return Optional.ofNullable(height);
  }

  public Photo setHeight(Integer v) {
    this.height = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Photo)) return false;

    Photo that = (Photo) thatObj;

    return this.id == that.id
        && this.albumId == that.albumId
        && this.ownerId == that.ownerId
        && this.userId == that.userId
        && this.text.equals(that.text)
        && this.date == that.date
        && this.sizes.equals(that.sizes)
        && this.width.equals(that.width)
        && this.height.equals(that.height);
  }

  @Override
  public String toString() {
    return "Photo{"
        + "id="
        + this.id
        + ','
        + "albumId="
        + this.albumId
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "userId="
        + this.userId
        + ','
        + "text="
        + '\''
        + this.text
        + '\''
        + ','
        + "date="
        + this.date
        + ','
        + "sizes="
        + this.sizes
        + ','
        + "width="
        + this.width
        + ','
        + "height="
        + this.height
        + '}';
  }
}
