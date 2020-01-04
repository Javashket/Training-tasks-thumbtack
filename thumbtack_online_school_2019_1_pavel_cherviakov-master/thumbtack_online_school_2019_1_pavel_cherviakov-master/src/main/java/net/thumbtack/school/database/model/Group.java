package net.thumbtack.school.database.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {

    private int id;
    private String name;
    private String room;
    private List<Trainee> trainees;
    private List<Subject> subjects;

    public Group() {

    }

    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.trainees = trainees;
        this.subjects = subjects;
    }

    public Group(int id, String name, String room) {
        this(id, name, room, new ArrayList<>(), new ArrayList<>());
    }

    public Group(String name, String room) {
        this(0, name, room);
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Group getGroup() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(getName(), group.getName()) &&
                Objects.equals(getRoom(), group.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRoom());
    }

    public void addTrainee(Trainee trainee) {
        this.trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) {
        this.trainees.remove(trainee);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

}
