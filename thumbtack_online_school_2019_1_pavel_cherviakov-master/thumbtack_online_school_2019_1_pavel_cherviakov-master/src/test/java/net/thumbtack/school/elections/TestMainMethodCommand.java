package net.thumbtack.school.elections;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestMainMethodCommand {

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

    @Test
    public void testMainStartAndStopServerWithNullFile() throws IOException {
        Server.main(new String[]{""});
        assertTrue(Server.isStartingServer());
        Server.main(new String[]{"-e"});
        assertFalse(Server.isStartingServer());
    }

    @Test
    public void testMainStartAndStopServerWithFile() throws IOException {
        String path = "AllDataTest.txt";
        Server.startServer(null);
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterDtoResponse resultRegister = new Gson().fromJson(jsonResponseRegister, RegisterVoterDtoResponse.class);
        TokenVoterDtoRequest tokenVoterDtoRequest = new TokenVoterDtoRequest(resultRegister.getToken());
        String jsonTokenVoterDtoRequest = new Gson().toJson(tokenVoterDtoRequest);

        Server.stopServer(path);
        Server.main(new String[]{"-l" + path });
        assertTrue(Server.isStartingServer());
        String response = Server.getAllVoters(jsonTokenVoterDtoRequest);
        AllVotersDtoResponse allVotersDtoResponse = new Gson().fromJson(response, AllVotersDtoResponse.class);
        for(Voter voter : allVotersDtoResponse.getVoters()) {
            assertEquals(requestRegister.hashCode(), voter.hashCode());
        }
        Server.main(new String[]{"-s" + path});
        Server.main(new String[]{"-e"});
        assertFalse(Server.isStartingServer());
        Server.startServer(path);
        assertTrue(Server.isStartingServer());
        response = Server.getAllVoters(jsonTokenVoterDtoRequest);
        allVotersDtoResponse = new Gson().fromJson(response, AllVotersDtoResponse.class);
        for(Voter voter : allVotersDtoResponse.getVoters()) {
            assertEquals(requestRegister.hashCode(), voter.hashCode());
        }
        Server.stopServer(null);
    }

    @Test
    public void testMainStartAndStopServerWithErrorSyntax() throws IOException {
        Server.main(new String[]{"ghgh"});
        assertTrue(Server.isStartingServer());
        Server.main(new String[]{"ghgh2"});
        assertTrue(Server.isStartingServer());
        Server.main(new String[]{"-e"});
        assertFalse(Server.isStartingServer());
    }

}
