package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Vote;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VoteMapper {

    @Delete("DELETE FROM vote")
    void deleteAll();

    @Insert("INSERT INTO vote (voter_id, vote, mayor_candidate_id)" +
            " VALUES (#{vote.voter.id}, #{vote.vote}," +
            " #{mayorCandidate.id})")
    @Options(useGeneratedKeys = true)
    Integer insert(@Param("vote")Vote vote, @Param("mayorCandidate")MayorCandidate mayorCandidate);

    @Insert({"<script>",
            "INSERT INTO vote (token_voter, consentOnNomination) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "(#{item.token_voter},  #{consentOnNomination})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(List<Vote> votes);

    @Select("SELECT * FROM vote WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "votedVoters", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.elections.mybatis.mappers.VoteMapper.getByMayorCandidateId"))})
    Vote getById(int id);

    @Select("SELECT * FROM vote WHERE mayor_candidate_id = #{id}")
    List<Vote> getByMayorCandidateId(int id);

    @Select("SELECT * FROM  vote")
    List<Vote> getAll();

    @Delete("DELETE FROM  vote WHERE token_voter = #{token}")
    void delete(String token);
}
