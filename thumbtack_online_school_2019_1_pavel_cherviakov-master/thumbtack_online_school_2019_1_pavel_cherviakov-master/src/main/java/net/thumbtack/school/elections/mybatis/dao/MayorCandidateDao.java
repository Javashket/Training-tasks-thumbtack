package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.MayorCandidate;

import java.util.List;

public interface MayorCandidateDao {

    Integer insert(MayorCandidate mayorCandidate);

//    boolean insert(List<MayorCandidate> mayorCandidate);

    void delete(MayorCandidate mayorCandidate);

    void deleteAll();

    MayorCandidate getById(int id);

    List<MayorCandidate> getAll();

}
