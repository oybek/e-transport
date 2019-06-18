package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Video {

  private int id;

  private int ownerId;

  private String title;

  private String description;

  private int duration;

  private String photo130;

  private String photo320;

  private String photo640;

  private String photo800;

  private String photo1280;

  private String firstFrame130;

  private String firstFrame320;

  private String firstFrame640;

  private String firstFrame800;

  private String firstFrame1280;

  private long date;

  private long addingDate;

  private int views;

  private int comments;

  private String player;

  private String platform;

  private Integer canEdit;

  private int canAdd;

  private Integer isPrivate;

  private Integer processing;

  private Integer live;

  private String upcoming;

  private boolean isFavorite;

  public Video() {}

  public Video(
      int id,
      int ownerId,
      String title,
      String description,
      int duration,
      String photo130,
      String photo320,
      String photo640,
      String photo800,
      String photo1280,
      String firstFrame130,
      String firstFrame320,
      String firstFrame640,
      String firstFrame800,
      String firstFrame1280,
      long date,
      long addingDate,
      int views,
      int comments,
      String player,
      String platform,
      Integer canEdit,
      int canAdd,
      Integer isPrivate,
      Integer processing,
      Integer live,
      String upcoming,
      boolean isFavorite) {
    this.id = id;
    this.ownerId = ownerId;
    this.title = title;
    this.description = description;
    this.duration = duration;
    this.photo130 = photo130;
    this.photo320 = photo320;
    this.photo640 = photo640;
    this.photo800 = photo800;
    this.photo1280 = photo1280;
    this.firstFrame130 = firstFrame130;
    this.firstFrame320 = firstFrame320;
    this.firstFrame640 = firstFrame640;
    this.firstFrame800 = firstFrame800;
    this.firstFrame1280 = firstFrame1280;
    this.date = date;
    this.addingDate = addingDate;
    this.views = views;
    this.comments = comments;
    this.player = player;
    this.platform = platform;
    this.canEdit = canEdit;
    this.canAdd = canAdd;
    this.isPrivate = isPrivate;
    this.processing = processing;
    this.live = live;
    this.upcoming = upcoming;
    this.isFavorite = isFavorite;
  }

  public int getId() {
    return this.id;
  }

  public Video setId(int v) {
    this.id = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public Video setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public String getTitle() {
    return this.title;
  }

  public Video setTitle(String v) {
    this.title = v;
    return this;
  }

  public String getDescription() {
    return this.description;
  }

  public Video setDescription(String v) {
    this.description = v;
    return this;
  }

  public int getDuration() {
    return this.duration;
  }

  public Video setDuration(int v) {
    this.duration = v;
    return this;
  }

  public String getPhoto130() {
    return this.photo130;
  }

  public Video setPhoto130(String v) {
    this.photo130 = v;
    return this;
  }

  public String getPhoto320() {
    return this.photo320;
  }

  public Video setPhoto320(String v) {
    this.photo320 = v;
    return this;
  }

  public String getPhoto640() {
    return this.photo640;
  }

  public Video setPhoto640(String v) {
    this.photo640 = v;
    return this;
  }

  public String getPhoto800() {
    return this.photo800;
  }

  public Video setPhoto800(String v) {
    this.photo800 = v;
    return this;
  }

  public String getPhoto1280() {
    return this.photo1280;
  }

  public Video setPhoto1280(String v) {
    this.photo1280 = v;
    return this;
  }

  public String getFirstFrame130() {
    return this.firstFrame130;
  }

  public Video setFirstFrame130(String v) {
    this.firstFrame130 = v;
    return this;
  }

  public String getFirstFrame320() {
    return this.firstFrame320;
  }

  public Video setFirstFrame320(String v) {
    this.firstFrame320 = v;
    return this;
  }

  public String getFirstFrame640() {
    return this.firstFrame640;
  }

  public Video setFirstFrame640(String v) {
    this.firstFrame640 = v;
    return this;
  }

  public String getFirstFrame800() {
    return this.firstFrame800;
  }

  public Video setFirstFrame800(String v) {
    this.firstFrame800 = v;
    return this;
  }

  public String getFirstFrame1280() {
    return this.firstFrame1280;
  }

  public Video setFirstFrame1280(String v) {
    this.firstFrame1280 = v;
    return this;
  }

  public long getDate() {
    return this.date;
  }

  public Video setDate(long v) {
    this.date = v;
    return this;
  }

  public long getAddingDate() {
    return this.addingDate;
  }

  public Video setAddingDate(long v) {
    this.addingDate = v;
    return this;
  }

  public int getViews() {
    return this.views;
  }

  public Video setViews(int v) {
    this.views = v;
    return this;
  }

  public int getComments() {
    return this.comments;
  }

  public Video setComments(int v) {
    this.comments = v;
    return this;
  }

  public String getPlayer() {
    return this.player;
  }

  public Video setPlayer(String v) {
    this.player = v;
    return this;
  }

  public String getPlatform() {
    return this.platform;
  }

  public Video setPlatform(String v) {
    this.platform = v;
    return this;
  }

  public Optional<Integer> getCanEdit() {
    return Optional.ofNullable(canEdit);
  }

  public Video setCanEdit(Integer v) {
    this.canEdit = v;
    return this;
  }

  public int getCanAdd() {
    return this.canAdd;
  }

  public Video setCanAdd(int v) {
    this.canAdd = v;
    return this;
  }

  public Optional<Integer> getIsPrivate() {
    return Optional.ofNullable(isPrivate);
  }

  public Video setIsPrivate(Integer v) {
    this.isPrivate = v;
    return this;
  }

  public Optional<Integer> getProcessing() {
    return Optional.ofNullable(processing);
  }

  public Video setProcessing(Integer v) {
    this.processing = v;
    return this;
  }

  public Optional<Integer> getLive() {
    return Optional.ofNullable(live);
  }

  public Video setLive(Integer v) {
    this.live = v;
    return this;
  }

  public String getUpcoming() {
    return this.upcoming;
  }

  public Video setUpcoming(String v) {
    this.upcoming = v;
    return this;
  }

  public boolean getIsFavorite() {
    return this.isFavorite;
  }

  public Video setIsFavorite(boolean v) {
    this.isFavorite = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Video)) return false;

    Video that = (Video) thatObj;

    return this.id == that.id
        && this.ownerId == that.ownerId
        && this.title.equals(that.title)
        && this.description.equals(that.description)
        && this.duration == that.duration
        && this.photo130.equals(that.photo130)
        && this.photo320.equals(that.photo320)
        && this.photo640.equals(that.photo640)
        && this.photo800.equals(that.photo800)
        && this.photo1280.equals(that.photo1280)
        && this.firstFrame130.equals(that.firstFrame130)
        && this.firstFrame320.equals(that.firstFrame320)
        && this.firstFrame640.equals(that.firstFrame640)
        && this.firstFrame800.equals(that.firstFrame800)
        && this.firstFrame1280.equals(that.firstFrame1280)
        && this.date == that.date
        && this.addingDate == that.addingDate
        && this.views == that.views
        && this.comments == that.comments
        && this.player.equals(that.player)
        && this.platform.equals(that.platform)
        && this.canEdit.equals(that.canEdit)
        && this.canAdd == that.canAdd
        && this.isPrivate.equals(that.isPrivate)
        && this.processing.equals(that.processing)
        && this.live.equals(that.live)
        && this.upcoming.equals(that.upcoming)
        && this.isFavorite == that.isFavorite;
  }

  @Override
  public String toString() {
    return "Video{"
        + "id="
        + this.id
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "title="
        + '\''
        + this.title
        + '\''
        + ','
        + "description="
        + '\''
        + this.description
        + '\''
        + ','
        + "duration="
        + this.duration
        + ','
        + "photo130="
        + '\''
        + this.photo130
        + '\''
        + ','
        + "photo320="
        + '\''
        + this.photo320
        + '\''
        + ','
        + "photo640="
        + '\''
        + this.photo640
        + '\''
        + ','
        + "photo800="
        + '\''
        + this.photo800
        + '\''
        + ','
        + "photo1280="
        + '\''
        + this.photo1280
        + '\''
        + ','
        + "firstFrame130="
        + '\''
        + this.firstFrame130
        + '\''
        + ','
        + "firstFrame320="
        + '\''
        + this.firstFrame320
        + '\''
        + ','
        + "firstFrame640="
        + '\''
        + this.firstFrame640
        + '\''
        + ','
        + "firstFrame800="
        + '\''
        + this.firstFrame800
        + '\''
        + ','
        + "firstFrame1280="
        + '\''
        + this.firstFrame1280
        + '\''
        + ','
        + "date="
        + this.date
        + ','
        + "addingDate="
        + this.addingDate
        + ','
        + "views="
        + this.views
        + ','
        + "comments="
        + this.comments
        + ','
        + "player="
        + '\''
        + this.player
        + '\''
        + ','
        + "platform="
        + '\''
        + this.platform
        + '\''
        + ','
        + "canEdit="
        + this.canEdit
        + ','
        + "canAdd="
        + this.canAdd
        + ','
        + "isPrivate="
        + this.isPrivate
        + ','
        + "processing="
        + this.processing
        + ','
        + "live="
        + this.live
        + ','
        + "upcoming="
        + '\''
        + this.upcoming
        + '\''
        + ','
        + "isFavorite="
        + this.isFavorite
        + '}';
  }
}
