package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.util.List;

public interface GroupDao {

    Integer insert(School school, Group group);

    Integer update(Group group);

    List<Group> getAll();

    void delete(Group group);

    Integer moveTraineeToGroup(Group group, Trainee trainee);

    void deleteTraineeFromGroup(Trainee trainee);

    void addSubjectToGroup(Group group, Subject subject);
}