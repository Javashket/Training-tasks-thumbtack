package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {

    private Queue<Trainee> queue;

    public TraineeQueue() {
        this.queue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        this.queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        if(this.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        return this.queue.remove();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
