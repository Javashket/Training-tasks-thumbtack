package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RatingDaoImpl extends DaoImplBase implements RatingDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingDaoImpl.class);

    @Override
    public Integer insert(Rating rating, Offer offer) {
        LOGGER.debug("DAO insert Rating {}, {}", rating, offer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getRatingMapper(sqlSession).insert(rating, offer);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Rating {}, {}", rating,offer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return rating.getId();
    }

    @Override
    public void batchInsert(List<Rating> ratings) {
        LOGGER.debug("DAO insert Ratings {}", ratings);
        try (SqlSession sqlSession = getSession()) {
            try {
                getRatingMapper(sqlSession).batchInsert(ratings);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Ratings {}", ratings, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Rating getById(int id) {
        LOGGER.debug("DAO get Rating by id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getRatingMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Rating by id", ex);
            throw ex;
        }
    }

    @Override
    public void deleteById(int id) {
        LOGGER.debug("DAO delete Rating by id {}", id);
        try (SqlSession sqlSession = getSession()){
            getRatingMapper(sqlSession).deleteById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't delete Rating by id", ex);
            throw ex;
        }
    }

    public Rating getByOfferId(int id) {
        LOGGER.debug("DAO get Rating by offer_id {}", id);
        try (SqlSession sqlSession = getSession()){
            return getRatingMapper(sqlSession).getByOfferId(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Rating by offer_id", ex);
            throw ex;
        }
    }

    @Override
    public List<Rating> getByOffer(Offer offer) {
        LOGGER.debug("DAO get Rating by Offer {}", offer);
        try (SqlSession sqlSession = getSession()){
            return getRatingMapper(sqlSession).getByOffer(offer);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Rating by Offer", ex);
            throw ex;
        }
    }

    @Override
    public List<Rating> getAll() {
        LOGGER.debug("DAO get all Ratings");
        try (SqlSession sqlSession = getSession()){
            return getRatingMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Ratings", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Ratings");
        try (SqlSession sqlSession = getSession()) {
            try {
                getRatingMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Ratings", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
