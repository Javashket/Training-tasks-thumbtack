package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VoterDaoImpl extends DaoImplBase implements VoterDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoterDaoImpl.class);

    @Override
    public Integer insert(Voter voter) {
        LOGGER.debug("DAO insert Voter {}", voter);
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).insert(voter);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Voter {}", voter, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return voter.getId();
    }

    @Override
    public void batchInsert(List<MayorCandidate> mayorCandidates) {
        LOGGER.debug("DAO insert MayorCandidates {}", mayorCandidates);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).batchInsert(mayorCandidates);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert MayorCandidates {}", mayorCandidates, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Voter getByToken(String token) {
        LOGGER.debug("DAO get Voter by token {}", token);
        try (SqlSession sqlSession = getSession()){
            return getVoterMapper(sqlSession).getByToken(token);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Voter by token", ex);
            throw ex;
        }
    }

    @Override
    public void logoutByToken(String token) {
        LOGGER.debug("DAO logout by token Voter {}", token);
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).logoutByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't logout by token Voter {}", token, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public String loginAndSetNewToken(String login, String token) {
        LOGGER.debug("DAO login and set new token {}, {}", login, token );
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).loginAndSetNewToken(login, token);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't login and set new token {}, {}", login, token, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return token;
    }

    @Override
    public Voter getById(int id) {
        LOGGER.debug("DAO get Voter by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getVoterMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Voter by id", ex);
            throw ex;
        }
    }

    @Override
    public List<Voter> getAll() {
        LOGGER.debug("DAO get all Voters");
        try (SqlSession sqlSession = getSession()){
            return getVoterMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Voters", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Voter voter) {
        LOGGER.debug("DAO delete Voter {}", voter);
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).delete(voter);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Voter {}", voter, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Voters");
        try (SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Voters", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
