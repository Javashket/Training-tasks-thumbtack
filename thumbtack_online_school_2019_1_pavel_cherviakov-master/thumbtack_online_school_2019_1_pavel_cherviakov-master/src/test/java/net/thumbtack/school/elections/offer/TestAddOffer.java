package net.thumbtack.school.elections.offer;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.offer.AddOfferErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestAddOffer {

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
    public void startServer() {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() {
        Server.stopServer(null);
    }

    @Test
    public void testAddOffer(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request2 = new Offer(result1.getToken(), content);
        String jsonRequest2 = new Gson().toJson(request2);
        Server.addOffer(jsonRequest2);
        String jsonResponse2 = Server.getAllOffers();
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse2, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        expected.add(request2);
        assertEquals(expected, allOffersDtoResponse.getOffers());
    }

    @Test
    public void testAddSameOfferFromSameVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request2 = new Offer(result1.getToken(), content);
        String jsonRequest2 = new Gson().toJson(request2);
        Server.addOffer(jsonRequest2);
        String jsonResponse2 = Server.addOffer(jsonRequest2);
        AddOfferErrorCode actual = new Gson().fromJson(jsonResponse2, AddOfferErrorCode.class);
        AddOfferErrorCode expected = new AddOfferErrorCode();
        expected.setErrorString(expected.getSameOffer());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testAddSameOfferFromAnotherVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request3 = new Offer(result1.getToken(), content);
        Offer request4 = new Offer(result2.getToken(), content);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.addOffer(jsonRequest3);
        String jsonRequest4 = new Gson().toJson(request4);
        String jsonResponse3 = Server.addOffer(jsonRequest4);
        AddOfferErrorCode actual = new Gson().fromJson(jsonResponse3, AddOfferErrorCode.class);
        AddOfferErrorCode expected = new AddOfferErrorCode();
        expected.setErrorString(expected.getSameOffer());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testAddEmptyOffer(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String content = "";
        Offer request2 = new Offer(result1.getToken(), content);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 =  Server.addOffer(jsonRequest2);
        AddOfferErrorCode actual = new Gson().fromJson(jsonResponse2, AddOfferErrorCode.class);
        AddOfferErrorCode expected = new AddOfferErrorCode();
        expected.setErrorString(expected.getEmptyOffer());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
