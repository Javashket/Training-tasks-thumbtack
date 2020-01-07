package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.errors.voting.VotingOperationsErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestWhenStartVoting {

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
    public void startServer() throws IOException {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() throws IOException {
        Server.stopServer(null);
    }

    @Test
    public void testRegisterVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request1);
        Server.startVoting();
        String jsonResponse = Server.registerVoter(jsonRequest);
        VotingOperationsErrorCode actual = new Gson().fromJson(jsonResponse, VotingOperationsErrorCode.class);
        VotingOperationsErrorCode expected = new VotingOperationsErrorCode();
        expected.setErrorString(expected.getStartVoting());
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testAddOffer(){

    }

    @Test
    public void testLogin(){

    }

    @Test
    public void testLogout(){

    }

    @Test
    public void testPutOnMayor(){

    }

    @Test
    public void testRateOffer(){

    }
}
