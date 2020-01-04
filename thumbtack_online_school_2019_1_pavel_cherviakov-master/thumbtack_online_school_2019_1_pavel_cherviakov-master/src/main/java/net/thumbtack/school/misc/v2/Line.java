package net.thumbtack.school.misc.v2;

import net.thumbtack.school.iface.v2.Resizable;

import java.util.Objects;

public class Line implements Resizable {

    private double length;

    public Line(double length) {
        this.length = length;
    }

    @Override
    public void resize(double ratio) {
        this.length = this.length * ratio;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Double.compare(line.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }
}
