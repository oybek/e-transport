package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Coordinate {

  private double latitude;

  private double longitude;

  public Coordinate() {}

  public Coordinate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public Coordinate setLatitude(double v) {
    this.latitude = v;
    return this;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public Coordinate setLongitude(double v) {
    this.longitude = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Coordinate)) return false;

    Coordinate that = (Coordinate) thatObj;

    return this.latitude == that.latitude && this.longitude == that.longitude;
  }

  @Override
  public String toString() {
    return "Coordinate{" + "latitude=" + this.latitude + ',' + "longitude=" + this.longitude + '}';
  }
}
