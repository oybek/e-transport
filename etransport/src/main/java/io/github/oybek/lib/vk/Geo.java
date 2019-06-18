package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Geo {

  private String type;

  private Coordinate coordinates;

  private Place place;

  public Geo() {}

  public Geo(String type, Coordinate coordinates, Place place) {
    this.type = type;
    this.coordinates = coordinates;
    this.place = place;
  }

  public String getType() {
    return this.type;
  }

  public Geo setType(String v) {
    this.type = v;
    return this;
  }

  public Coordinate getCoordinates() {
    return this.coordinates;
  }

  public Geo setCoordinates(Coordinate v) {
    this.coordinates = v;
    return this;
  }

  public Place getPlace() {
    return this.place;
  }

  public Geo setPlace(Place v) {
    this.place = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Geo)) return false;

    Geo that = (Geo) thatObj;

    return this.type.equals(that.type)
        && this.coordinates.equals(that.coordinates)
        && this.place.equals(that.place);
  }

  @Override
  public String toString() {
    return "Geo{"
        + "type="
        + '\''
        + this.type
        + '\''
        + ','
        + "coordinates="
        + this.coordinates
        + ','
        + "place="
        + this.place
        + '}';
  }
}
