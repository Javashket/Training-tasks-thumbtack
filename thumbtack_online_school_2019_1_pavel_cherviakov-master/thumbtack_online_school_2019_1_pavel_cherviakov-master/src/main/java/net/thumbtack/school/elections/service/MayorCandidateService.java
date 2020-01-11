package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.IncludeOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.AddMayorCandidateErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.AlreadyConsentOnMayor;
import net.thumbtack.school.elections.errors.mayorcandidate.NotConsentOnMayorErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.TokenMayorErrorCode;
import net.thumbtack.school.elections.errors.offer.IncludeOfferErrorCode;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.MayorCandidateDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.Server;

public class MayorCandidateService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private MayorCandidateDao mayorCandidateDao;
    private String votingOperationsErrorCodeResponse;

    public MayorCandidateService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.mayorCandidateDao = new MayorCandidateDaoImpl();
        VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
        votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
        this.votingOperationsErrorCodeResponse = new Gson().toJson(votingOperationsErrorCode);
    }

    public String putOnMayor(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        PutOnMayorDtoRequest putOnMayorDtoRequest = new Gson().fromJson(jsonRequest, PutOnMayorDtoRequest.class);
        Voter voter = voterDao.getByToken(putOnMayorDtoRequest.getPushing_voter_token());
        if(voter == null) {
            TokenVoterErrorCode tokenMayorErrorCode = new TokenVoterErrorCode();
            tokenMayorErrorCode.setErrorString(tokenMayorErrorCode.getNotFoundToken());
            return new Gson().toJson(tokenMayorErrorCode);
        }
        voter = null;
        for (Voter voter1 : voterDao.getAll()) {
            if(voter1.hashCode() == putOnMayorDtoRequest.getRegisterVoterDtoRequest().hashCode() &&
                    voter1.getToken() != null ) {
                voter = voter1;
                break;
            }
        }
        if (voter != null) {
            MayorCandidate mayorCandidate = new MayorCandidate(voter.getToken());
            if(voter.getToken().equals(putOnMayorDtoRequest.getPushing_voter_token())) {
                mayorCandidate.setConsentOnNomination(true);
            }
            mayorCandidateDao.insert(mayorCandidate);
            for(Offer offer : offerDao.getAll()) {
                if(offer.getAuthor_token().equals(mayorCandidate.getToken_voter())) {
                    mayorCandidateDao.includeOffer(mayorCandidate.getId(), offer);
                }
            }
            return "";
        }
        AddMayorCandidateErrorCode addMayorCandidateErrorCode = new AddMayorCandidateErrorCode();
        addMayorCandidateErrorCode.setErrorString(addMayorCandidateErrorCode.getNotMayorCandidate());
        return new Gson().toJson(addMayorCandidateErrorCode);
    }


    public String consentOnPositionOnMayor(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        TokenMayorErrorCode expected = new TokenMayorErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson( expected.setErrorString(expected.getNotFoundToken()));
        }
        if(mayorCandidateDao.getByTokenVoter(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(expected.setErrorString(expected.getNotFoundToken()));
        }
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(tokenVoterDto.getToken()) && mayorCandidate.isConsentOnNomination()) {
                AlreadyConsentOnMayor alreadyConsentOnMayor = new AlreadyConsentOnMayor();
                alreadyConsentOnMayor.setErrorString(alreadyConsentOnMayor.getAlreadyConfirm());
                return new Gson().toJson(alreadyConsentOnMayor);
            }
        }
        mayorCandidateDao.consentOnPosition(tokenVoterDto.getToken());
        for(Offer offer : offerDao.getAll()) {
            if(offer.getAuthor_token().equals(tokenVoterDto.getToken())) {
                mayorCandidateDao.includeOffer( mayorCandidateDao.getByTokenVoter(tokenVoterDto.getToken()).getId(), offer);
            }
        }
        return "";
    }

    public String getAllCandidates(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        AllCandidatesDtoResponse allCandidatesDtoResponse = new AllCandidatesDtoResponse();
        allCandidatesDtoResponse.setMayorCandidates(mayorCandidateDao.getAll());
        return new Gson().toJson(allCandidatesDtoResponse);
    }

    public String includeOfferInYourProgram(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        IncludeOfferDtoRequest includeOfferDtoRequest = new Gson().fromJson(jsonRequest, IncludeOfferDtoRequest.class);
        MayorCandidate mayorCandidate1 = null;
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(includeOfferDtoRequest.getToken()) &&
            mayorCandidate.isConsentOnNomination()) {
                mayorCandidate1 = mayorCandidate;
            }
        }
        if(mayorCandidate1 == null) {
            NotConsentOnMayorErrorCode notConsentOnMayorErrorCode = new NotConsentOnMayorErrorCode();
            notConsentOnMayorErrorCode.setErrorString(notConsentOnMayorErrorCode.getNotConsent());
            return new Gson().toJson(notConsentOnMayorErrorCode);
        }
        for(Offer offer : offerDao.getAll()) {
            if(offer.getContent().equals(includeOfferDtoRequest.getContent())) {
                mayorCandidateDao.includeOffer(mayorCandidate1.getId(), offer);
                return "";
            }
        }
        IncludeOfferErrorCode includeOfferErrorCode = new IncludeOfferErrorCode();
        includeOfferErrorCode.setErrorString(includeOfferErrorCode.getErrorString());
        return new Gson().toJson(includeOfferDtoRequest);
    }

    public String withdrawCandidateWithMayor(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDtoRequest = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        MayorCandidate mayorCandidate1 = null;
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(tokenVoterDtoRequest.getToken()) &&
                    mayorCandidate.isConsentOnNomination()) {
                mayorCandidate1 = mayorCandidate;
            }
        }
        if(mayorCandidate1 == null) {
            NotConsentOnMayorErrorCode notConsentOnMayorErrorCode = new NotConsentOnMayorErrorCode();
            notConsentOnMayorErrorCode.setErrorString(notConsentOnMayorErrorCode.getNotConsent());
            return new Gson().toJson(notConsentOnMayorErrorCode);
        }
        mayorCandidateDao.delete(tokenVoterDtoRequest.getToken());
        return "";
    }

    public String deleteOfferFromYourProgram(String jsonRequest) {
        if (Server.isStartingVoting()) {
            return this.votingOperationsErrorCodeResponse;
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        IncludeOfferDtoRequest includeOfferDtoRequest = new Gson().fromJson(jsonRequest, IncludeOfferDtoRequest.class);
        MayorCandidate mayorCandidate1 = null;
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(includeOfferDtoRequest.getToken()) &&
                    mayorCandidate.isConsentOnNomination()) {
                mayorCandidate1 = mayorCandidate;
            }
        }
        if(mayorCandidate1 == null) {
            NotConsentOnMayorErrorCode notConsentOnMayorErrorCode = new NotConsentOnMayorErrorCode();
            notConsentOnMayorErrorCode.setErrorString(notConsentOnMayorErrorCode.getNotConsent());
            return new Gson().toJson(notConsentOnMayorErrorCode);
        }
        for(Offer offer : offerDao.getAll()) {
            if(offer.getContent().equals(includeOfferDtoRequest.getContent()) &&
                    !offer.getAuthor_token().equals(includeOfferDtoRequest.getToken())) {
                mayorCandidateDao.deleteOffer(mayorCandidate1.getId(), offer);
                return "";
            }
        }
        IncludeOfferErrorCode includeOfferErrorCode = new IncludeOfferErrorCode();
        includeOfferErrorCode.setErrorString(includeOfferErrorCode.getErrorString());
        return new Gson().toJson(includeOfferDtoRequest);
    }
}
