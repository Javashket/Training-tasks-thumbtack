package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Ellipse {

    private Point center;
    private int xAxis, yAxis;

    public Ellipse(Point center, int xAxis, int yAxis) {
        this.center = center;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        this(new Point(xCenter, yCenter), xAxis, yAxis);
    }

    public Ellipse(int xAxis, int yAxis) {
        this(new Point(), xAxis, yAxis);
    }

    public Ellipse() {
        this(1, 1);
    }

    public Point getCenter() {
        return this.center;
    }

    public int getXAxis() {
        return this.xAxis;
    }

    public int getYAxis() {
        return this.yAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void moveTo(int x, int y) {
        this.center.moveTo(x, y);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        this.center.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        this.stretch(ratio, ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        this.xAxis = (int)(this.xAxis * xRatio);
        this.yAxis = (int)(this.yAxis * yRatio);
    }

    public double getArea() {
        return Math.PI * this.xAxis * this.yAxis / 4;
    }

    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(this.yAxis, 2) + Math.pow(this.xAxis, 2)) / 8);
    }

    public boolean isInside(int x, int y) {
       return (Math.pow(x - this.center.getX(),2) / Math.pow(this.xAxis / 2, 2) +
               Math.pow(y - this.center.getY(),2) / Math.pow(this.yAxis / 2, 2)) <= 1;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return center.equals(ellipse.center) &&
                xAxis == ellipse.xAxis &&
                yAxis == ellipse.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, xAxis, yAxis);
    }
}
