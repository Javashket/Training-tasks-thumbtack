package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Vote;

import java.util.List;

public interface VoteDao {

    void deleteAll();

    Integer insert(Vote vote, MayorCandidate mayorCandidate);

    void batchInsert(List<Vote> votes);

    Vote getById(int id);

    List<Vote> getByMayorCandidateId(int id);

    List<Vote> getAll();
}
