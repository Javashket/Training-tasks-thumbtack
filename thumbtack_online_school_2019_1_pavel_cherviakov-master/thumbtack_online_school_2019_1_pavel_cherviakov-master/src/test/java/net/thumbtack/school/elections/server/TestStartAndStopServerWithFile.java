package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestStartAndStopServerWithFile {

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

    @Test
    public void testStopAndStartServerSaveDataToFileAndReadDataFromFile() throws IOException {
        Server.startServer(null);

        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer1 = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        Server.addOffer(jsonRequestAddOffer1);
        RateOfferDtoRequest requestRateOffer2 = new RateOfferDtoRequest(content, resultRegister2.getToken(), 4);
        String jsonRequestRateOffer2 = new Gson().toJson(requestRateOffer2);
        Server.rateOffer(jsonRequestRateOffer2);
        String path = "AllDataTest.txt";
        Server.stopServer(path);

        Server.startServer(path);
        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestTokenVoter = new Gson().toJson(tokenVoterDtoRequest);
        String jsonResponseAllOffers = Server.getAllOffers(jsonRequestTokenVoter);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponseAllOffers, AllOffersDtoResponse.class);
        Rating rating = new Rating(resultRegister2.getToken(), 4);
        requestAddOffer1.addRate(rating);
        assertEquals(requestAddOffer1.getRatings(), allOffersDtoResponse.getOffers());

        Server.stopServer(null);
    }

}
