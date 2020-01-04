package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;

public class ArrayBox <T extends Figure> {

    private T[] t;

    ArrayBox(T[] boxes) {
        this.t = boxes;
    }

    public T[] getContent() {
        return this.t;
    }

    public void setContent(T[] boxes) {
        this.t = boxes;
    }

    public T getElement(int i) {
        return this.t[i];
    }

    public void setElement(int i, T t) {
        this.t[i] = t;
    }

    public boolean isSameSize(ArrayBox<? extends Figure> t1) {
        return this.t.length == t1.t.length;
    }

}
