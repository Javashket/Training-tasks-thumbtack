package net.thumbtack.school.figures.v2;

import java.util.Objects;

public class Rectangle extends Figure {

    private Point leftTop, rightBottom;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom));
    }

    public Rectangle(int length, int width) {
        this(new Point(0, -width), new Point(length, 0));
    }

    public Rectangle() {
        this(1, 1);
    }

    public Point getTopLeft() {
        return this.leftTop;
    }

    public Point getBottomRight() {
        return this.rightBottom;
    }

    public void setTopLeft(Point topLeft) {
        this.leftTop = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.rightBottom = bottomRight;
    }

    public int getLength() {
        return this.rightBottom.getX() - this.leftTop.getX();
    }

    public int getWidth() {
        return this.rightBottom.getY() - this.leftTop.getY();
    }

    @Override
    public void moveTo(int x, int y) {
        int length = getLength();
        int width = getWidth();
        this.leftTop.moveTo(x, y);
        this.rightBottom.moveTo(length + this.leftTop.getX(),
                width + this.leftTop.getY());
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.leftTop.moveRel(dx, dy);
        this.rightBottom.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        this.stretch(ratio, ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        int length = (int) (getLength() * xRatio);
        int width = (int) (getWidth() * yRatio);
        this.rightBottom.setX(length + this.leftTop.getX());
        this.rightBottom.setY(width + this.leftTop.getY());
    }

    @Override
    public double getArea() {
    return (this.rightBottom.getX() - this.leftTop.getX()) *
            (this.rightBottom.getY() - this.leftTop.getY());
    }

    @Override
    public double getPerimeter() {
        return (getLength() + getWidth()) * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        return this.leftTop.getX() <= x && this.rightBottom.getX() >= x &&
                this.leftTop.getY() <= y && this.rightBottom.getY() >= y;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {
        int xLeftTop = this.leftTop.getX(), xRightBottom = this.rightBottom.getX();
        int yleftTop = this.leftTop.getY(), yRightBottom = this.rightBottom.getY();
        int xLeftTop2 = rectangle.leftTop.getX(), xRightBottom2 = rectangle.rightBottom.getX();
        int yleftTop2 = rectangle.leftTop.getY(), yRightBottom2 = rectangle.rightBottom.getY();
        return isInside(xLeftTop2, yleftTop2) || isInside(xLeftTop2, yRightBottom2) ||
                isInside(xRightBottom2, yleftTop2) || isInside(xRightBottom2, yRightBottom2) ||
                rectangle.isInside(xLeftTop, yleftTop) || rectangle.isInside(xLeftTop, yRightBottom) ||
                rectangle.isInside(xRightBottom, yleftTop) || rectangle.isInside(xRightBottom, yRightBottom);
    }

    public boolean isInside(Rectangle rectangle) {
        return this.leftTop.getX() <= rectangle.leftTop.getX() && this.leftTop.getY() <= rectangle.leftTop.getY() &&
                this.rightBottom.getX() >= rectangle.rightBottom.getX() && this.rightBottom.getY() >= rectangle.rightBottom.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return leftTop.equals(rectangle.leftTop) &&
                rightBottom.equals(rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom);
    }
}
