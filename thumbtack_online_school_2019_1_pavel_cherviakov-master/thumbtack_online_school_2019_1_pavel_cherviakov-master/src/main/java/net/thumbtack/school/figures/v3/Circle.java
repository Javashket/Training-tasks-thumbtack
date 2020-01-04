package net.thumbtack.school.figures.v3;

import java.util.Objects;

public class Circle extends Figure {

      private Point center;
      private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this(new Point(xCenter, yCenter), radius);
    }

    public Circle(int radius) {
        this(new Point(), radius);
    }

    public Circle() {
        this(1);
    }

    public Point getCenter() {
        return this.center;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void moveTo(int x, int y) {
        this.center.moveTo(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.center.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        this.radius = (int) (this.radius * ratio);
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public boolean isInside(int x, int y) {
        return Math.pow(this.center.getX() - x, 2) +
                Math.pow(this.center.getY() - y, 2) <= Math.pow(this.radius, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return center.equals(circle.center) &&
                radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}
