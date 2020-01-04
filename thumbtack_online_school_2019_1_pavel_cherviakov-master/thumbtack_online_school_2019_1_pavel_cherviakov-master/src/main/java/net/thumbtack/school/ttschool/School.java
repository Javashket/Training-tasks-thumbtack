package net.thumbtack.school.ttschool;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class School {

    private String name;
    private int year;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        nameEqualsNullOrEmpty(name);
        this.name = name;
        this.year = year;
        groups = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public static void nameEqualsNullOrEmpty(String name) throws TrainingException {
        if(name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        }
    }

    public void setName(String name) throws TrainingException {
        nameEqualsNullOrEmpty(name);
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public void  addGroup(Group group) throws TrainingException {
        for (Group group1 : this.groups) {
            if (group1.getName().equals(group.getName())) {
                throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
            }
        }
        this.groups.add(group);
    }

    public void  removeGroup(Group group) throws TrainingException{
        if(!this.groups.remove(group)) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
    }

    public void  removeGroup(String name) throws TrainingException {
        boolean resultRemove = false;
        for (Group group1 : this.groups) {
            if (group1.getName().equals(name)) {
                this.groups.remove(group1);
                resultRemove = true;
            }
        }
        if(!resultRemove) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
    }

    public boolean  containsGroup(Group group) {
        for (Group group1 : this.groups) {
            if (group1.getName().equals(group.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                name.equals(school.name) &&
                Objects.equals(groups, school.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, groups);
    }
}
