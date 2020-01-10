package net.thumbtack.school.elections.json;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.errors.json.SyntaxJsonErrorCode;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonErrorSyntax extends Init {

    @Test
    public void testRegisterOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.registerVoter(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testLoginOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.login(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.login(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.login(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testLogoutOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.logout(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.logout(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.logout(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testAddOfferOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.addOffer(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.addOffer(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.addOffer(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testPutOnMayorOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.putOnMayor(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.putOnMayor(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.putOnMayor(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testRateOfferOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.rateOffer(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.rateOffer(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.rateOffer(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testDeleteRatingFromOfferOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.deleteRatingFromOffer(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.deleteRatingFromOffer(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.deleteRatingFromOffer(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testWithdrawCandidateWithMayorOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.withdrawCandidateWithMayor(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.withdrawCandidateWithMayor(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.withdrawCandidateWithMayor(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testConsentOnPositionOnMayorOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.consentOnPositionOnMayor(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.consentOnPositionOnMayor(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.consentOnPositionOnMayor(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testVoteForCandidateOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.voteForCandidate(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.voteForCandidate(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.voteForCandidate(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testVoteAgainstAllCandidatesOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.voteAgainstAllCandidates(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.voteAgainstAllCandidates(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.voteAgainstAllCandidates(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testIncludeOfferInYourProgramOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.includeOfferInYourProgram(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.includeOfferInYourProgram(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.includeOfferInYourProgram(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testDeleteOfferFromYourProgramOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.deleteOfferFromYourProgram(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.deleteOfferFromYourProgram(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.deleteOfferFromYourProgram(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testGetAllVotersOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.getAllVoters(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.getAllVoters(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.getAllVoters(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testGetAllCandidatesOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.getAllCandidates(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.getAllCandidates(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.getAllCandidates(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testGetAllOffersOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.getAllOffers(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.getAllOffers(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.getAllOffers(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testGetOffersSeveralCandidatesOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.getOffersSeveralCandidates(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.getOffersSeveralCandidates(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.getOffersSeveralCandidates(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testSummarizeOnErrorSyntaxJson() throws IOException {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.summarize(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.summarize(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.summarize(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }
}
