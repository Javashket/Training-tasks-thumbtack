package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.ElectionDtoRequest;
import net.thumbtack.school.elections.dto.request.IncludeOfferDtoRequest;
import net.thumbtack.school.elections.dto.request.VoteDtoRequest;
import net.thumbtack.school.elections.dto.response.ElectionDtoResponse;
import net.thumbtack.school.elections.errors.election.FileErrorCode;
import net.thumbtack.school.elections.errors.election.MayorErrorCode;
import net.thumbtack.school.elections.errors.election.VotingOperationsErrorCode;
import net.thumbtack.school.elections.errors.mayorcandidate.TokenMayorErrorCode;
import net.thumbtack.school.elections.errors.offer.IncludeOfferErrorCode;
import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;
import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Vote;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.mybatis.dao.*;
import net.thumbtack.school.elections.mybatis.daoimpl.*;
import net.thumbtack.school.elections.server.Server;

import java.io.File;
import java.io.IOException;

public class ElectionService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private MayorCandidateDao mayorCandidateDao;
    private VoteDao voteDao;

    public ElectionService() {
        this.offerDao = new OfferDaoImpl();
        this.voterDao = new VoterDaoImpl();
        this.mayorCandidateDao = new MayorCandidateDaoImpl();
        this.voteDao = new VoteDaoImpl();
    }

    public String voteAgainstAllCandidates(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        VoteDtoRequest voteDtoRequest = new Gson().fromJson(jsonRequest, VoteDtoRequest.class);
        Voter voter2 = voterDao.getByToken(voteDtoRequest.getToken());
        if( voter2 == null) {
            TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        for(MayorCandidate mayorCandidate: mayorCandidateDao.getAll()) {
            Vote vote = new Vote(voter2,false);
            voteDao.insert(vote, mayorCandidate);
        }
        return "";
    }

    public String voteForCandidate(String jsonRequest) {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        VoteDtoRequest voteDtoRequest = new Gson().fromJson(jsonRequest, VoteDtoRequest.class);
        Voter voter2 = voterDao.getByToken(voteDtoRequest.getToken());
        if( voter2 == null) {
            TokenVoterErrorCode tokenVoterErrorCode = new TokenVoterErrorCode();
            return new Gson().toJson(tokenVoterErrorCode.setErrorString(tokenVoterErrorCode.getNotFoundToken()));
        }
        String tokenMayor = null;
        for (Voter voter : voterDao.getAll()) {
            if(voter.hashCode() == voteDtoRequest.getRegisterVoterDtoRequest().hashCode()) {
                tokenMayor = voter.getToken();
                break;
            }
        }
        MayorCandidate mayorCandidate1 = null;
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(tokenMayor)) {
                mayorCandidate1 = mayorCandidate;
            }
        }
        if(mayorCandidate1 == null) {
            TokenMayorErrorCode tokenMayorErrorCode = new TokenMayorErrorCode();
            return new Gson().toJson(tokenMayorErrorCode.setErrorString(tokenMayorErrorCode.getNotFoundToken()));
        }
        if(voteDtoRequest.getToken().equals(mayorCandidate1.getToken_voter())) {
            MayorErrorCode mayorErrorCode = new MayorErrorCode();
            mayorErrorCode.setErrorString(mayorErrorCode.getErrorVote());
            return new Gson().toJson(mayorErrorCode);
        }
        Vote vote = new Vote(voter2,true);
        voteDao.insert(vote, mayorCandidate1);
        return "";
    }

    public String includeOfferInYourProgram(String jsonRequest) {
        if(Server.isStartingVoting()) {
            VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
            votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
            return new Gson().toJson(votingOperationsErrorCode);
        }
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        IncludeOfferDtoRequest includeOfferDtoRequest = new Gson().fromJson(jsonRequest, IncludeOfferDtoRequest.class);
        MayorCandidate mayorCandidate1 = null;
        for (MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.getToken_voter().equals(includeOfferDtoRequest.getToken())) {
                mayorCandidate1 = mayorCandidate;
                break;
            }
        }
        if(mayorCandidate1 == null) {
            TokenMayorErrorCode tokenMayorErrorCode = new TokenMayorErrorCode();
            tokenMayorErrorCode.setErrorString(tokenMayorErrorCode.getNotFoundToken());
            return new Gson().toJson(tokenMayorErrorCode);
        }
        Offer offer1 = null;
        for (Offer offer : offerDao.getAll()) {
            if(offer.getContent().equals(includeOfferDtoRequest.getContent())) {
                offer1 = offer;
                break;
            }
        }
        if(offer1 == null) {
            IncludeOfferErrorCode includeOfferErrorCode = new IncludeOfferErrorCode();
            includeOfferErrorCode.setErrorString(includeOfferErrorCode.getNotFoundOffer());
            return new Gson().toJson(includeOfferErrorCode);
        }
        mayorCandidateDao.includeOffer(mayorCandidate1.getId(), offer1);
        return "";
    }

    public String summarize(String jsonRequest) throws IOException {
        String response = JsonService.checkIsValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        ElectionDtoRequest electionDtoRequest = new Gson().fromJson(jsonRequest, ElectionDtoRequest.class);
        File file = new File(electionDtoRequest.getPathFileWrite());
        if((!file.isFile() && file.isDirectory()) ||
                !electionDtoRequest.getPathFileWrite().equals("")) {
            FileErrorCode fileErrorCode = new FileErrorCode();
            return new Gson().toJson(fileErrorCode.setErrorString(fileErrorCode.getFileNotFound()));
        }
        MayorCandidate mayorCandidate1 = null;
        int countVoteForPrev = 0;
        int countVoteForNext = 0, countVoteAgainstNext = 0;
        for(MayorCandidate mayorCandidate : mayorCandidateDao.getAll()) {
            if(mayorCandidate.isConsentOnNomination() && !mayorCandidate.getProgram().isEmpty() &&
                 !mayorCandidate.getVotedVoters().isEmpty()) {
                for(Vote vote : mayorCandidate.getVotedVoters()) {
                    if(vote.isVote()) {
                        countVoteForNext++;
                    } else {
                        countVoteAgainstNext--;
                    }
                }
                if(countVoteForNext > countVoteAgainstNext && countVoteForNext > countVoteForPrev) {
                    mayorCandidate1 = mayorCandidate;
                }
                countVoteForPrev = countVoteForNext;
                countVoteForNext = 0;
                countVoteAgainstNext = 0;
            }
        }
        ElectionDtoResponse electionDtoResponse = new ElectionDtoResponse();
        if(mayorCandidate1 == null) {
            electionDtoResponse.setResult("Выборы не состоялись.");
        } else {
            electionDtoResponse.setResult("Мэром выбран " +
                    voterDao.getByToken(mayorCandidate1.getToken_voter()).toString());
        }
        Server.stopServer(file.getPath());
        return new Gson().toJson(electionDtoResponse);
    }
}
