package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.mayorcandidate.LogoutMayorErrorCode;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestLogout extends Init {

    @Test
    public void testLogoutVoter() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);

        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        assertEquals("", Server.logout(jsonRequestLogout));

        LogoutVoterErrorCode expected = new LogoutVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        LogoutVoterErrorCode actual = new Gson().fromJson(Server.logout(jsonRequestRegister), LogoutVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLogoutVoterCheckDeleteAuthorOffers() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2", "Иванов2",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);

        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAdd = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAdd = new Gson().toJson(requestAdd);
        Server.addOffer(jsonRequestAdd);
        Server.logout(jsonRequestLogout);

        TokenVoterDtoRequest requestAllOffers = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestAllOffers = new Gson().toJson(requestAllOffers);
        String jsonResponseAllOffers = Server.getAllOffers(jsonRequestAllOffers);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponseAllOffers, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        requestAdd.setAuthor_token(null);
        expected.add(requestAdd);
        assertEquals(expected, allOffersDtoResponse.getOffers());
    }

    @Test
    public void testLogoutVoterCheckDeleteRatings() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2", "Иванов2",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);

        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAdd = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAdd = new Gson().toJson(requestAdd);
        Server.addOffer(jsonRequestAdd);

        RateOfferDtoRequest requestRate2 = new RateOfferDtoRequest(content, resultRegister2.getToken(), 4);
        String jsonRequestRate2 = new Gson().toJson(requestRate2);
        Server.rateOffer(jsonRequestRate2);
        Server.logout(jsonRequestLogout);

        TokenVoterDtoRequest requestAllOffers = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestAllOffers = new Gson().toJson(requestAllOffers);
        String jsonResponseAllOffers = Server.getAllOffers(jsonRequestAllOffers);
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponseAllOffers, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        expected.add(requestAdd);
        assertEquals(expected, allOffersDtoResponse.getOffers());
    }

    @Test
    public void testLogoutVoterWhenIsConsentMayorCandidate() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2", "Иванов2",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);

        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        PutOnMayorDtoRequest putOnMayorDtoRequest = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonRequestPutOnMayor = new Gson().toJson(putOnMayorDtoRequest);
        Server.putOnMayor(jsonRequestPutOnMayor);
        Server.consentOnPositionOnMayor(jsonRequestLogout);

        String responseLogout2 = Server.logout(jsonRequestLogout);
        LogoutMayorErrorCode actual = new Gson().fromJson(responseLogout2, LogoutMayorErrorCode.class);
        LogoutMayorErrorCode expected = new LogoutMayorErrorCode();
        expected.setErrorString(expected.getLogoutMayor());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLogoutVoterWhenIsNotConsentMayorCandidate() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2", "Иванов2",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);

        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        PutOnMayorDtoRequest putOnMayorDtoRequest = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonRequestPutOnMayor = new Gson().toJson(putOnMayorDtoRequest);
        Server.putOnMayor(jsonRequestPutOnMayor);

        assertEquals("", Server.logout(jsonRequestLogout));
    }
}
