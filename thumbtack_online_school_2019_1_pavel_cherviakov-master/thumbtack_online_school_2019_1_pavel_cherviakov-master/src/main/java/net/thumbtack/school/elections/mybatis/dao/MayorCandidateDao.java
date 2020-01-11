package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MayorCandidateDao {

    Integer insert(MayorCandidate mayorCandidate);

    void batchInsert(List<MayorCandidate> mayorCandidates);

    MayorCandidate getByTokenVoter(String token);

    void delete(String token);

    void deleteAll();

    void consentOnPosition(String token);

    MayorCandidate getById(int id);

    List<MayorCandidate> getAll();

    void includeOffer(int id, Offer offer);

    void deleteOffer(int id, Offer offer);
}
