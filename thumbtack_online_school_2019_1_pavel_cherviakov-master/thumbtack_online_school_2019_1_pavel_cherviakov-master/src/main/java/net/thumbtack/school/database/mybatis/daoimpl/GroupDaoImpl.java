package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.GroupDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroupDaoImpl extends DaoImplBase implements GroupDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public Integer insert(School school, Group group) {
        LOGGER.debug("DAO insert Group {}, {}", group, school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).insert(school, group);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Group {}, {}", group, school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return group.getId();
    }

    @Override
    public Integer update(Group group) {
        LOGGER.debug("DAO update Group  {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).update(group);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't update Group {}", group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return group.getId();
    }

    public Group getOnSchoolId(Integer id) {
        LOGGER.debug("DAO get Group");
        try (SqlSession sqlSession = getSession()) {
            return getGroupMapper(sqlSession).getOnSchoolId(id);
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get Group", ex);
            throw ex;
        }
    }

    @Override
    public List<Group> getAll() {
        LOGGER.debug("DAO get all Groups");
        try (SqlSession sqlSession = getSession()) {
            return getGroupMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.debug("Can't get all", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Group group) {
        LOGGER.debug("DAO delete Group {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).delete(group);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Group {}", group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Integer moveTraineeToGroup(Group group, Trainee trainee) {
        LOGGER.debug("DAO move Trainee to Group {}, {}", trainee, group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).moveTraineeToGroup( group, trainee);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't  move Trainee to Group {}, {}", trainee, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee.getId();
    }

    @Override
    public void deleteTraineeFromGroup(Trainee trainee) {
        LOGGER.debug("DAO delete Trainee from Group {}", trainee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).deleteTraineeFromGroup(trainee);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Trainee from Group {}", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void addSubjectToGroup(Group group, Subject subject) {
        LOGGER.debug("DAO add Subject to Group {}, {}", subject, group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).addSubjectToGroup( group, subject);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't add Subject to Group {}, {}", subject, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
