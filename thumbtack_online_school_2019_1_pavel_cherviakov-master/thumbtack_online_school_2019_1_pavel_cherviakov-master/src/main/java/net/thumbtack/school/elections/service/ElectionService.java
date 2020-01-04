package net.thumbtack.school.elections.service;

import com.google.gson.*;
import net.thumbtack.school.elections.mybatis.daoimpl.MayorCandidateDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.Server;

public class ElectionService {

    private VoterDaoImpl voterDao;
    private OfferDaoImpl offerDao;
    private MayorCandidateDaoImpl mayorCandidateDao;

    public ElectionService() {
        this.offerDao = new OfferDaoImpl();
        this.voterDao = new VoterDaoImpl();
    }

    public String putOnMayor(String requestJsonString) {
        if(Server.isStartingServer()) {
            return "{'error':'Сервер не запущен.'}";
        }
        String response = isValidJson(requestJsonString);
        if(!response.equals("")) {
            return response;
        }
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(requestJsonString);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String tokenVoter = rootObject.get("tokenVoter").getAsString();
        String tokenNominationMayor = rootObject.get("tokenNominationMayor").getAsString();

        return "";
    }

    public String addOffer(String requestJsonString) {
        if(Server.isStartingServer()) {
            return "{'error':'Сервер не запущен.'}";
        }
        String response = isValidJson(requestJsonString);
        if(!response.equals("")) {
            return response;
        }
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(requestJsonString);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String tokenVoter = rootObject.get("tokenVoter").getAsString();
        String textOffer = rootObject.get("Offer").getAsString();
//        Offer offer = new Offer(voterDao.getByToken(tokenVoter), textOffer);
//        offer.addRaiting(offer.getAuthor(),5);
//        this.offerDao.insert(offer);
        return response;
    }

    public String rateOffer(String requestJsonString) {
        return "";
    }

    public static String agreeToPositionOnMayor(String requestJsonString) {



        return "";
    }

    public String  getAllOffers() {
        return "voterService.getAllOffers()";
    }

    private String isValidJson(String json) {
        Gson gson = new Gson();
        try {
            gson.fromJson(json, Object.class);
            return "";
        } catch(JsonSyntaxException ex) {
            return "{'error':' ошибка синтаксиса JSON'}";
        }
    }

    public String getCandidates() {

        return new Gson().toJson(mayorCandidateDao.getAll());
    }

    public String vote(String requestJsonString) {
        String response = isValidJson(requestJsonString);
        if(!response.equals("")) {
            return response;
        }
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(requestJsonString);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String tokenVoter = rootObject.get("tokenVoter").getAsString();
        String voice = rootObject.get("voice").getAsString();
        return "";
    }

//    public String summarize() {
//        MayorCandidate mayorCandidate = new MayorCandidate();
//        for(MayorCandidate m : mayorCandidateDao.getAll()) {
//            if(mayorCandidate.getCountVoicesPros().equals(m.getCountVoicesPros())) {
//                mayorCandidate = m;
//            }
//        }
//        if (mayorCandidate.getCountVoicesPros() > mayorCandidate.getCountVoicesCons()) {
//            return new Gson().toJson(mayorCandidate);
//        } else {
//            return "{'error':'выборы признаны несостоявшимися'}";
//        }
//    }

    public String addCandidate(String requestJsonString) {

        return "electionService.addCandidate(requestJsonString)";
    }

    public static String isStartingServer() {
        if(Server.isStartingServer()) {
            return "{'error':'Сервер не запущен.'}";
        }
        return "";
    }
}
