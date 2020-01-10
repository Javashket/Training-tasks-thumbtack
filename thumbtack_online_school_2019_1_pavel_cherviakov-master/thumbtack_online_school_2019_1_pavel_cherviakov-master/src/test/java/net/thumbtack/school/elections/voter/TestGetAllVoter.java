package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllVoter extends Init {

    @Test
    public void testGetAllVoter() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3", "Иванов3",
                "Иванович3", "улица3", "дом3", "563", "logpass3", "passlogpass3");

        String responseRegister1 = Server.registerVoter(new Gson().toJson(requestRegister1));
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(responseRegister1, RegisterVoterDtoResponse.class);
        Server.registerVoter(new Gson().toJson(requestRegister2));
        Server.registerVoter(new Gson().toJson(requestRegister3));

        List<RegisterVoterDtoRequest> expected = new ArrayList<>();
        expected.add(requestRegister1);
        expected.add(requestRegister2);
        expected.add(requestRegister3);
        TokenVoterDtoRequest tokenVoterDtoRequest1 = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestGetAllVoters1 = new Gson().toJson(tokenVoterDtoRequest1);
        AllVotersDtoResponse actual = new Gson().fromJson(Server.getAllVoters(jsonRequestGetAllVoters1), AllVotersDtoResponse.class);

        assertEquals(expected.hashCode(), actual.getVoters().hashCode());
    }

    @Test
    public void testGetAllVoterWithInvalidToken() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1", "Иванов1",
                "Иванович1", "улица1", "дом1", "561", "logpass1", "passlogpass1");
        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович2", "улица2", "дом2", "562", "logpass2", "passlogpass2");
        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3", "Иванов3",
                "Иванович3", "улица3", "дом3", "563", "logpass3", "passlogpass3");

        String responseRegister1 = Server.registerVoter(new Gson().toJson(requestRegister1));
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(responseRegister1, RegisterVoterDtoResponse.class);
        Server.registerVoter(new Gson().toJson(requestRegister2));
        Server.registerVoter(new Gson().toJson(requestRegister3));

        TokenVoterDtoRequest tokenVoterDtoRequest1 = new TokenVoterDtoRequest(resultRegister1.getToken());
        String jsonRequestLogout1 = new Gson().toJson(tokenVoterDtoRequest1);
        Server.logout(jsonRequestLogout1);

        LogoutVoterErrorCode actual = new Gson().fromJson(Server.getAllVoters(jsonRequestLogout1), LogoutVoterErrorCode.class);
        LogoutVoterErrorCode expected = new LogoutVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
