package net.thumbtack.school.database.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class School {

    private int id;
    private String name;
    private int year;
    private List<Group> groups;

    public School() {

    }

    public School(int id, String name, int year, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.groups = groups;
    }

    public School(int id, String name, int year) {
        this(id, name, year, new ArrayList<>());
    }

    public School(String name, int year) {
        this(0, name, year);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public School getSchool() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return getYear() == school.getYear() &&
                Objects.equals(getName(), school.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getYear());
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
    }
}
