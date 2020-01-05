package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.model.Offer;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OfferDaoImpl extends DaoImplBase implements OfferDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferDaoImpl.class);

    @Override
    public Integer insert(Offer offer) {
        LOGGER.debug("DAO insert Offer {}", offer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getOfferMapper(sqlSession).insert(offer);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Offer {}", offer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return offer.getId();
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
    public Offer getById(int id) {
        LOGGER.debug("DAO get Offer by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getOfferMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Offer by id", ex);
            throw ex;
        }
    }

    @Override
    public Offer getByContent(String content) {
        LOGGER.debug("DAO get Offer by content {}", content);
        try (SqlSession sqlSession = getSession()){
            return getOfferMapper(sqlSession).getByContent(content);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Offer by content", ex);
            throw ex;
        }
    }

    @Override
    public List<Offer> getAll() {
        LOGGER.debug("DAO get all Offer");
        try (SqlSession sqlSession = getSession()){
            return getOfferMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Offers", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Offer offer) {
        LOGGER.debug("DAO delete Offer {}", offer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getOfferMapper(sqlSession).delete(offer);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Offer {}", offer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Offers");
        try (SqlSession sqlSession = getSession()) {
            try {
                getOfferMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Offers", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

}
