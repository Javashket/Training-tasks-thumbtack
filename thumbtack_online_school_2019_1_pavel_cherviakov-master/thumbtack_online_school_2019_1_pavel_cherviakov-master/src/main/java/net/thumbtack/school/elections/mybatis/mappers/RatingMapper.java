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

    @Insert({"<script>",
            "INSERT INTO rating (token_evaluating_voter, rating, offer_id) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.token_evaluating_voter}, #{item.rating}, #{item.offer.id})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "rating.id")
    void batchInsert(List<Rating> ratings);

    @Select("DELETE FROM rating WHERE id = #{id}")
    Rating getById(int id);

    @Delete("SELECT * FROM rating WHERE id = #{id}")
    void deleteById(int id);

    @Select("SELECT * FROM rating WHERE offer_id = #{id}")
    Rating getByOfferId(int id);

    @Select("SELECT * FROM rating WHERE offer_id = #{id}")
    List <Rating> getByOffer(Offer offer);

    @Select("SELECT * FROM rating")
    List<Rating> getAll();

}
