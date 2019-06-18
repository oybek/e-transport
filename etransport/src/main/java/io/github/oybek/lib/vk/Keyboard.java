package io.github.oybek.lib.vk;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Keyboard {

  private boolean oneTime;

  private List<List<Button>> buttons;

  public Keyboard() {}

  public Keyboard(boolean oneTime, List<List<Button>> buttons) {
    this.oneTime = oneTime;
    this.buttons = buttons;
  }

  public boolean getOneTime() {
    return this.oneTime;
  }

  public Keyboard setOneTime(boolean v) {
    this.oneTime = v;
    return this;
  }

  public List<List<Button>> getButtons() {
    return this.buttons;
  }

  public Keyboard setButtons(List<List<Button>> v) {
    this.buttons = v;
    return this;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (this == thatObj) return true;

    if (!(thatObj instanceof Keyboard)) return false;

    Keyboard that = (Keyboard) thatObj;

    return this.oneTime == that.oneTime && this.buttons.equals(that.buttons);
  }

  @Override
  public String toString() {
    return "Keyboard{" + "oneTime=" + this.oneTime + ',' + "buttons=" + this.buttons + '}';
  }
}
