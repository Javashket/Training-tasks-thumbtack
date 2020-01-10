package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.elections.model.MayorCandidate;

import java.util.List;

public interface MayorCandidateDao {

    Integer insert(MayorCandidate mayorCandidate);

    void batchInsert(List<MayorCandidate> mayorCandidates);

    MayorCandidate getByTokenVoter(String token);

//    boolean insert(List<MayorCandidate> mayorCandidate);

    void delete(String token);

    void deleteAll();

    void consentOnPosition(String token);

    MayorCandidate getById(int id);

    List<MayorCandidate> getAll();

}
