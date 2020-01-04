package net.thumbtack.school.elections.mybatis.daoimpl;

import net.thumbtack.school.elections.mybatis.dao.CommonDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonDaoImpl extends DaoImplBase implements CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Override
    public void clear() {
        LOGGER.debug("DAO clear ");
        try (SqlSession sqlSession = getSession()) {
            try {
                getMayorCandidateMapper(sqlSession).deleteAll();
                getVoterMapper(sqlSession).deleteAll();
                getOfferMapper(sqlSession).deleteAll();
                getRatingMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't clear", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
