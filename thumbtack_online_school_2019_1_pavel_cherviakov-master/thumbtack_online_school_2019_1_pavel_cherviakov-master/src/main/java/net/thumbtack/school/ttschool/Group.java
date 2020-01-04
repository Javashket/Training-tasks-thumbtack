package net.thumbtack.school.ttschool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Group {

    private String name, room;
    private List<Trainee> list;

    public Group(String name, String room) throws TrainingException {
        nameEqualsNullOrEmpty(name);
        this.name = name;
        roomEqualsNullOrEmpty(room);
        this.room = room;
        this.list = new ArrayList<>();
    }

    public static void nameEqualsNullOrEmpty(String name) throws TrainingException {
        if(name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
    }

    public static void roomEqualsNullOrEmpty(String room) throws TrainingException {
        if(room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws TrainingException {
        nameEqualsNullOrEmpty(name);
        this.name = name;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) throws TrainingException {
        roomEqualsNullOrEmpty(room);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return this.list;
    }

    public void  addTrainee(Trainee trainee) {
        this.list.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
       if(!this.list.remove(trainee)) {
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
       }
    }

    public void  removeTrainee(int index) throws TrainingException {
        if(index >= this.list.size() ||this.list.remove(index) == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public Trainee  getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : list) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : list) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  sortTraineeListByFirstNameAscendant() {
        Comparator<Trainee> comparator = Comparator.comparing(Trainee::getFirstName);
        this.list.sort(comparator);
    }

    public void  sortTraineeListByRatingDescendant() {
        Comparator<Trainee> comparator = (o1, o2) -> o2.getRating() - o1.getRating();
        this.list.sort(comparator);
    }

    public void  reverseTraineeList() {
        int size = this.list.size();
        for (int i = 0; i < size / 2; i++) {
            Trainee trainee = this.list.get(i);
            this.list.remove(i);
            this.list.add(i, this.list.get(size - i - 2));
            this.list.remove(size - i - 1);
            this.list.add(size- i - 1, trainee);
        }
    }

    public void  rotateTraineeList(int positions) {
        if(positions < 0) {
            this.reverseTraineeList();
        }
        int position = Math.abs(positions);
        int size = this.list.size();
        ArrayList<Trainee> trainees = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            for (int j = 1; j < size; j++) {
                trainees.add(this.list.get(j));
            }
            trainees.add(this.list.get(0));
            this.list.clear();
            this.list.addAll(trainees);
            trainees.clear();
        }
        if(positions < 0) {
            this.reverseTraineeList();
        }
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {
        List<Trainee> list = new ArrayList<>();
        int maxRaiting = 0;
        for (Trainee trainee : this.list) {
            if (trainee.getRating() > maxRaiting) {
                maxRaiting = trainee.getRating();
            }
        }
        for (Trainee trainee : this.list) {
            if (trainee.getRating() == maxRaiting) {
                list.add(trainee);
            }
        }
        if(list.size() == 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return list;
    }

    public boolean  hasDuplicates() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (this.list.get(i).getFullName().equals(this.list.get(j).getFullName()) &&
                        this.list.get(i).getRating() == this.list.get(j).getRating() && j != i) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) &&
                room.equals(group.room) &&
                Objects.equals(list, group.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, list);
    }
}
