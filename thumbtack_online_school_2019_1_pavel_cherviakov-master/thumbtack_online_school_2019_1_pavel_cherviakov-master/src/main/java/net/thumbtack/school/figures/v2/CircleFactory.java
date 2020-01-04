package net.thumbtack.school.figures.v2;

public class CircleFactory {

    private static int createCount = 0;

    public static Circle createCircle(Point center, int radius) {
        Circle circle = new Circle(center,radius);
        if(circle.getRadius() == radius && circle.getCenter().equals(center)) {
            createCount++;
        }
        return circle;
    }

    public static int getCircleCount() {
        return createCount;
    }

    public static void reset() {
        createCount = 0;
    }

}
