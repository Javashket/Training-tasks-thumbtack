package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;

import java.util.List;

public interface RatingDao {

    Integer insert(Rating rating, Offer offer);

    void deleteAll();

    Rating getById(int id);

    List<Rating> getByOffer(Offer offer);

    List<Rating> getAll();

}
