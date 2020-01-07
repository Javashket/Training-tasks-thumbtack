package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestLogout {

    private static boolean setUpIsDone = false;

    @BeforeAll()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    @BeforeEach()
    public void startServer() throws IOException {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() throws IOException {
        Server.stopServer(null);
    }

    @Test
    public void testLogoutVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        LogoutVoterErrorCode expected = new LogoutVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        assertEquals("", Server.logout(jsonRequest));
        LogoutVoterErrorCode actual = new Gson().fromJson(Server.logout(jsonRequest), LogoutVoterErrorCode.class);
        assertEquals(expected.getErrorString(),actual.getErrorString());
    }

    @Test
    public void testLogoutVoterCheckDeleteAuthorOffers(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result1.getToken());
        String jsonRequest2 = new Gson().toJson(request2);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request3 = new Offer(result1.getToken(), content);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.addOffer(jsonRequest3);
        Server.logout(jsonRequest2);
        String jsonResponse2 = Server.getAllOffers();
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse2, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        request3.setAuthor_token("");
        expected.add(request3);
        assertEquals(expected, allOffersDtoResponse.getOffers());
    }

    @Test
    public void testLogoutVoterCheckDeleteRatings(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        LogoutVoterErrorCode expected = new LogoutVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        assertEquals("", Server.logout(jsonRequest));
        LogoutVoterErrorCode actual = new Gson().fromJson(Server.logout(jsonRequest), LogoutVoterErrorCode.class);
        assertEquals(expected.getErrorString(),actual.getErrorString());
        fail();
    }

}
