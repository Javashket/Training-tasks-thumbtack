package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Vote;
import net.thumbtack.school.elections.mybatis.dao.VoteDao;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VoteDaoImpl extends DaoImplBase implements VoteDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MayorCandidateDaoImpl.class);

    @Override
    public Integer insert(Vote vote) {
        LOGGER.debug("DAO insert Vote {}", vote);
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoteMapper(sqlSession).insert(vote);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Vote {}", vote, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return vote.getId();
    }

    @Override
    public void batchInsert(List<Vote> votes) {
        LOGGER.debug("DAO insert Votes {}", votes);
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoteMapper(sqlSession).batchInsert(votes);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Votes {}", votes, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Vote getById(int id) {
        LOGGER.debug("DAO get Vote by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getVoteMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Vote by id", ex);
            throw ex;
        }
    }

    @Override
    public Vote getByMayorCandidateId(int id) {
        LOGGER.debug("DAO get Vote by Mayor Candidate id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getVoteMapper(sqlSession).getByMayorCandidateId(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Vote by Mayor Candidate id", ex);
            throw ex;
        }
    }

    @Override
    public List<Vote> getAll() {
        LOGGER.debug("DAO get all Vote");
        try (SqlSession sqlSession = getSession()){
            return getVoteMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Vote", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Votes");
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoteMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Votes", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
