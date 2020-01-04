package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class PairBox <T extends Figure, S extends Figure> implements HasArea {

    private T figure;
    private S figure1;

    PairBox(T figure, S figure1) {
        this.figure = figure;
        this.figure1 = figure1;
    }

    public T getContentFirst() {
        return this.figure;
    }

    public S getContentSecond() {
        return  this.figure1;
    }

    public void setContentFirst(T figure) {
        this.figure = figure;
    }

    public void setContentSecond(S figure1) {
        this.figure1 = figure1;
    }

    public boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox) {
        return pairBox.getArea() == this.getArea();
    }

    public static boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox,
                                      PairBox<? extends Figure, ? extends Figure> pairBox1) {
        return pairBox.getArea() == pairBox1.getArea();
    }

    @Override
    public double getArea() {
        return figure.getArea() + figure1.getArea();
    }
}
