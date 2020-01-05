package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import net.thumbtack.school.elections.model.MayorCandidate;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MayorCandidateDaoImpl extends DaoImplBase implements MayorCandidateDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MayorCandidateDaoImpl.class);

    @Override
    public Integer insert(MayorCandidate mayorCandidate) {
        LOGGER.debug("DAO insert MayorCandidate {}", mayorCandidate);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).insert(mayorCandidate);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert MayorCandidate {}", mayorCandidate, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return mayorCandidate.getId();
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
    public MayorCandidate getById(int id) {
        LOGGER.debug("DAO get Voter by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getMayorCandidateMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Voter by id", ex);
            throw ex;
        }
    }

    @Override
    public List<MayorCandidate> getAll() {
        LOGGER.debug("DAO get all MayorCandidates");
        try (SqlSession sqlSession = getSession()){
            return getMayorCandidateMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all MayorCandidate", ex);
            throw ex;
        }
    }

    @Override
    public void delete(MayorCandidate mayorCandidate) {
        LOGGER.debug("DAO delete MayorCandidate {}", mayorCandidate);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).delete(mayorCandidate);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete MayorCandidate {}", mayorCandidate, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all MayorCandidates");
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all MayorCandidates", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
