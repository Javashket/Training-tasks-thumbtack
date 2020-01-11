package net.thumbtack.school.elections;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.ElectionDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestVotingMayor extends Init {

    @Test
    public void testVotingMayorWhenVotedForMore() throws IOException {
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

        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3","Иванов3",
                "Иванович3","улица3","дом3", "563","logpass3","passlogpass3");
        String jsonRequestRegister3 = new Gson().toJson(requestRegister3);
        String jsonResponseRegister3 = Server.registerVoter(jsonRequestRegister3);
        RegisterVoterDtoResponse resultRegister3 = new Gson().fromJson(jsonResponseRegister3, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister4 = new RegisterVoterDtoRequest("Иван4","Иванов4",
                "Иванович4","улица4","дом4", "564","logpass4","passlogpass4");
        String jsonRequestRegister4 = new Gson().toJson(requestRegister4);
        String jsonResponseRegister4 = Server.registerVoter(jsonRequestRegister4);
        RegisterVoterDtoResponse resultRegister4 = new Gson().fromJson(jsonResponseRegister4, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest putOnMayorDtoRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister3);
        PutOnMayorDtoRequest putOnMayorDtoRequest2 = new PutOnMayorDtoRequest(resultRegister2.getToken(), requestRegister4);
        String jsonPutOnMayorDtoRequest1 = new Gson().toJson(putOnMayorDtoRequest1);
        String jsonPutOnMayorDtoRequest2 = new Gson().toJson(putOnMayorDtoRequest2);
        Server.putOnMayor(jsonPutOnMayorDtoRequest1);
        Server.putOnMayor(jsonPutOnMayorDtoRequest2);

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        Offer requestAddOffer1 = new Offer(resultRegister3.getToken(), content1);
        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        Server.addOffer(jsonRequestAddOffer1);
        String content2 = "Вымостить тротуарной плиткой центральную площадь2.";
        Offer requestAddOffer2 = new Offer(resultRegister4.getToken(), content2);
        String jsonRequestAddOffer2 = new Gson().toJson(requestAddOffer2);
        Server.addOffer(jsonRequestAddOffer2);

        TokenVoterDtoRequest tokenVoterDtoRequest3 = new TokenVoterDtoRequest(resultRegister3.getToken());
        TokenVoterDtoRequest tokenVoterDtoRequest4 = new TokenVoterDtoRequest(resultRegister4.getToken());
        String jsonTokenVoterRequest3 = new Gson().toJson(tokenVoterDtoRequest3);
        String jsonTokenVoterRequest4 = new Gson().toJson(tokenVoterDtoRequest4);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest3);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest4);
        Server.startVoting();

        VoteDtoRequest voteDtoRequest1 = new VoteDtoRequest(resultRegister1.getToken(), requestRegister3);
        VoteDtoRequest voteDtoRequest2 = new VoteDtoRequest(resultRegister2.getToken(), requestRegister3);
        String jsonVoteRequest1 = new Gson().toJson(voteDtoRequest1);
        String jsonVoteRequest2 = new Gson().toJson(voteDtoRequest2);
        Server.voteForCandidate(jsonVoteRequest1);
        Server.voteForCandidate(jsonVoteRequest2);

        ElectionDtoRequest electionDtoRequest = new ElectionDtoRequest("");
        String jsonElectionDtoRequest = new Gson().toJson(electionDtoRequest);
        ElectionDtoResponse expected = new ElectionDtoResponse("Мэром выбран " + requestRegister3.toString());
        String jsonResponseSummarize = Server.summarize(jsonElectionDtoRequest);
        ElectionDtoResponse actual = new Gson().fromJson(jsonResponseSummarize, ElectionDtoResponse.class);
        assertEquals(expected.getResult(), actual.getResult());
    }

    @Test
    public void testVotingMayorWhenVotedAgainstMore() throws IOException {
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

        RegisterVoterDtoRequest requestRegister3 = new RegisterVoterDtoRequest("Иван3","Иванов3",
                "Иванович3","улица3","дом3", "563","logpass3","passlogpass3");
        String jsonRequestRegister3 = new Gson().toJson(requestRegister3);
        String jsonResponseRegister3 = Server.registerVoter(jsonRequestRegister3);
        RegisterVoterDtoResponse resultRegister3 = new Gson().fromJson(jsonResponseRegister3, RegisterVoterDtoResponse.class);

        RegisterVoterDtoRequest requestRegister4 = new RegisterVoterDtoRequest("Иван4","Иванов4",
                "Иванович4","улица4","дом4", "564","logpass4","passlogpass4");
        String jsonRequestRegister4 = new Gson().toJson(requestRegister4);
        String jsonResponseRegister4 = Server.registerVoter(jsonRequestRegister4);
        RegisterVoterDtoResponse resultRegister4 = new Gson().fromJson(jsonResponseRegister4, RegisterVoterDtoResponse.class);

        PutOnMayorDtoRequest putOnMayorDtoRequest1 = new PutOnMayorDtoRequest(resultRegister1.getToken(), requestRegister3);
        PutOnMayorDtoRequest putOnMayorDtoRequest2 = new PutOnMayorDtoRequest(resultRegister2.getToken(), requestRegister4);
        String jsonPutOnMayorDtoRequest1 = new Gson().toJson(putOnMayorDtoRequest1);
        String jsonPutOnMayorDtoRequest2 = new Gson().toJson(putOnMayorDtoRequest2);
        Server.putOnMayor(jsonPutOnMayorDtoRequest1);
        Server.putOnMayor(jsonPutOnMayorDtoRequest2);

        String content1 = "Вымостить тротуарной плиткой центральную площадь1.";
        Offer requestAddOffer1 = new Offer(resultRegister3.getToken(), content1);
        String jsonRequestAddOffer1 = new Gson().toJson(requestAddOffer1);
        Server.addOffer(jsonRequestAddOffer1);
        String content2 = "Вымостить тротуарной плиткой центральную площадь2.";
        Offer requestAddOffer2 = new Offer(resultRegister4.getToken(), content2);
        String jsonRequestAddOffer2 = new Gson().toJson(requestAddOffer2);
        Server.addOffer(jsonRequestAddOffer2);

        TokenVoterDtoRequest tokenVoterDtoRequest3 = new TokenVoterDtoRequest(resultRegister3.getToken());
        TokenVoterDtoRequest tokenVoterDtoRequest4 = new TokenVoterDtoRequest(resultRegister4.getToken());
        String jsonTokenVoterRequest3 = new Gson().toJson(tokenVoterDtoRequest3);
        String jsonTokenVoterRequest4 = new Gson().toJson(tokenVoterDtoRequest4);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest3);
        Server.consentOnPositionOnMayor(jsonTokenVoterRequest4);
        Server.startVoting();

        TokenVoterDtoRequest tokenVoterDtoRequest1 = new TokenVoterDtoRequest(resultRegister1.getToken());
        TokenVoterDtoRequest tokenVoterDtoRequest2 = new TokenVoterDtoRequest(resultRegister2.getToken());
        String jsonVoteRequest1 = new Gson().toJson(tokenVoterDtoRequest1);
        String jsonVoteRequest2 = new Gson().toJson(tokenVoterDtoRequest2);
        Server.voteAgainstAllCandidates(jsonVoteRequest1);
        Server.voteAgainstAllCandidates(jsonVoteRequest2);

        ElectionDtoRequest electionDtoRequest = new ElectionDtoRequest("");
        String jsonElectionDtoRequest = new Gson().toJson(electionDtoRequest);
        ElectionDtoResponse expected = new ElectionDtoResponse("Выборы не состоялись.");
        String jsonResponseSummarize = Server.summarize(jsonElectionDtoRequest);
        ElectionDtoResponse actual = new Gson().fromJson(jsonResponseSummarize, ElectionDtoResponse.class);
        assertEquals(expected.getResult(), actual.getResult());
    }

}
