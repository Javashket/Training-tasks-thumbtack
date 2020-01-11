package net.thumbtack.school.elections.mayorcandidate;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestGetAllCandidate extends Init {

    @Test
    public void testGetAllCandidate(){
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

        PutOnMayorDtoRequest requestPutOnMayor1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister1);
        String jsonRequestPutOnMayor1 = new Gson().toJson(requestPutOnMayor1);
        Server.putOnMayor(jsonRequestPutOnMayor1);

        PutOnMayorDtoRequest requestPutOnMayor2 = new PutOnMayorDtoRequest(resultRegister2.getToken(), requestRegister2);
        String jsonRequestPutOnMayor2 = new Gson().toJson(requestPutOnMayor2);
        Server.putOnMayor(jsonRequestPutOnMayor2);

        TokenVoterDtoRequest requestGetAllCandidates = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestGetAllCandidates  = new Gson().toJson(requestGetAllCandidates);
        String jsonResponseGetAllCandidates = Server.getAllCandidates(jsonRequestGetAllCandidates);
        AllCandidatesDtoResponse actual = new Gson().fromJson(jsonResponseGetAllCandidates, AllCandidatesDtoResponse.class);
        List<RegisterVoterDtoRequest> expected = new ArrayList<>();
        expected.add(requestRegister1);
        expected.add(requestRegister2);
        assertNotEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    public void testGetAllCandidateWithInvalidToken(){
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

        PutOnMayorDtoRequest requestPutOnMayor1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister1);
        String jsonRequestPutOnMayor1 = new Gson().toJson(requestPutOnMayor1);
        Server.putOnMayor(jsonRequestPutOnMayor1);

        PutOnMayorDtoRequest requestPutOnMayor2 = new PutOnMayorDtoRequest(resultRegister2.getToken(), requestRegister2);
        String jsonRequestPutOnMayor2 = new Gson().toJson(requestPutOnMayor2);
        Server.putOnMayor(jsonRequestPutOnMayor2);

        TokenVoterDtoRequest requestGetAllCandidates = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestGetAllCandidates  = new Gson().toJson(requestGetAllCandidates);
        Server.logout(jsonRequestGetAllCandidates);
        String jsonResponseGetAllCandidates = Server.getAllCandidates(jsonRequestGetAllCandidates);
        TokenVoterErrorCode actual = new Gson().fromJson(jsonResponseGetAllCandidates, TokenVoterErrorCode.class);
        TokenVoterErrorCode expected = new TokenVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());

        assertEquals(expected.getErrorString(), actual.getErrorString());

    }

}
