package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.errors.offer.AddOfferErrorCode;
import net.thumbtack.school.elections.errors.offer.RateOfferErrorCode;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
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
import net.thumbtack.school.elections.server.Server;

import java.util.Collections;
import java.util.List;

public class OfferService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;
    private MayorCandidateDao mayorCandidateDao;
    private String votingOperationsErrorCodeResponse;

    public OfferService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.ratingDao = new RatingDaoImpl();
        this.mayorCandidateDao = new MayorCandidateDaoImpl();
        VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
        votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
        this.votingOperationsErrorCodeResponse = new Gson().toJson(votingOperationsErrorCode);
    }

    public String addOffer(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        AddOfferErrorCode addOfferErrorCode = new AddOfferErrorCode();
        Offer offer  = new Gson().fromJson(jsonRequest, Offer.class);
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

    public String rateOffer(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        RateOfferDtoRequest rateOfferDtoRequest  = new Gson().fromJson(jsonRequest, RateOfferDtoRequest.class);
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

    // из своего предлож удалить не может
    public String deleteRatingFromOffer(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }

        return null;
    }

    public String  getAllOffers(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        LogoutVoterErrorCode logoutVoterErrorCode = new LogoutVoterErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(logoutVoterErrorCode.setErrorString(logoutVoterErrorCode.getNotFoundToken()));
        }
        List<Offer> offers = offerDao.getAll();
        Collections.sort(offers);
        return new Gson().toJson(new AllOffersDtoResponse(offers));
    }

    //удаление всех предложений

}
