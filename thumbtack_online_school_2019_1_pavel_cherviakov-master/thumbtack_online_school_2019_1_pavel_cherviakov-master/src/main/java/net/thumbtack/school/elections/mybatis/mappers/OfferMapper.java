package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OfferMapper {

    @Delete("DELETE FROM offer")
    void deleteAll();

    @Insert("INSERT INTO offer (author_token, average_rating, content) " +
            "VALUES (#{author_token}, #{average_rating}," +
            " #{content})")
    @Options(useGeneratedKeys = true)
    Integer insert(Offer offer);

    @Insert({"<script>",
            "INSERT INTO offer (author_token, average_rating, content) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.author_token}, #{item.average_rating}, #{item.content})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(List<Offer> offers);

    @Select("SELECT * FROM offer WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ratings", column = "offer_id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.elections.mybatis.mappers.RatingMapper.getById"))})
    Offer getById(int id);

    @Select("SELECT * FROM offer WHERE content = #{content}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.elections.mybatis.mappers.RatingMapper.getByOfferId"))})
    Offer getByContent(String content);

    @Select("SELECT * FROM offer")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.elections.mybatis.mappers.RatingMapper.getByOfferId"))})
    List<Offer> getAll();

    @Delete("DELETE FROM offer WHERE id = #{offer.id}")
    void delete(Offer offer);

    @Update("UPDATE offer SET author_token = 'fg' WHERE content = #{content} ")
    void updateSetEmptyAuthor(String content);

}
