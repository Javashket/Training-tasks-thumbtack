package net.thumbtack.school.elections.mayorcandidate;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.mayorcandidate.TokenMayorErrorCode;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestConsentOnNomination extends Init {

    @Test
    public void testConsentOnMayorCandidate(){
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

        PutOnMayorDtoRequest putOnMayorRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonPutOnMayorRequest1 = new Gson().toJson(putOnMayorRequest1);
        Server.putOnMayor(jsonPutOnMayorRequest1);

        TokenVoterDtoRequest tokenVoterRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonTokenVoterRequest2 = new Gson().toJson(tokenVoterRequest2);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest2);

        TokenVoterDtoRequest tokenVoterRequest1 = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonTokenVoterRequest1 = new Gson().toJson(tokenVoterRequest1);
        String jsonResponseAllCandidates = Server.getAllCandidates(jsonTokenVoterRequest1);
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponseAllCandidates, AllCandidatesDtoResponse.class);
        boolean actual = false;

        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if (mayorCandidate.getToken_voter().equals(resultRegister2.getToken()) && mayorCandidate.isConsentOnNomination()) {
                actual = true;
                break;
            }
        }
        assertTrue(actual);


        // добавить включений программ

    }

    @Test
    public void testConsentOnMayorCandidateWithFalseToken(){
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

        PutOnMayorDtoRequest requestPutOnMayor = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister2);
        String jsonRequestPutOnMayor = new Gson().toJson(requestPutOnMayor);
        Server.putOnMayor(jsonRequestPutOnMayor);

        TokenVoterDtoRequest tokenVoterDtoRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonRequestTokenVoterDtoRequest2 = new Gson().toJson(tokenVoterDtoRequest2);
        Server.logout(jsonRequestTokenVoterDtoRequest2);
        String jsonResponseConsentOnPosition2 = Server.consentOnPositionOnMayor(jsonRequestTokenVoterDtoRequest2);
        TokenMayorErrorCode expected = new TokenMayorErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        TokenMayorErrorCode actual = new Gson().fromJson(jsonResponseConsentOnPosition2, TokenMayorErrorCode.class);
        assertEquals(actual.getErrorString(), expected.getErrorString());
    }

    @Test
    public void testConsentAlreadyAgreedOnMayorCandidate(){




    }

}
