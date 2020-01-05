package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RatingMapper {

    @Delete("DELETE FROM rating")
    void deleteAll();

    @Insert("INSERT INTO rating (token_evaluating_voter, rating, offer_id)" +
            " VALUES (#{rating.token_evaluating_voter}, " +
            "#{rating.rating}, #{offer.id})")
    @Options(useGeneratedKeys = true, keyProperty = "rating.id")
    Integer insert(@Param("rating") Rating rating, @Param("offer") Offer offer);

    @Select("SELECT * FROM rating WHERE id = #{id}")
    Rating getById(int id);

    @Select("SELECT * FROM rating WHERE offer_id = #{id}")
    List <Rating> getByOffer(Offer offer);

    @Select("SELECT * FROM rating")
    List<Rating> getAll();

}
