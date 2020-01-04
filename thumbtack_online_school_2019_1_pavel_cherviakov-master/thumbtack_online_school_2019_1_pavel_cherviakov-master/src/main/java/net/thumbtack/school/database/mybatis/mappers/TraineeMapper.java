package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TraineeMapper {

    @Insert("INSERT INTO trainee (firstname, lastname, rating) VALUES ( #{firstName}, #{lastName}, #{rating} )")
    @Options(useGeneratedKeys = true)
    Integer insert(Trainee trainee);

    @Select("SELECT * FROM trainee WHERE id = #{id}")
    Trainee getById(int id);

    @Select("SELECT * FROM trainee")
    List<Trainee> getAll();

    @Update("UPDATE trainee SET firstname = #{trainee.firstName}," +
            " lastname = #{trainee.lastName} , rating = #{trainee.rating} " +
            " WHERE id = #{trainee.id} ")
    Integer update( @Param("trainee") Trainee trainee);

    @Select({"<script>",
            "SELECT * FROM trainee",
            "<where>" ,
            "<if test='firstName != null'> firstname like #{firstName}",
            "</if>",
            "<if test='lastName != null'> AND lastname like #{lastName}",
            "</if>",
            "<if test='rating != null'> AND rating like #{rating}",
            "</if>",
            "</where>" ,
            "</script>"})
    List<Trainee> getAllWithParams(@Param("firstName") String firstName,
                                          @Param("lastName") String lastName,
                                          @Param("rating")Integer rating);

    @Insert({"<script>",
            "INSERT INTO trainee (firstname, lastname, rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.rating} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(List<Trainee> trainees);

    @Delete("DELETE FROM trainee WHERE id = #{trainee.id}")
    void delete(Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();

    @Select("SELECT * FROM trainee WHERE group_id = #{id} ")
    Group getOnGroupId(Integer id);
}
