package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.MayorCandidate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MayorCandidateMapper {

    @Delete("DELETE FROM mayor_candidate")
    void deleteAll();

    @Insert("INSERT INTO mayor_candidate (token_voter, consentOnNomination) VALUES (#{token_voter}, #{consentOnNomination})")
    @Options(useGeneratedKeys = true)
    Integer insert(MayorCandidate mayorCandidate);

    @Insert({"<script>",
            "INSERT INTO mayor_candidate (token_voter, consentOnNomination) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "(#{item.token_voter},  #{consentOnNomination})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(List<MayorCandidate> mayorCandidates);

    @Select("SELECT * FROM mayor_candidate WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "votedVoters", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.VoteMapper.getByMayorCandidateId"))})
    MayorCandidate getById(int id);

    @Select("SELECT * FROM mayor_candidate WHERE token_voter = #{token}")
    MayorCandidate getByTokenVoter(String token);

    @Update("UPDATE mayor_candidate SET consentOnNomination = true" +
            " WHERE token_voter = #{token} ")
    void consentOnPosition(String token);

    @Select("SELECT * FROM  mayor_candidate")
    List<MayorCandidate> getAll();

    @Delete("DELETE FROM  mayor_candidate WHERE token_voter = #{token}")
    void delete(String token);

}
