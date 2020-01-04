package net.thumbtack.school.misc.v2;

import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class Auto implements Colored {

    private int color;

    public Auto(int color) {
        this.color = color;
    }

    @Override
    public void setColor(int color) {
      this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
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
