package net.thumbtack.school.elections.offer;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.offer.RateOfferErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestRateOffer extends Init {

    @Test
    public void testRateOffer(){
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
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        RateOfferDtoRequest requestRateOffer2 = new RateOfferDtoRequest(content, resultRegister2.getToken(), 4);
        String jsonRequestRateOffer2 = new Gson().toJson(requestRateOffer2);
        Server.rateOffer(jsonRequestRateOffer2);
        TokenVoterDtoRequest tokenVoterRequest = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest = new Gson().toJson(tokenVoterRequest);
        String jsonResponse5 = Server.getAllOffers(jsonTokenVoterRequest);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse5, AllOffersDtoResponse.class);
        Offer actual = new Offer();
        for(Offer offer : allOffersDtoResponse.getOffers()) {
            if(offer.getContent().equals(content)) {
                actual = offer;
            }
        }
        assertEquals(requestAddOffer.getRatings(), actual.getRatings());
        // добавить проверку валидности токена во все методы
    }

    @Test
    public void testRateOfferOnDeletePrevRating() {
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
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        RateOfferDtoRequest requestRateOffer2 = new RateOfferDtoRequest(content, resultRegister2.getToken(), 4);
        String jsonRequestRateOffer2 = new Gson().toJson(requestRateOffer2);
        Server.rateOffer(jsonRequestRateOffer2);
        RateOfferDtoRequest requestRateOffer2Again = new RateOfferDtoRequest(content, resultRegister2.getToken(), 5);
        String jsonRequestRateOffer2Again = new Gson().toJson(requestRateOffer2Again);
        Server.rateOffer(jsonRequestRateOffer2Again);

        TokenVoterDtoRequest tokenVoterRequest = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest = new Gson().toJson(tokenVoterRequest);
        String jsonResponseAllOffers = Server.getAllOffers(jsonTokenVoterRequest);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponseAllOffers, AllOffersDtoResponse.class);
        int actual_rating = 0;
        for(Offer offer : allOffersDtoResponse.getOffers()) {
            if(offer.getContent().equals(content)) {
                for(Rating rating1 : offer.getRatings()) {
                    if(rating1.getToken_evaluating_voter().equals(resultRegister2.getToken())) {
                        actual_rating = rating1.getRating();
                    }
                }
            }
        }
        assertNotEquals(4, actual_rating);
    }

    @Test
    public void testRateOfferTryingAuthorRateAgain() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        RateOfferDtoRequest requestRateOffer = new RateOfferDtoRequest(content, resultRegister.getToken(), 4);

        String jsonRequestRateOffer = new Gson().toJson(requestRateOffer);
        String jsonResponseRateOffer = Server.rateOffer(jsonRequestRateOffer);
        RateOfferErrorCode actual = new Gson().fromJson(jsonResponseRateOffer, RateOfferErrorCode.class);
        RateOfferErrorCode expected = new RateOfferErrorCode();
        expected.setErrorString(expected.getRatingAuthorConst());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
