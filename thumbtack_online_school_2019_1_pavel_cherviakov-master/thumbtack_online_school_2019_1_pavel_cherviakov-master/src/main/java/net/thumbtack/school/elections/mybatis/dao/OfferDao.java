package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.elections.model.Offer;

import java.util.List;

public interface OfferDao {

    Integer insert(Offer offer);

    void batchInsert(List<Offer> offers);

    void delete(Offer offer);

    void deleteAll();

    List<Offer> getAll();

    Offer getById(int id);

    Offer getByContent(String content);

    void updateSetEmptyAuthor(String content);
}
