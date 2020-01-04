package net.thumbtack.school.misc.v2;

import net.thumbtack.school.iface.v2.HasArea;

import java.util.Objects;

public class House implements HasArea {

    private int area;

    public House(int area) {
        this.area = area;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return area == house.area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }
}
