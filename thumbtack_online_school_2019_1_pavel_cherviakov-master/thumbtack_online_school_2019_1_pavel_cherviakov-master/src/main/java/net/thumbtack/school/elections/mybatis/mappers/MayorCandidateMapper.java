package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MayorCandidateMapper {

//    boolean insert(List<MayorCandidate> mayorCandidate);

    @Delete("DELETE FROM mayor_candidate")
    void deleteAll();

    @Insert("INSERT INTO mayor_candidate (voter_id) VALUES (#{voter.id})")
    @Options(useGeneratedKeys = true)
    Integer insert(MayorCandidate mayorCandidate);

    @Select("SELECT * FROM mayor_candidate WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "voter", column = "voter_id", javaType = Voter.class,
                    one = @One(select = "net.thumbtack.school.elections.mybatis.mappers.VoterMapper.getById"))})
    MayorCandidate getById(int id);

    @Select("SELECT * FROM  mayor_candidate")
    List<MayorCandidate> getAll();

    @Delete("DELETE FROM  mayor_candidate WHERE id = #{voter.id}")
    void delete(MayorCandidate  mayorCandidate);

}
