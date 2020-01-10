package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.voter.LoginVoterErrorCode;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestLogin extends Init {

    @Test
    public void testLoginVoter() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);

        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        assertEquals("", Server.logout(jsonRequestLogout));

        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(requestRegister.getLogin(), requestRegister.getPassword());
        String jsonRequestLogin = new Gson().toJson(loginVoterDtoRequest);
        assertEquals("", Server.login(jsonRequestLogin));
    }

    @Test
    public void testLoginVoterWithErrorLogin() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);

        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        assertEquals("", Server.logout(jsonRequestLogout));

        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(requestRegister.getLogin() + "0", requestRegister.getPassword());
        String jsonRequestLogint = new Gson().toJson(loginVoterDtoRequest);
        LoginVoterErrorCode expected = new LoginVoterErrorCode();
        expected.setErrorString(expected.getNotFoundLogin());
        LoginVoterErrorCode actual = new Gson().fromJson(Server.login(jsonRequestLogint), LoginVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLoginVoterWithErrorPassword() {
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван", "Иванов",
                "Иванович", "улица", "дом", "56", "logpass", "passlogpass");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);

        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest requestLogout = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonRequestLogout = new Gson().toJson(requestLogout);
        assertEquals("", Server.logout(jsonRequestLogout));

        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(requestRegister.getLogin(), requestRegister.getPassword() + "0");
        String jsonRequestLogin = new Gson().toJson(loginVoterDtoRequest);
        LoginVoterErrorCode expected = new LoginVoterErrorCode();
        expected.setErrorString(expected.getErrorPassword());
        LoginVoterErrorCode actual = new Gson().fromJson(Server.login(jsonRequestLogin), LoginVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
