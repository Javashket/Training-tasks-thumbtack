package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.voter.LoginVoterErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestLogin {

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

    @BeforeEach()
    public void startServer() {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() {
        Server.stopServer(null);
    }

    @Test
    public void testLoginVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);

        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        assertEquals("", Server.logout(jsonRequest));

        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(request1.getLogin(),request1.getPassword());
        jsonRequest = new Gson().toJson(loginVoterDtoRequest);
        assertEquals("", Server.login(jsonRequest));
    }

    @Test
    public void testLoginVoterWithErrorLogin(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);

        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        assertEquals("", Server.logout(jsonRequest));

        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(request1.getLogin() + "0",request1.getPassword());
        jsonRequest = new Gson().toJson(loginVoterDtoRequest);
        LoginVoterErrorCode loginVoterErrorCode = new LoginVoterErrorCode();
        loginVoterErrorCode.setErrorString(loginVoterErrorCode.getNotFoundLogin());
        LoginVoterErrorCode actual = new Gson().fromJson(Server.login(jsonRequest), LoginVoterErrorCode.class);
        assertEquals(loginVoterErrorCode.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testLoginVoterWithErrorPassword(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        assertEquals("", Server.logout(jsonRequest));
        LoginVoterDtoRequest loginVoterDtoRequest = new LoginVoterDtoRequest(request1.getLogin(),request1.getPassword() + "0");
        jsonRequest = new Gson().toJson(loginVoterDtoRequest);
        LoginVoterErrorCode loginVoterErrorCode = new LoginVoterErrorCode();
        loginVoterErrorCode.setErrorString(loginVoterErrorCode.getErrorPassword());
        LoginVoterErrorCode actual = new Gson().fromJson(Server.login(jsonRequest), LoginVoterErrorCode.class);
        assertEquals(loginVoterErrorCode.getErrorString(), actual.getErrorString());
    }

}
