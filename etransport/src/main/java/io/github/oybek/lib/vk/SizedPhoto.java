package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SizedPhoto {

  private String src;

  private int width;

  private int height;

  private String type;

  public SizedPhoto() {}

  public SizedPhoto(String src, int width, int height, String type) {
    this.src = src;
    this.width = width;
    this.height = height;
    this.type = type;
  }

  public String getSrc() {
    return this.src;
  }

  public SizedPhoto setSrc(String v) {
    this.src = v;
    return this;
  }

  public int getWidth() {
    return this.width;
  }

  public SizedPhoto setWidth(int v) {
    this.width = v;
    return this;
  }

  public int getHeight() {
    return this.height;
  }

  public SizedPhoto setHeight(int v) {
    this.height = v;
    return this;
  }

  public String getType() {
    return this.type;
  }

  public SizedPhoto setType(String v) {
    this.type = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof SizedPhoto)) return false;

    SizedPhoto that = (SizedPhoto) thatObj;

    return this.src.equals(that.src)
        && this.width == that.width
        && this.height == that.height
        && this.type.equals(that.type);
  }

  @Override
  public String toString() {
    return "SizedPhoto{"
        + "src="
        + '\''
        + this.src
        + '\''
        + ','
        + "width="
        + this.width
        + ','
        + "height="
        + this.height
        + ','
        + "type="
        + '\''
        + this.type
        + '\''
        + '}';
  }
}
