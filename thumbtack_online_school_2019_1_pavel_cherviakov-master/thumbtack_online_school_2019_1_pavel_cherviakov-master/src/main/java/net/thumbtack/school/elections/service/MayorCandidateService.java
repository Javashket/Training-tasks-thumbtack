package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.PutOnMayorDtoRequest;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllCandidatesDtoResponse;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.AddMayorCandidateErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.TokenMayorErrorCode;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.mybatis.dao.MayorCandidateDao;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.MayorCandidateDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.RatingDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.server.Server;

public class MayorCandidateService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;
    private MayorCandidateDao mayorCandidateDao;
    private String votingOperationsErrorCodeResponse;

    public MayorCandidateService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.ratingDao = new RatingDaoImpl();
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
            TokenMayorErrorCode tokenMayorErrorCode = new TokenMayorErrorCode();
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
                // добавить включение программ
            }
            mayorCandidateDao.insert(mayorCandidate);
            return "";
        }
        AddMayorCandidateErrorCode addMayorCandidateErrorCode = new AddMayorCandidateErrorCode();
        addMayorCandidateErrorCode.setErrorString(addMayorCandidateErrorCode.getNotMayorCandidate());
        return new Gson().toJson(addMayorCandidateErrorCode);
    }


    public String consentOnPositionOnMayor(String jsonRequest) {
        // добавить проверку  токена,  проверку токена среди кандидатов в мэры
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
            TokenMayorErrorCode tokenMayorErrorCode = new TokenMayorErrorCode();
            tokenMayorErrorCode.setErrorString(tokenMayorErrorCode.getNotFoundToken());
            return new Gson().toJson( expected.setErrorString(expected.getNotFoundToken()));
        }
        mayorCandidateDao.consentOnPosition(tokenVoterDto.getToken());
        // включение предложений
        return "";
    }

    public String getAllCandidates(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        LogoutVoterErrorCode logoutVoterErrorCode = new LogoutVoterErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(logoutVoterErrorCode.setErrorString(logoutVoterErrorCode.getNotFoundToken()));
        }
        AllCandidatesDtoResponse allCandidatesDtoResponse = new AllCandidatesDtoResponse();
        allCandidatesDtoResponse.setMayorCandidates(mayorCandidateDao.getAll());
        return new Gson().toJson(allCandidatesDtoResponse);
    }

    public String includeOfferInYourProgram(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        // сначала должен дать свое согласие на мэры
        return null;
    }

    public String withdrawСandidacyWithMayor(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        return null;
    }

    public String deleteOfferFromYourProgram(String jsonRequest) {
        return null;
    }

    // удаление всех кандидатов
}
