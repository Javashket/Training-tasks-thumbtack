package net.thumbtack.school.elections.offer;

import com.google.gson.Gson;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllOffers {

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
    public void testGetOfferOrderByRating(){
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String content = "Вымостить тротуарной плиткой центральную площадь.";
        Offer request2 = new Offer(result1.getToken(), content);
        String jsonRequest2 = new Gson().toJson(request2);
        Server.addOffer(jsonRequest2);
        String jsonResponse2 = Server.getAllOffers();
        AllOffersDtoResponse allOffersDtoResponse = new Gson().fromJson(jsonResponse2, AllOffersDtoResponse.class);
        List<Offer> expected = new ArrayList<>();
        expected.add(request2);
        assertEquals(expected, allOffersDtoResponse.getOffers());
    }

}
