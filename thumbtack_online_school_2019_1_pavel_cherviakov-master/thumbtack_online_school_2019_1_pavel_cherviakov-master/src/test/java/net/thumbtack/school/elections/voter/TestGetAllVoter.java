package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.AllVotersDtoResponse;
import net.thumbtack.school.elections.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllVoter {

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
    public void testGetAllVoter(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        RegisterVoterDtoRequest request3 = new RegisterVoterDtoRequest("Иван3","Иванов3",
                "Иванович3","улица3","дом3", "563","logpass3","passlogpass3");
        Server.registerVoter(new Gson().toJson(request1));
        Server.registerVoter(new Gson().toJson(request2));
        Server.registerVoter(new Gson().toJson(request3));
        List<RegisterVoterDtoRequest> registerVoterDtoRequests = new ArrayList<>();
        registerVoterDtoRequests.add(request1);
        registerVoterDtoRequests.add(request2);
        registerVoterDtoRequests.add(request3);
        AllVotersDtoResponse actual = new Gson().fromJson(Server.getAllVoters(), AllVotersDtoResponse.class);
        assertEquals(registerVoterDtoRequests.hashCode(), actual.getVoters().hashCode());
    }

}
