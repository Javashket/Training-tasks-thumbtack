package net.thumbtack.school.database.mybatis.dao;

import java.util.List;

import net.thumbtack.school.database.model.School;

public interface SchoolDao {

    School insert(School school);

    School getById(int id);

    List<School> getAllLazy();

    List<School> getAllUsingJoin();

    void update(School school);

    void delete(School school);

    void deleteAll();

    Integer insertSchoolTransactional(School school2018);
}