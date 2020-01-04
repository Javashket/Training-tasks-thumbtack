package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.model.Subject;

import java.util.List;

public interface SubjectDao {

    Integer insert(Subject subject);

    Subject getById(int id);

    List<Subject> getAll();

    Integer update(Subject subject);

    void delete(Subject subject);

    void deleteAll();
}