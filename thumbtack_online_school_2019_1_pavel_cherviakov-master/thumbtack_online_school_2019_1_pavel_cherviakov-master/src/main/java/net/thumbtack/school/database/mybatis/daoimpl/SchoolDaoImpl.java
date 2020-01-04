package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.mybatis.dao.SchoolDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SchoolDaoImpl extends DaoImplBase implements SchoolDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDaoImpl.class);

    @Override
    public School insert(School school) {
        LOGGER.debug("DAO insert School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insert(school);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert School {}, {}", school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return school;
    }

    @Override
    public School getById(int id) {
        LOGGER.debug("DAO get School by id {}", id);
        try (SqlSession sqlSession = getSession()){
           return getSchoolMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get School by id ", ex);
            throw ex;
        }
    }

    @Override
    public List<School> getAllLazy() {
        LOGGER.debug("DAO get all School lazy ");
        try (SqlSession sqlSession = getSession()) {
            return getSchoolMapper(sqlSession).getAllLazy();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all School Lazy ", ex);
            throw ex;
        }
    }

    @Override
    public List<School> getAllUsingJoin() {
        LOGGER.debug("DAO get all School using join");
        try (SqlSession sqlSession = getSession()){
            return sqlSession.selectList("net.thumbtack.school.database.mybatis.mappers.SchoolMapper.getAllUsingJoin");
        } catch (RuntimeException ex ) {
            LOGGER.debug("Can't get all School using join", ex);
            throw  ex;
        }
    }

    @Override
    public void update(School school) {
        LOGGER.debug("DAO update School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).update(school);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update School {}, {}", school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(School school) {
        LOGGER.debug("DAO delete School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).delete(school);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete School {}, {}", school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Schools ");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Schools", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Integer insertSchoolTransactional(School school2018) {
        LOGGER.debug("DAO insert School transactional {}", school2018);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insertSchoolTransactional(school2018);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert School transactional {}", school2018, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return school2018.getId();
    }
}
