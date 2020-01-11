package net.thumbtack.school.elections.offer;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.GetOffersSeveralCandidatesDtoRequest;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGetOffers extends Init {

    @Test
    public void testGetAllOffers(){
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

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        String content2 = "Вымостить тротуарной плиткой центральную площадь2.";
        String content3 = "Вымостить тротуарной плиткой центральную площадь3.";
        String content4 = "Вымостить тротуарной плиткой центральную площадь4.";

        Offer requestAddOffer1 = new Offer(resultRegister1.getToken(), content1);
        Offer requestAddOffer2 = new Offer(resultRegister1.getToken(), content2);
        Offer requestAddOffer3 = new Offer(resultRegister1.getToken(), content3);
        Offer requestAddOffer4 = new Offer(resultRegister1.getToken(), content4);

        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        String jsonRequestAddOffer2 = new Gson().toJson(requestAddOffer2);
        String jsonRequestAddOffer3 = new Gson().toJson(requestAddOffer3);
        String jsonRequestAddOffer4 = new Gson().toJson(requestAddOffer4);

        Server.addOffer(jsonRequestAddOffer1);
        Server.addOffer(jsonRequestAddOffer2);
        Server.addOffer(jsonRequestAddOffer3);
        Server.addOffer(jsonRequestAddOffer4);

        RateOfferDtoRequest rateOfferRequest1 = new RateOfferDtoRequest(content1, resultRegister2.getToken(), 5);
        RateOfferDtoRequest rateOfferRequest2 = new RateOfferDtoRequest(content2, resultRegister2.getToken(), 2);
        RateOfferDtoRequest rateOfferRequest3 = new RateOfferDtoRequest(content3, resultRegister2.getToken(), 3);
        RateOfferDtoRequest rateOfferRequest4 = new RateOfferDtoRequest(content4, resultRegister2.getToken(), 1);

        String jsonRequestRateOffer1 = new Gson().toJson(rateOfferRequest1);
        String jsonRequestRateOffer2 = new Gson().toJson(rateOfferRequest2);
        String jsonRequestRateOffer3 = new Gson().toJson(rateOfferRequest3);
        String jsonRequestRateOffer4 = new Gson().toJson(rateOfferRequest4);

        Server.rateOffer(jsonRequestRateOffer1);
        Server.rateOffer(jsonRequestRateOffer2);
        Server.rateOffer(jsonRequestRateOffer3);
        Server.rateOffer(jsonRequestRateOffer4);

        TokenVoterDtoRequest tokenVoterRequest = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonTokenVoterRequest = new Gson().toJson(tokenVoterRequest);
        String jsonResponseAllOffers = Server.getAllOffers(jsonTokenVoterRequest);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponseAllOffers, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        List<Offer> actual = allOffersDtoResponse.getOffers();

        expected.add(requestAddOffer1);
        expected.add(requestAddOffer2);
        expected.add(requestAddOffer3);
        expected.add(requestAddOffer4);

        requestAddOffer1.addRate(new Rating(resultRegister2.getToken(), 5));
        requestAddOffer2.addRate(new Rating(resultRegister2.getToken(), 2));
        requestAddOffer3.addRate(new Rating(resultRegister2.getToken(), 3));
        requestAddOffer4.addRate(new Rating(resultRegister2.getToken(), 1));

        Collections.sort(expected);
        assertEquals(expected, actual);
        assertEquals(expected.get(0), actual.get(0));
        assertEquals(expected.get(3), actual.get(3));
    }

    @Test
    public void testGetAllOffersWithInvalidToken(){
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        Offer requestAddOffer1 = new Offer(resultRegister1.getToken(), content1);
        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        Server.addOffer(jsonRequestAddOffer1);

        TokenVoterDtoRequest tokenVoterRequest = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonTokenVoterRequest1 = new Gson().toJson(tokenVoterRequest);
        Server.logout(jsonTokenVoterRequest1);
        String jsonResponseAllOffers = Server.getAllOffers(jsonTokenVoterRequest1);
        TokenVoterErrorCode expected = new TokenVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        TokenVoterErrorCode actual = new Gson().fromJson(jsonResponseAllOffers, TokenVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testGetOffersSeveralCandidates(){
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

        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3","Иванов3",
                "Иванович3","улица3","дом3", "563","logpass3","passlogpass3");
        String jsonRequestRegister3 = new Gson().toJson(requestRegister3);
        String jsonResponseRegister3 = Server.registerVoter(jsonRequestRegister3);
        RegisterVoterDtoResponse resultRegister3 = new Gson().fromJson(jsonResponseRegister3, RegisterVoterDtoResponse.class);

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        String content2 = "Вымостить тротуарной плиткой центральную площадь2.";
        String content3 = "Вымостить тротуарной плиткой центральную площадь3.";

        Offer requestAddOffer1 = new Offer(resultRegister1.getToken(), content1);
        Offer requestAddOffer2 = new Offer(resultRegister2.getToken(), content2);
        Offer requestAddOffer3 = new Offer(resultRegister3.getToken(), content3);

        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        String jsonRequestAddOffer2 = new Gson().toJson(requestAddOffer2);
        String jsonRequestAddOffer3 = new Gson().toJson(requestAddOffer3);

        Server.addOffer(jsonRequestAddOffer1);
        Server.addOffer(jsonRequestAddOffer2);
        Server.addOffer(jsonRequestAddOffer3);

        GetOffersSeveralCandidatesDtoRequest getOffersSeveralCandidatesRequest = new GetOffersSeveralCandidatesDtoRequest(resultRegister1.getToken());
        getOffersSeveralCandidatesRequest.addCandidateToken(resultRegister1.getToken());
        getOffersSeveralCandidatesRequest.addCandidateToken(resultRegister2.getToken());
        String jsonGetOffersSeveralCandidatesRequest = new Gson().toJson(getOffersSeveralCandidatesRequest);
        String jsonResponseOffersSeveralCandidates = Server.getOffersSeveralCandidates(jsonGetOffersSeveralCandidatesRequest);
        AllOffersDtoResponse OffersSeveralCandidatesResponse = new Gson().fromJson(jsonResponseOffersSeveralCandidates, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        expected.add(requestAddOffer1);
        expected.add(requestAddOffer2);
        List<Offer> actual = OffersSeveralCandidatesResponse.getOffers();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetOffersSeveralCandidatesWithInvalidToken(){
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

        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3","Иванов3",
                "Иванович3","улица3","дом3", "563","logpass3","passlogpass3");
        String jsonRequestRegister3 = new Gson().toJson(requestRegister3);
        String jsonResponseRegister3 = Server.registerVoter(jsonRequestRegister3);
        RegisterVoterDtoResponse resultRegister3 = new Gson().fromJson(jsonResponseRegister3, RegisterVoterDtoResponse.class);

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        String content2 = "Вымостить тротуарной плиткой центральную площадь2.";
        String content3 = "Вымостить тротуарной плиткой центральную площадь3.";

        Offer requestAddOffer1 = new Offer(resultRegister1.getToken(), content1);
        Offer requestAddOffer2 = new Offer(resultRegister1.getToken(), content2);
        Offer requestAddOffer3 = new Offer(resultRegister1.getToken(), content3);

        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        String jsonRequestAddOffer2 = new Gson().toJson(requestAddOffer2);
        String jsonRequestAddOffer3 = new Gson().toJson(requestAddOffer3);

        Server.addOffer(jsonRequestAddOffer1);
        Server.addOffer(jsonRequestAddOffer2);
        Server.addOffer(jsonRequestAddOffer3);

        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonTokenVoterDtoRequest = new Gson().toJson(tokenVoterDtoRequest);
        Server.logout(jsonTokenVoterDtoRequest);

        GetOffersSeveralCandidatesDtoRequest getOffersSeveralCandidatesRequest = new GetOffersSeveralCandidatesDtoRequest(resultRegister1.getToken());
        getOffersSeveralCandidatesRequest.addCandidateToken(resultRegister1.getToken());
        getOffersSeveralCandidatesRequest.addCandidateToken(resultRegister2.getToken());
        String jsonGetOffersSeveralCandidatesRequest = new Gson().toJson(getOffersSeveralCandidatesRequest);
        String jsonResponseOffersSeveralCandidates = Server.getOffersSeveralCandidates(jsonGetOffersSeveralCandidatesRequest);
        TokenVoterErrorCode expected = new Gson().fromJson(jsonResponseOffersSeveralCandidates, TokenVoterErrorCode.class);
        TokenVoterErrorCode actual = new TokenVoterErrorCode();
        actual.setErrorString(actual.getNotFoundToken());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

}
