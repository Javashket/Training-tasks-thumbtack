package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class Box <T extends Figure> implements HasArea {

    private T t;

    Box(T t) {
        this.t = t;
    }

    public T getContent() {
        return this.t;
    }

    public void setContent(T t) {
        this.t = t;
    }

    public boolean isAreaEqual(Box<? extends Figure> t) {
      return t.getArea() == this.t.getArea();
    }

    public static boolean isAreaEqual(Box<? extends Figure> box, Box<? extends Figure> box1) {
        return box.getArea() == box1.getArea();
    }

    @Override
    public double getArea() {
        return t.getArea();
    }
}
