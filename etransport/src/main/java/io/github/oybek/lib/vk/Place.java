package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Place {

  private int id;

  private String title;

  private double latitude;

  private double longitude;

  private int created;

  private String icon;

  private String country;

  private String city;

  public Place() {}

  public Place(
      int id,
      String title,
      double latitude,
      double longitude,
      int created,
      String icon,
      String country,
      String city) {
    this.id = id;
    this.title = title;
    this.latitude = latitude;
    this.longitude = longitude;
    this.created = created;
    this.icon = icon;
    this.country = country;
    this.city = city;
  }

  public int getId() {
    return this.id;
  }

  public Place setId(int v) {
    this.id = v;
    return this;
  }

  public String getTitle() {
    return this.title;
  }

  public Place setTitle(String v) {
    this.title = v;
    return this;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public Place setLatitude(double v) {
    this.latitude = v;
    return this;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public Place setLongitude(double v) {
    this.longitude = v;
    return this;
  }

  public int getCreated() {
    return this.created;
  }

  public Place setCreated(int v) {
    this.created = v;
    return this;
  }

  public String getIcon() {
    return this.icon;
  }

  public Place setIcon(String v) {
    this.icon = v;
    return this;
  }

  public String getCountry() {
    return this.country;
  }

  public Place setCountry(String v) {
    this.country = v;
    return this;
  }

  public String getCity() {
    return this.city;
  }

  public Place setCity(String v) {
    this.city = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Place)) return false;

    Place that = (Place) thatObj;

    return this.id == that.id
        && this.title.equals(that.title)
        && this.latitude == that.latitude
        && this.longitude == that.longitude
        && this.created == that.created
        && this.icon.equals(that.icon)
        && this.country.equals(that.country)
        && this.city.equals(that.city);
  }

  @Override
  public String toString() {
    return "Place{"
        + "id="
        + this.id
        + ','
        + "title="
        + '\''
        + this.title
        + '\''
        + ','
        + "latitude="
        + this.latitude
        + ','
        + "longitude="
        + this.longitude
        + ','
        + "created="
        + this.created
        + ','
        + "icon="
        + '\''
        + this.icon
        + '\''
        + ','
        + "country="
        + '\''
        + this.country
        + '\''
        + ','
        + "city="
        + '\''
        + this.city
        + '\''
        + '}';
  }
}
