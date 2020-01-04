package net.thumbtack.school.elections.mybatis.mappers;

import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VoterMapper {

    @Delete("DELETE FROM voter")
    void deleteAll();

    @Insert("INSERT INTO voter (firstName, lastName," +
            " patronymic, street, house," +
            " apartment, login, password, token) VALUES (#{firstName}, " +
            "#{lastName}," +
            " #{patronymic}, " +
            "#{street}, #{house}," +
            " #{apartment}, #{login}" +
            ", #{password},#{token})")
    @Options(useGeneratedKeys = true)
    Integer insert(Voter voter);

    @Select("SELECT * FROM voter WHERE token = #{token}")
    Voter getByToken(String token);

    @Update("UPDATE voter SET token = ''" +
            " WHERE token = #{token} ")
    void logoutByToken(String token);

    @Update("UPDATE voter SET token = #{token}" +
            " WHERE login = #{login} ")
    void loginAndSetNewToken(@Param("login")String login, @Param("token")String token);

    @Select("SELECT * FROM voter WHERE id = #{id}")
    Voter getById(int id);

    @Select("SELECT * FROM voter")
    List<Voter> getAll();

    @Delete("DELETE FROM voter WHERE id = #{voter.id}")
    void delete(Voter voter);

}
