package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {

    @Insert("INSERT INTO subject (name) VALUES (#{subject.name})")
    @Options(useGeneratedKeys = true)
    Integer insert(Subject subject);

    @Select("SELECT * FROM subject WHERE id = #{id}")
    Subject getById(int id);

    @Select("SELECT * FROM subject")
    List<Subject> getAll();

    @Update("UPDATE subject SET name = #{subject.name}  WHERE id = #{subject.id} ")
    Integer update(Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{subject.id}")
    void delete(Subject subject);

    @Delete("DELETE FROM subject")
    void deleteAll();
}
