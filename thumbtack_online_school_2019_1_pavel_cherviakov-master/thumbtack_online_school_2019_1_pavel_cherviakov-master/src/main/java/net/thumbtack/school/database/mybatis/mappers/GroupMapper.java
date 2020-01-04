package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GroupMapper {


    @Insert(" INSERT INTO groupp (name, room, school_id) VALUES (#{group.name}, #{group.room}, #{school.id})")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    Integer insert(@Param("school") School school, @Param("group") Group group);

    @Update("UPDATE groupp SET name = #{group.name}," +
            " room = #{group.room} " +
            " WHERE id = #{group.id} ")
    Integer update(Group group);

    @Select("SELECT * FROM groupp")
    List<Group> getAll();

    @Select("SELECT * FROM groupp WHERE school_id = #{id} ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getOnGroupId"))})
    Group getOnSchoolId(Integer id);

    @Delete("DELETE FROM groupp WHERE id = #{group.id}")
    void delete(Group group);

    @Update("UPDATE trainee SET group_id = #{group.id} " +
            " WHERE id = #{trainee.id} ")
    Integer moveTraineeToGroup(@Param("group")Group group,  @Param("trainee")Trainee trainee);

    @Update("UPDATE trainee SET group_id = null " +
            " WHERE id = #{trainee.id} ")
    void deleteTraineeFromGroup(Trainee trainee);

    @Update("UPDATE subject SET group_id = #{group.id} " +
            " WHERE id = #{subject.id} ")
    void addSubjectToGroup( @Param("group") Group group, @Param("subject") Subject subject);
}