package net.thumbtack.school.elections.mybatis.dao;

import net.thumbtack.school.elections.model.Voter;

import java.util.List;

public interface VoterDao {

    Integer insert(Voter voter);

    void delete(Voter voter);

    void deleteAll();

    Voter getByToken(String token);

    String loginAndSetNewToken(String login, String token);

    void logoutByToken(String token);

    Voter getById(int id);

    List<Voter> getAll();

}
