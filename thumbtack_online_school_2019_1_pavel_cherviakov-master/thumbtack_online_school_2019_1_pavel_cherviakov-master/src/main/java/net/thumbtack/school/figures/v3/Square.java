package net.thumbtack.school.figures.v3;

import java.util.Objects;

public class Square extends Figure {

     private Point leftTop, rightBottom;

    public Square(Point leftTop, int size) {
        this.leftTop = leftTop;
        this.rightBottom = new Point(leftTop.getX() + size, leftTop.getY() + size);
    }

    public Square(int xLeft, int yTop, int size) {
        this(new Point(xLeft, yTop), size);
    }

    public Square(int size) {
        this(new Point(0, -size), size);
    }

    public Square() {
        this(new Point(0, -1), 1) ;
    }

    public Point getTopLeft() {
        return leftTop;
    }

    public Point getBottomRight() {
        return rightBottom;
    }

    public void setTopLeft(Point topLeft) {
        int x = this.leftTop.getX() - topLeft.getX();
        int y = this.leftTop.getY() - topLeft.getY();
        this.leftTop = topLeft;
        this.rightBottom = new Point(this.rightBottom.getX() - x, this.rightBottom.getY() - y);
    }

    public int getLength() {
        return this.rightBottom.getX() - this.leftTop.getX();
    }

    @Override
    public void moveTo(int x, int y) {
        int length = getLength();
        this.leftTop.moveTo(x, y);
        this.rightBottom.moveTo(x + length, y + length);
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.leftTop.moveRel(dx, dy);
        this.rightBottom.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        int length = (int) (getLength() * ratio);
        this.rightBottom.setX(this.leftTop.getX() + length);
        this.rightBottom.setY(this.leftTop.getY() + length);
    }

    @Override
    public double getArea() {
        return Math.pow(getLength(), 2);
    }

    @Override
    public double getPerimeter() {
        return getLength() * 4;
    }

    @Override
    public boolean isInside(int x, int y) {
        return this.leftTop.getX() <= x && this.rightBottom.getX() >= x &&
                this.leftTop.getY() <= y && this.rightBottom.getY() >= y;
    }

    public boolean isIntersects(Square square) {
        int xLeftTop = this.leftTop.getX(), xRightBottom = this.rightBottom.getX();
        int yleftTop = this.leftTop.getY(), yRightBottom = this.rightBottom.getY();
        int xLeftTop2 = square.leftTop.getX(), xRightBottom2 = square.rightBottom.getX();
        int yleftTop2 = square.leftTop.getY(), yRightBottom2 = square.rightBottom.getY();
        return isInside(xLeftTop2, yleftTop2) || isInside(xLeftTop2, yRightBottom2) ||
                isInside(xRightBottom2, yleftTop2) || isInside(xRightBottom2, yRightBottom2) ||
                square.isInside(xLeftTop, yleftTop) || square.isInside(xLeftTop, yRightBottom) ||
                square.isInside(xRightBottom, yleftTop) || square.isInside(xRightBottom, yRightBottom);
    }

    public boolean isInside(Square square) {
        return this.leftTop.getX() <= square.leftTop.getX() && this.leftTop.getY() <= square.leftTop.getY() &&
                this.rightBottom.getX() >= square.rightBottom.getX() && this.rightBottom.getY() >= square.rightBottom.getY();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return leftTop.equals(square.leftTop) &&
                rightBottom.equals(square.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom);
    }
}
