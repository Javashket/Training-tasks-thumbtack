package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import org.apache.ibatis.annotations.Param;
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
    public void includeOffer(int id, Offer offer) {
        LOGGER.debug("DAO include Offer {}, {}", id, offer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).includeOffer(id,offer);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't include Offer {}, {}", id, offer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteOffer(int id, Offer offer){
        LOGGER.debug("DAO delete Offer {}, {}", id, offer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).deleteOffer(id, offer);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Offer {}, {}", id, offer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
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
        LOGGER.debug("DAO get MayorCandidate by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getMayorCandidateMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get MayorCandidate by id", ex);
            throw ex;
        }
    }

    @Override
    public void consentOnPosition(String token) {
        LOGGER.debug("DAO consent on position {}", token );
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).consentOnPosition(token);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't consent on position {}", token, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public MayorCandidate getByTokenVoter(String token) {
        LOGGER.debug("DAO get MayorCandidate by token {}", token);
        try (SqlSession sqlSession = getSession()){
            return getMayorCandidateMapper(sqlSession).getByTokenVoter(token);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get MayorCandidate by token", ex);
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
    public void delete(String token) {
        LOGGER.debug("DAO delete MayorCandidate {}", token);
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).delete(token);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete MayorCandidate {}", token, ex);
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
