package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

public class Auto implements Colored {

    private Color color;

    public Auto(Color color) {
        this.color = color;
    }

    @Override
    public void setColor(Color color) {
      this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
          this.color = Color.colorFromString(colorString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return color == auto.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
