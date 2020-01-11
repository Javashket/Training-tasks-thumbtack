package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.DeleteRatingDtoRequest;
import net.thumbtack.school.elections.dto.request.GetOffersSeveralCandidatesDtoRequest;
import net.thumbtack.school.elections.dto.request.RateOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllOffersDtoResponse;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.errors.offer.AddOfferErrorCode;
import net.thumbtack.school.elections.errors.offer.RateOfferErrorCode;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.RatingDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;
    private String votingOperationsErrorCodeResponse;

    public OfferService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.ratingDao = new RatingDaoImpl();
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
        ratingDao.insert(offer.getRatings().get(0), offer);
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
        TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
        if(voterDao.getByToken(rateOfferDtoRequest.getTokenEvaluating()) == null) {
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        Rating rating = new Rating(rateOfferDtoRequest.getTokenEvaluating(), rateOfferDtoRequest.getRating());
        Offer offer = offerDao.getByContent(rateOfferDtoRequest.getContent());
        if(rating.getToken_evaluating_voter().equals(offer.getAuthor_token())) {
            RateOfferErrorCode rateOfferErrorCode = new RateOfferErrorCode();
            rateOfferErrorCode.setErrorString(rateOfferErrorCode.getRatingAuthorConst());
            return new Gson().toJson(rateOfferErrorCode);
        }
        offer.addRate(rating);
        ratingDao.insert(rating, offer);
        offerDao.update(offer);
        return "";
    }

    public String deleteRatingFromOffer(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        DeleteRatingDtoRequest deleteRatingDtoRequest = new Gson().fromJson(jsonRequest, DeleteRatingDtoRequest.class);
        TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
        if(voterDao.getByToken(deleteRatingDtoRequest.getToken()) == null) {
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        for(Offer offer : offerDao.getAll()) {
            if(offer.getContent().equals(deleteRatingDtoRequest.getContextOffer()) &&
             !offer.getAuthor_token().equals(deleteRatingDtoRequest.getToken())) {
                for(Rating rating : offer.getRatings()) {
                    if(rating.getToken_evaluating_voter().equals(deleteRatingDtoRequest.getToken())) {
                        ratingDao.deleteById(rating.getId());
                        break;
                    }
                }
                break;
            }
        }
        return "";
    }

    public String  getAllOffers(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        List<Offer> offers = offerDao.getAll();
        Collections.sort(offers);
        return new Gson().toJson(new AllOffersDtoResponse(offers));
    }

    public String getOffersSeveralCandidates(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        GetOffersSeveralCandidatesDtoRequest getOffersSeveralCandidatesDtoRequest = new Gson().fromJson(jsonRequest, GetOffersSeveralCandidatesDtoRequest.class);
        List<String> tokens = getOffersSeveralCandidatesDtoRequest.getCandidates_token();
        for (String token : tokens) {
            if(voterDao.getByToken(token) == null ||
            voterDao.getByToken(getOffersSeveralCandidatesDtoRequest.getToken()) == null) {
                TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
                tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken());
                return new Gson().toJson(tokenVoterErrorCode);
            }
        }
        List<Offer> offers = new ArrayList<>();
        for(Offer offer : offerDao.getAll()) {
            for(String token : tokens) {
                if (offer.getAuthor_token().equals(token)) {
                    offers.add(offer);
                    break;
                }
            }
        }
        Collections.sort(offers);
        return new Gson().toJson(new AllOffersDtoResponse(offers));
    }
}
