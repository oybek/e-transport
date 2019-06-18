package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Button {

  private Action action;

  private String color;

  public Button() {}

  public Button(Action action, String color) {
    this.action = action;
    this.color = color;
  }

  public Action getAction() {
    return this.action;
  }

  public Button setAction(Action v) {
    this.action = v;
    return this;
  }

  public String getColor() {
    return this.color;
  }

  public Button setColor(String v) {
    this.color = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Button)) return false;

    Button that = (Button) thatObj;

    return this.action.equals(that.action) && this.color.equals(that.color);
  }

  @Override
  public String toString() {
    return "Button{" + "action=" + this.action + ',' + "color=" + '\'' + this.color + '\'' + '}';
  }
}
