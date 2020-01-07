package net.thumbtack.school.elections.offer;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.offer.RateOfferErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestRateOffer {

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
    public void testRateOffer(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request3 = new Offer(result1.getToken(), content);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.addOffer(jsonRequest3);
        RateOfferDtoRequest request4 = new RateOfferDtoRequest(content, result2.getToken(), 4);
        String jsonRequest4 = new Gson().toJson(request4);
        Server.rateOffer(jsonRequest4);
        String jsonResponse5 = Server.getAllOffers();
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse5, AllOffersDtoResponse.class);
        Offer actual = new Offer();
        for(Offer offer : allOffersDtoResponse.getOffers()) {
            if(offer.getContent().equals(content)) {
                actual = offer;
            }
        }
        assertEquals(request3.getRatings(), actual.getRatings());
        // добавить проверку валидности токена во все методы
    }

    @Test
    public void testRateOfferOnDeletePrevRating() {
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request3 = new Offer(result1.getToken(), content);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.addOffer(jsonRequest3);
        RateOfferDtoRequest request4 = new RateOfferDtoRequest(content, result2.getToken(), 4);
        String jsonRequest4 = new Gson().toJson(request4);
        Server.rateOffer(jsonRequest4);
        RateOfferDtoRequest request5 = new RateOfferDtoRequest(content, result2.getToken(), 5);
        String jsonRequest5 = new Gson().toJson(request5);
        Server.rateOffer(jsonRequest5);
        String jsonResponse6 = Server.getAllOffers();
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse6, AllOffersDtoResponse.class);
        int actual_rating = 0;
        for(Offer offer : allOffersDtoResponse.getOffers()) {
            if(offer.getContent().equals(content)) {
                for(Rating rating1 : offer.getRatings()) {
                    if(rating1.getToken_evaluating_voter().equals(result2.getToken())) {
                        actual_rating = rating1.getRating();
                    }
                }
            }
        }
        assertNotEquals(4, actual_rating);
    }

    @Test
    public void testRateOfferTryingAuthorRateAgain() {
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request3 = new Offer(result1.getToken(), content);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.addOffer(jsonRequest3);
        RateOfferDtoRequest request4 = new RateOfferDtoRequest(content, result1.getToken(), 4);
        String jsonRequest4 = new Gson().toJson(request4);
        String jsonResponse2 = Server.rateOffer(jsonRequest4);
        RateOfferErrorCode actual = new Gson().fromJson(jsonResponse2, RateOfferErrorCode.class);
        RateOfferErrorCode expected = new RateOfferErrorCode();
        expected.setErrorString(expected.getRatingAuthorConst());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
