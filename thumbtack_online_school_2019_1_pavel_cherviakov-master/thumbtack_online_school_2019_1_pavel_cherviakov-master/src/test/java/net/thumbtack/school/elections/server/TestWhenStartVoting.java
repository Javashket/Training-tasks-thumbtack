package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.model.Offer;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestWhenStartVoting extends Init {

    @Test
    public void testRegisterVoter() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        Server.startVoting();
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseRegister, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLogout() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse registerVoterDtoResponse = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(registerVoterDtoResponse.getToken());
        String requestLogout = new Gson().toJson(tokenVoterDtoRequest);
        Server.startVoting();
        String jsonResponseLogout = Server.logout(requestLogout);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseLogout, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLogin() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse registerVoterDtoResponse = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(registerVoterDtoResponse.getToken());
        String requestLogout = new Gson().toJson(tokenVoterDtoRequest);
        String jsonResponseLogout = Server.logout(requestLogout);
        Server.startVoting();
        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(requestRegister.getLogin(), requestRegister.getPassword());
        String requestLogin = new Gson().toJson(loginVoterDtoRequest);
        String jsonResponseLogin = Server.logout(requestLogin);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseLogin, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testAddOffer() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.startVoting();
        String jsonResponseAddOffer = Server.addOffer(jsonRequestAddOffer);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseAddOffer, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRateOffer() {
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

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        Server.startVoting();
        RateOfferDtoRequest rateOfferDtoRequest = new RateOfferDtoRequest(content, resultRegister2.getToken(), 5);
        String jsonRateOfferDtoRequest = new Gson().toJson(rateOfferDtoRequest);
        String jsonResponseRateOffer = Server.rateOffer(jsonRateOfferDtoRequest);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseRateOffer, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testDeleteRatingFromOffer() {
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

        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        RateOfferDtoRequest rateOfferDtoRequest = new RateOfferDtoRequest(content, resultRegister2.getToken(), 5);
        String jsonRateOfferDtoRequest = new Gson().toJson(rateOfferDtoRequest);
        Server.rateOffer(jsonRateOfferDtoRequest);
        Server.startVoting();

        DeleteRatingDtoRequest deleteRatingDtoRequest = new DeleteRatingDtoRequest(content, resultRegister2.getToken());
        String jsonDeleteRatingDtoRequest = new Gson().toJson(deleteRatingDtoRequest);
        String jsonResponseDeleteRating = Server.deleteRatingFromOffer(jsonDeleteRatingDtoRequest);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseDeleteRating, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testPutOnMayor() {
        Server.startVoting();
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest requestPutOnMayor = new PutOnMayorDtoRequest(resultRegister.getToken(), requestRegister);
        String jsonRequestPutOnMayor = new Gson().toJson(requestPutOnMayor);
        Server.startVoting();
        String jsonResponsePutOnMayor = Server.putOnMayor(jsonRequestPutOnMayor);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponsePutOnMayor, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testWithdrawCandidateWithMayor() {
        Server.startVoting();
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest requestPutOnMayor = new PutOnMayorDtoRequest(resultRegister.getToken(), requestRegister);
        String jsonRequestPutOnMayor = new Gson().toJson(requestPutOnMayor);
        Server.putOnMayor(jsonRequestPutOnMayor);
        Server.startVoting();
        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonTokenVoterDtoRequest = new Gson().toJson(tokenVoterDtoRequest);
        String jsonResponseWithdrawCandidateWithMayor = Server.withdrawCandidateWithMayor(jsonTokenVoterDtoRequest);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseWithdrawCandidateWithMayor, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testConsentOnPositionOnMayor() {
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

        PutOnMayorDtoRequest putOnMayorRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonPutOnMayorRequest1 = new Gson().toJson(putOnMayorRequest1);
        Server.putOnMayor(jsonPutOnMayorRequest1);

        TokenVoterDtoRequest tokenVoterRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest2 = new Gson().toJson(tokenVoterRequest2);
        Server.startVoting();
        String jsonResponseConsentOnPositionOnMayor = Server.consentOnPositionOnMayor(jsonTokenVoterRequest2);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponseConsentOnPositionOnMayor, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testIncludeOfferInYourProgram() {
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

        PutOnMayorDtoRequest putOnMayorRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonPutOnMayorRequest1 = new Gson().toJson(putOnMayorRequest1);
        Server.putOnMayor(jsonPutOnMayorRequest1);

        TokenVoterDtoRequest tokenVoterRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest2 = new Gson().toJson(tokenVoterRequest2);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest2);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);
        Server.startVoting();
        IncludeOfferDtoRequest includeOfferRequest = new IncludeOfferDtoRequest(content);
        String jsonIncludeOfferRequest = new Gson().toJson(includeOfferRequest);
        String jsonIncludeOfferResponse = Server.includeOfferInYourProgram(jsonIncludeOfferRequest);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonIncludeOfferResponse, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());

    }

    @Test
    public void testDeleteOfferFromYourProgram() {
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

        PutOnMayorDtoRequest putOnMayorRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonPutOnMayorRequest1 = new Gson().toJson(putOnMayorRequest1);
        Server.putOnMayor(jsonPutOnMayorRequest1);

        TokenVoterDtoRequest tokenVoterRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest2 = new Gson().toJson(tokenVoterRequest2);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest2);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer requestAddOffer = new Offer(resultRegister1.getToken(), content);
        String jsonRequestAddOffer = new Gson().toJson(requestAddOffer);
        Server.addOffer(jsonRequestAddOffer);

        IncludeOfferDtoRequest includeOfferRequest = new IncludeOfferDtoRequest(content);
        String jsonIncludeOfferRequest = new Gson().toJson(includeOfferRequest);
        Server.includeOfferInYourProgram(jsonIncludeOfferRequest);
        Server.startVoting();

        DeleteOfferInYourProgramDtoRequest deleteOfferInYourProgramRequest = new DeleteOfferInYourProgramDtoRequest(resultRegister1.getToken(),content);
        String jsonDeleteOfferInYourProgramRequest = new Gson().toJson(deleteOfferInYourProgramRequest);
        String jsonDeleteOfferResponse = Server.deleteOfferFromYourProgram(jsonDeleteOfferInYourProgramRequest);

        VotingOperationsErrorCode actual = new Gson().fromJson(jsonDeleteOfferResponse, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
