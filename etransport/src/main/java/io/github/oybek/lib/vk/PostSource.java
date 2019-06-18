package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostSource {

  private PostSourceType ttype;

  private PlatformType platform;

  private DataType data;

  private String url;

  public PostSource() {}

  public PostSource(PostSourceType ttype, PlatformType platform, DataType data, String url) {
    this.ttype = ttype;
    this.platform = platform;
    this.data = data;
    this.url = url;
  }

  public PostSourceType getTtype() {
    return this.ttype;
  }

  public PostSource setTtype(PostSourceType v) {
    this.ttype = v;
    return this;
  }

  public PlatformType getPlatform() {
    return this.platform;
  }

  public PostSource setPlatform(PlatformType v) {
    this.platform = v;
    return this;
  }

  public DataType getData() {
    return this.data;
  }

  public PostSource setData(DataType v) {
    this.data = v;
    return this;
  }

  public String getUrl() {
    return this.url;
  }

  public PostSource setUrl(String v) {
    this.url = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof PostSource)) return false;

    PostSource that = (PostSource) thatObj;

    return this.ttype.equals(that.ttype)
        && this.platform.equals(that.platform)
        && this.data.equals(that.data)
        && this.url.equals(that.url);
  }

  @Override
  public String toString() {
    return "PostSource{"
        + "ttype="
        + this.ttype
        + ','
        + "platform="
        + this.platform
        + ','
        + "data="
        + this.data
        + ','
        + "url="
        + '\''
        + this.url
        + '\''
        + '}';
  }
}
