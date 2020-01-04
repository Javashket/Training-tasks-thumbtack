package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SchoolMapper {

    @Insert("INSERT INTO school (name, year) VALUES (#{name}, #{year})")
    @Options(useGeneratedKeys = true)
    Integer insert(School school);

    @Select("SELECT * FROM school WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getOnSchoolId"))})
    School getById(int id);

    @Select("SELECT id, name, year FROM school")
    List<School> getAllLazy();

    List<School> getAllUsingJoin();

    @Update("UPDATE school SET name = #{school.name}," +
            " year = #{school.year} " +
            " WHERE id = #{school.id} ")
    void update(School school);

    @Delete("DELETE FROM school WHERE id = #{school.id}")
    void delete(School school);

    @Delete("DELETE FROM school")
    void deleteAll();

    @Insert("INSERT INTO school (name, year) VALUES (#{name}, #{year})")
    @Options(useGeneratedKeys = true)
    Integer insertSchoolTransactional(School school2018);
}
