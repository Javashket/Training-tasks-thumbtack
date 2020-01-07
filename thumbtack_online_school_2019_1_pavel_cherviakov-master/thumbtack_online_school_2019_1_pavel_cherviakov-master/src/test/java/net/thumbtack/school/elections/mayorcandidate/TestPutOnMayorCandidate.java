package net.thumbtack.school.elections.mayorcandidate;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPutOnMayorCandidate {

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
    public void testPutOnMayorCandidateYourself(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        PutOnMayorDtoRequest request2 = new PutOnMayorDtoRequest(result1.getToken(), request1.hashCode());
        String jsonRequest2 = new Gson().toJson(request2);
        Server.putOnMayor(jsonRequest2);
        String jsonResponse2 = Server.getAllCandidates();
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponse2, AllCandidatesDtoResponse.class);
        boolean actual = false;
        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if (mayorCandidate.getVoter().getToken().equals(result1.getToken())) {
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
        String jsonResponse2 = Server.registerVoter(jsonRequest1);
        assertNotNull(result1.getToken());
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);
        assertNotNull(result2.getToken());
        assertNotEquals(result1.getToken(), result2.getToken());
    }

    @Test
    public void testPutOnMayorCandidateIsFalsePushingVoterToken(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        PutOnMayorDtoRequest request2 = new PutOnMayorDtoRequest(result1.getToken(), request1.hashCode());
        String jsonRequest2 = new Gson().toJson(request2);
        Server.putOnMayor(jsonRequest2);
        String jsonResponse2 = Server.getAllCandidates();
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponse2, AllCandidatesDtoResponse.class);
        boolean actual = false;
        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if(mayorCandidate.getVoter().getToken().equals(result1.getToken())) {
                actual = true;
            }
        }
        assertTrue(actual);
    }

    @Test
    public void testPutOnMayorCandidateIsFalseHashcodeVoterOnMayor(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        PutOnMayorDtoRequest request2 = new PutOnMayorDtoRequest(result1.getToken(), request1.hashCode());
        String jsonRequest2 = new Gson().toJson(request2);
        Server.putOnMayor(jsonRequest2);
        String jsonResponse2 = Server.getAllCandidates();
        AllCandidatesDtoResponse allCandidatesDtoResponse = new Gson().fromJson(jsonResponse2, AllCandidatesDtoResponse.class);
        boolean actual = false;
        for(MayorCandidate mayorCandidate : allCandidatesDtoResponse.getMayorCandidates()) {
            if(mayorCandidate.getVoter().getToken().equals(result1.getToken())) {
                actual = true;
            }
        }
        assertTrue(actual);
    }

}
