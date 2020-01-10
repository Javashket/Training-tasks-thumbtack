package net.thumbtack.school.elections.mayorcandidate;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.mayorcandidate.AddMayorCandidateErrorCode;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPutOnMayorCandidate extends Init {

    @Test
    public void testPutOnMayorCandidateYourself(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        PutOnMayorDtoRequest request2 = new PutOnMayorDtoRequest(result1.getToken(), request1);
        String jsonRequest2 = new Gson().toJson(request2);
        Server.putOnMayor(jsonRequest2);
        TokenVoterDtoRequest tokenVoterDtoRequest3 = new TokenVoterDtoRequest(result1.getToken());
        String jsonRequest3 = new Gson().toJson(tokenVoterDtoRequest3);
        String jsonResponse2 = Server.getAllCandidates(jsonRequest3);
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponse2, AllCandidatesDtoResponse.class);
        boolean actual = false;
        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if (mayorCandidate.getToken_voter().equals(result1.getToken()) && mayorCandidate.isConsentOnNomination()) {
                // проверка вставки прдложений
                actual = true;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    public void testPutOnMayorCandidateAnother(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest request3 = new PutOnMayorDtoRequest(result1.getToken(), request2);
        String jsonRequest3 = new Gson().toJson(request3);
        Server.putOnMayor(jsonRequest3);
        TokenVoterDtoRequest tokenVoterDtoRequest4 = new TokenVoterDtoRequest(result1.getToken());
        String jsonRequest4 = new Gson().toJson(tokenVoterDtoRequest4);
        String jsonResponse3 = Server.getAllCandidates(jsonRequest4);
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponse3, AllCandidatesDtoResponse.class);
        boolean actual = false;
        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if (mayorCandidate.getToken_voter().equals(result2.getToken()) && !mayorCandidate.isConsentOnNomination()) {
                actual = true;
                break;
            }
        }
        assertTrue(actual);
    }

    @Test
    public void testPutOnMayorCandidateIsFalsePushingVoterToken(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        PutOnMayorDtoRequest request2 = new PutOnMayorDtoRequest(result1.getToken(), request1);
        String jsonRequest2 = new Gson().toJson(request2);
        TokenVoterDtoRequest request3 = new TokenVoterDtoRequest(result1.getToken());
        String jsonRequest3 = new Gson().toJson(request3);
        Server.logout(jsonRequest3);
        String jsonResponse2 = Server.putOnMayor(jsonRequest2);
        TokenVoterErrorCode actual = new Gson().fromJson(jsonResponse2, TokenVoterErrorCode.class);
        TokenVoterErrorCode expected = new TokenVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testPutOnMayorCandidateIsFalseTokenVoterOnMayor(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest request3 = new PutOnMayorDtoRequest(result1.getToken(), request2);
        String jsonRequest3 = new Gson().toJson(request3);
        TokenVoterDtoRequest request4 = new TokenVoterDtoRequest(result2.getToken());
        String jsonRequest4 = new Gson().toJson(request4);
        Server.logout(jsonRequest4);
        String jsonResponse3 = Server.putOnMayor(jsonRequest3);
        AddMayorCandidateErrorCode actual = new Gson().fromJson(jsonResponse3, AddMayorCandidateErrorCode.class);
        AddMayorCandidateErrorCode expected = new AddMayorCandidateErrorCode();
        expected.setErrorString(expected.getNotMayorCandidate());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
