package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredCircle extends Circle{

    private int color;

    public ColoredCircle(Point center, int radius, int color){
        super(center, radius);
        this.color = color;
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
        this(new Point(xCenter, yCenter), radius, color);
    }

    public ColoredCircle(int radius, int color) {
        super(radius);
        this.color = color;
    }

    public ColoredCircle(int color) {
        super();
        this.color = color;
    }

    public ColoredCircle() {
        this(1);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredCircle that = (ColoredCircle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
