package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {

    private Color color;

    public ColoredRectangle(Point leftTop, Point rightBottom, Color color) throws ColorException {
        super(leftTop, rightBottom);
        Color.coloredEqualsNull(color);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom), color);
    }

    public ColoredRectangle(int length, int width, Color color) throws ColorException {
        this(new Point(0, -width), new Point(length, 0), color);
    }

    public ColoredRectangle(Color color) throws ColorException {
        this( 1, 1, color);
    }

    public ColoredRectangle(Point leftTop, Point rightBottom, String color)  throws ColorException {
        this(leftTop, rightBottom, Color.colorFromString(color));
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, String color)  throws ColorException {
        this(xLeft, yTop, xRight, yBottom, Color.colorFromString(color));
    }

    public ColoredRectangle(int length, int width, String color)  throws ColorException {
        this(length, width, Color.colorFromString(color));
    }

    public ColoredRectangle(String color)  throws ColorException {
        this(Color.colorFromString(color));
    }

    public ColoredRectangle() throws ColorException {
        this(Color.RED);
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        setColor(Color.colorFromString(colorString));
    }

    public void setColor(Color color) throws ColorException {
        Color.coloredEqualsNull(color);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
