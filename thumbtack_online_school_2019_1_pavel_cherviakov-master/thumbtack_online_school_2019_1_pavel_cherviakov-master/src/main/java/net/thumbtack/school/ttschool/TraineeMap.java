package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {

    private Map<Trainee, String> map;

    public TraineeMap() {
        this.map = new HashMap<Trainee, String>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(this.map.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        this.map.put(trainee, institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(this.map.containsKey(trainee)) {
            this.map.replace(trainee, institute);
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if(this.map.remove(trainee) == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        };
    }

    public int getTraineesCount() {
        return this.map.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException{
        if (!this.map.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return this.map.get(trainee);
    }

    public Set<Trainee> getAllTrainees() {
        return this.map.keySet();
    }

    public Set<String> getAllInstitutes() {
        Set<String> strings = new HashSet<>();
        for (Trainee trainee : this.map.keySet()) {
            strings.add(this.map.get(trainee));
        }
        return strings;
    }

    public boolean isAnyFromInstitute(String institute) {
        return this.map.containsValue(institute);
    }
}
