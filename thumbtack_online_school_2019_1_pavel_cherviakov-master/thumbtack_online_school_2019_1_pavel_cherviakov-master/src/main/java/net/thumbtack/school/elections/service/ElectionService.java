package net.thumbtack.school.elections.service;

import com.google.gson.*;
import net.thumbtack.school.elections.errors.json.SyntaxJsonErrorCode;
import net.thumbtack.school.elections.errors.offer.AddOfferErrorCode;
import net.thumbtack.school.elections.errors.offer.RateOfferErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.MayorCandidateDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.RatingDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.server.Server;

import java.util.Collections;
import java.util.List;

public class ElectionService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;
    private MayorCandidateDao mayorCandidateDao;

    public ElectionService() {
        this.offerDao = new OfferDaoImpl();
        this.voterDao = new VoterDaoImpl();
        this.ratingDao = new RatingDaoImpl();
    }

    public String putOnMayor(String requestJson) {
        if(Server.isStartingServer()) {
            return "{'error':'Сервер не запущен.'}";
        }
        String response = isValidJson(requestJson);
        if(!response.equals("")) {
            return response;
        }
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(requestJson);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String tokenVoter = rootObject.get("tokenVoter").getAsString();
        String tokenNominationMayor = rootObject.get("tokenNominationMayor").getAsString();

        return "";
    }

    public String addOffer(String requestJson) {
//        if(Server.isStartingServer()) {
//            return "{'error':'Сервер не запущен.'}";
//        }
        String response = isValidJson(requestJson);
        if(!response.equals("")) {
            return response;
        }
            AddOfferErrorCode addOfferErrorCode = new AddOfferErrorCode();
          Offer offer  = new Gson().fromJson(requestJson, Offer.class);
          if(offer.getContent().equals("")) {
              addOfferErrorCode.setErrorString(addOfferErrorCode.getEmptyOffer());
              return new Gson().toJson(addOfferErrorCode);
          }
          for (Offer offer1 : offerDao.getAll()) {
              if(offer1.getContent().equals(offer.getContent())) {
                  addOfferErrorCode.setErrorString(addOfferErrorCode.getSameOffer());
                  return new Gson().toJson(addOfferErrorCode);
              }
          }
          offerDao.insert(offer);
//        Offer offer = new Offer(voterDao.getByToken(tokenVoter), textOffer);
//        offer.addRaiting(offer.getAuthor(),5);
//        this.offerDao.insert(offer);
        return "";
    }

    public String rateOffer(String requestJson) {
        RateOfferDtoRequest rateOfferDtoRequest  = new Gson().fromJson(requestJson, RateOfferDtoRequest.class);
        Rating rating = new Rating(rateOfferDtoRequest.getTokenEvaluating(), rateOfferDtoRequest.getRating());
        Offer offer = offerDao.getByContent(rateOfferDtoRequest.getContent());
        if(rating.getToken_evaluating_voter().equals(offer.getAuthor_token())) {
            RateOfferErrorCode rateOfferErrorCode = new RateOfferErrorCode();
            rateOfferErrorCode.setErrorString(rateOfferErrorCode.getRatingAuthorConst());
            return new Gson().toJson(rateOfferErrorCode);
        }
        ratingDao.insert(rating, offer);
        return "";
    }

    public static String agreeToPositionOnMayor(String requestJson) {



        return "";
    }

    public String  getAllOffers() {
        List<Offer> offers = offerDao.getAll();
        Collections.sort(offers);
        return new Gson().toJson(new AllOffersDtoResponse(offers));
    }

    private String isValidJson(String json) {
        try {
            JsonParser parser = new JsonParser();
            parser.parse(json);
            if (!json.contains("{")) {
                throw new JsonSyntaxException(json);
            }
        } catch(JsonSyntaxException ex) {
            System.out.println("df");
            SyntaxJsonErrorCode syntaxJsonErrorCode = new SyntaxJsonErrorCode();
            return new Gson().toJson(syntaxJsonErrorCode.setErrorString(syntaxJsonErrorCode.getErrorSyntax()));
        }
        return "";
    }

    public String getCandidates() {

        return new Gson().toJson(mayorCandidateDao.getAll());
    }

    public String vote(String requestJson) {
        String response = isValidJson(requestJson);
        if(!response.equals("")) {
            return response;
        }
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(requestJson);
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

    public String addCandidate(String requestJson) {

        return "electionService.addCandidate(requestJsonString)";
    }

    public static String isStartingServer() {
        if(Server.isStartingServer()) {
            return "{'error':'Сервер не запущен.'}";
        }
        return "";
    }
}
