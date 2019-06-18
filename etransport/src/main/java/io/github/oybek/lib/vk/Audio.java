package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Audio {

  private int id;

  private int ownerId;

  private String artist;

  private String title;

  private int duration;

  private String url;

  private int lyricsId;

  private int albumId;

  private int genreId;

  private int date;

  private Integer noSearch;

  private Integer isHq;

  public Audio() {}

  public Audio(
      int id,
      int ownerId,
      String artist,
      String title,
      int duration,
      String url,
      int lyricsId,
      int albumId,
      int genreId,
      int date,
      Integer noSearch,
      Integer isHq) {
    this.id = id;
    this.ownerId = ownerId;
    this.artist = artist;
    this.title = title;
    this.duration = duration;
    this.url = url;
    this.lyricsId = lyricsId;
    this.albumId = albumId;
    this.genreId = genreId;
    this.date = date;
    this.noSearch = noSearch;
    this.isHq = isHq;
  }

  public int getId() {
    return this.id;
  }

  public Audio setId(int v) {
    this.id = v;
    return this;
  }

  public int getOwnerId() {
    return this.ownerId;
  }

  public Audio setOwnerId(int v) {
    this.ownerId = v;
    return this;
  }

  public String getArtist() {
    return this.artist;
  }

  public Audio setArtist(String v) {
    this.artist = v;
    return this;
  }

  public String getTitle() {
    return this.title;
  }

  public Audio setTitle(String v) {
    this.title = v;
    return this;
  }

  public int getDuration() {
    return this.duration;
  }

  public Audio setDuration(int v) {
    this.duration = v;
    return this;
  }

  public String getUrl() {
    return this.url;
  }

  public Audio setUrl(String v) {
    this.url = v;
    return this;
  }

  public int getLyricsId() {
    return this.lyricsId;
  }

  public Audio setLyricsId(int v) {
    this.lyricsId = v;
    return this;
  }

  public int getAlbumId() {
    return this.albumId;
  }

  public Audio setAlbumId(int v) {
    this.albumId = v;
    return this;
  }

  public int getGenreId() {
    return this.genreId;
  }

  public Audio setGenreId(int v) {
    this.genreId = v;
    return this;
  }

  public int getDate() {
    return this.date;
  }

  public Audio setDate(int v) {
    this.date = v;
    return this;
  }

  public Optional<Integer> getNoSearch() {
    return Optional.ofNullable(noSearch);
  }

  public Audio setNoSearch(Integer v) {
    this.noSearch = v;
    return this;
  }

  public Optional<Integer> getIsHq() {
    return Optional.ofNullable(isHq);
  }

  public Audio setIsHq(Integer v) {
    this.isHq = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Audio)) return false;

    Audio that = (Audio) thatObj;

    return this.id == that.id
        && this.ownerId == that.ownerId
        && this.artist.equals(that.artist)
        && this.title.equals(that.title)
        && this.duration == that.duration
        && this.url.equals(that.url)
        && this.lyricsId == that.lyricsId
        && this.albumId == that.albumId
        && this.genreId == that.genreId
        && this.date == that.date
        && this.noSearch.equals(that.noSearch)
        && this.isHq.equals(that.isHq);
  }

  @Override
  public String toString() {
    return "Audio{"
        + "id="
        + this.id
        + ','
        + "ownerId="
        + this.ownerId
        + ','
        + "artist="
        + '\''
        + this.artist
        + '\''
        + ','
        + "title="
        + '\''
        + this.title
        + '\''
        + ','
        + "duration="
        + this.duration
        + ','
        + "url="
        + '\''
        + this.url
        + '\''
        + ','
        + "lyricsId="
        + this.lyricsId
        + ','
        + "albumId="
        + this.albumId
        + ','
        + "genreId="
        + this.genreId
        + ','
        + "date="
        + this.date
        + ','
        + "noSearch="
        + this.noSearch
        + ','
        + "isHq="
        + this.isHq
        + '}';
  }
}
