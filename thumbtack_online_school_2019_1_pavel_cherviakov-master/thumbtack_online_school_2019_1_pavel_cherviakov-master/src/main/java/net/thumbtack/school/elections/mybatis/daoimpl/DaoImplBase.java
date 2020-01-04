package net.thumbtack.school.elections.mybatis.daoimpl;


import net.thumbtack.school.elections.mybatis.mappers.MayorCandidateMapper;
import net.thumbtack.school.elections.mybatis.mappers.OfferMapper;
import net.thumbtack.school.elections.mybatis.mappers.RatingMapper;
import net.thumbtack.school.elections.mybatis.mappers.VoterMapper;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected VoterMapper getVoterMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(VoterMapper.class);
    }

    protected OfferMapper getOfferMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(OfferMapper.class);
    }

    protected MayorCandidateMapper getMayorCandidateMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(MayorCandidateMapper.class);
    }

    protected RatingMapper getRatingMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(RatingMapper.class);
    }

}