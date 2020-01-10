package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.Vote;

import java.util.List;

public interface VoteDao {

    void deleteAll();

    Integer insert(Vote vote);

    void batchInsert(List<Vote> votes);

    Vote getById(int id);

    Vote getByMayorCandidateId(int id);

    List<Vote> getAll();
}
