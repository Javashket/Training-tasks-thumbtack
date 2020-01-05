package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestLogout {

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
    public void testLogoutVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest request2 = new TokenVoterDtoRequest(result.getToken());
        jsonRequest = new Gson().toJson(request2);
        LogoutVoterErrorCode expected = new LogoutVoterErrorCode();
        expected.setErrorString(expected.getNotFoundToken());
        assertEquals("", Server.logout(jsonRequest));
        LogoutVoterErrorCode actual = new Gson().fromJson(Server.logout(jsonRequest), LogoutVoterErrorCode.class);
        assertEquals(expected.getErrorString(),actual.getErrorString());
    }

}
