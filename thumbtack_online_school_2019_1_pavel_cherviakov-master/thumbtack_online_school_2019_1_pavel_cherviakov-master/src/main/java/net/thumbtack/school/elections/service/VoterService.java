package net.thumbtack.school.elections.service;

import com.google.gson.*;
import net.thumbtack.school.elections.errors.json.SyntaxJsonErrorCode;
import net.thumbtack.school.elections.errors.voter.LoginVoterErrorCode;
import net.thumbtack.school.elections.errors.voter.LogoutVoterErrorCode;
import net.thumbtack.school.elections.errors.voter.RegisterVoterErrorCode;
import net.thumbtack.school.elections.errors.voting.VotingOperationsErrorCode;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.mybatis.dao.OfferDao;
import net.thumbtack.school.elections.mybatis.dao.RatingDao;
import net.thumbtack.school.elections.mybatis.dao.VoterDao;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.RatingDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.dto.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.AllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;

import java.util.UUID;

public class VoterService {

    private VoterDao voterDao;
    private OfferDao offerDao;
    private RatingDao ratingDao;

    public VoterService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
        this.ratingDao = new RatingDaoImpl();
    }

    public String registerVoter(String requestJson) {
        if(Server.isStartingVoting()) {
            VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
            votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
            return new Gson().toJson(votingOperationsErrorCode);
        }
        String response = isValidJson(requestJson);
        if(!response.equals("")) {
            return response;
        }
        if(!validateVoter(requestJson).equals("")) {
            return validateVoter(requestJson);
        }
        Voter voter = new Gson().fromJson(requestJson, Voter.class);
        voter.setTokenIsNewValue();
        voterDao.insert(voter);
        return new Gson().toJson(new RegisterVoterDtoResponse(voter.getToken()));
    }

    private String validateVoter(String jsonRequest) {
        RegisterVoterDtoRequest voterDTO = new Gson().fromJson(jsonRequest, RegisterVoterDtoRequest.class);
        String checkIsEmptyResult = checkIsEmptyFieldsVoter(voterDTO);
        if(!checkIsEmptyResult.equals("")) {
            return checkIsEmptyResult;
        }
        String checkLengthResult = checkLengthFieldsVoter(voterDTO);
        if(!checkLengthResult.equals("")) {
            return checkLengthResult;
        }
        String checkLoginResult = checkSameLogin(voterDTO);
        if(!checkLoginResult.equals("")) {
            return checkLoginResult;
        }
        String checkSameVoterResult = checkSameVoter(voterDTO);
        if(!checkSameVoterResult.equals("")) {
            return checkSameVoterResult;
        }
        return "";
    }

    public String login(String requestJson) {
        if(Server.isStartingVoting()) {
            VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
            votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
            return new Gson().toJson(votingOperationsErrorCode);
        }
        String response = isValidJson(requestJson);
        if(!response.equals("")) {
            return response;
        }
        RegisterVoterDtoRequest voterDTO  = new Gson().fromJson(requestJson, RegisterVoterDtoRequest.class);
        LoginVoterErrorCode loginVoterErrorCode = new LoginVoterErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if(voter.getLogin().equals(voterDTO.getLogin())) {
                if(voter.getPassword().equals(voterDTO.getPassword())) {
                            voterDao.loginAndSetNewToken(voterDTO.getLogin(), UUID.randomUUID().toString());
                    return "";
                } else {
                    return new Gson().toJson(loginVoterErrorCode.setErrorString(loginVoterErrorCode.getErrorPassword()));
                }
            }
        }
        return new Gson().toJson(loginVoterErrorCode.setErrorString(loginVoterErrorCode.getNotFoundLogin()));
    }

    public String logout(String jsonRequest) {
        if(Server.isStartingVoting()) {
            VotingOperationsErrorCode votingOperationsErrorCode = new VotingOperationsErrorCode();
            votingOperationsErrorCode.setErrorString(votingOperationsErrorCode.getStartVoting());
            return new Gson().toJson(votingOperationsErrorCode);
        }
        String response = isValidJson(jsonRequest);
        if(!response.equals("")) {
            return response;
        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        LogoutVoterErrorCode logoutVoterErrorCode = new LogoutVoterErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(logoutVoterErrorCode.setErrorString(logoutVoterErrorCode.getNotFoundToken()));
        }
       voterDao.logoutByToken(tokenVoterDto.getToken());
        for(Offer offer : offerDao.getAll()) {
            if (offer.getAuthor_token().equals(tokenVoterDto.getToken())) {
                offerDao.updateSetEmptyAuthor(offer.getContent());
            }
            for(Rating rating : ratingDao.getAll()) {
                if (rating.getToken_evaluating_voter().equals(tokenVoterDto.getToken())) {
                    ratingDao.deleteById(rating.getId());
                }
            }
        }
        return "";
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

    private String checkLengthFieldsVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterVoterErrorCode registerVoterErrorCode = new RegisterVoterErrorCode();
        if (voterDTO.getFirstName().length() > 30 || voterDTO.getFirstName().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthFirstName()));
        }
        if (voterDTO.getLastName().length() > 30 || voterDTO.getLastName().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthLastName()));
        }
        if (voterDTO.getPatronymic().length() > 30 || voterDTO.getPatronymic().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthPatronymic()));
        }
        if (voterDTO.getStreet().length() > 30 || voterDTO.getStreet().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthStreet()));
        }
        if (voterDTO.getHouse().length() > 30 || voterDTO.getHouse().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthHouse()));
        }
        if (voterDTO.getApartment().length() > 30 || voterDTO.getApartment().length() < 1) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthApartment()));
        }
        if (voterDTO.getLogin().length() > 30 || voterDTO.getLogin().length() < 6) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthLogin()));
        }
        if (voterDTO.getPassword().length() > 30 || voterDTO.getPassword().length() < 6) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getLengthPassword()));
        }
        return "";
    }

    private String checkIsEmptyFieldsVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterVoterErrorCode registerVoterErrorCode = new RegisterVoterErrorCode();
        if (voterDTO.getFirstName().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullFirstName()));
        }
        if (voterDTO.getLastName().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullLastName()));
        }
        if (voterDTO.getStreet().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullStreet()));
        }
        if (voterDTO.getHouse().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullHouse()));
        }
        if (voterDTO.getLogin().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullLogin()));
        }
        if (voterDTO.getPassword().isEmpty()) {
            return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getNullPassword()));
        }
        return "";
    }

    private String checkSameLogin(RegisterVoterDtoRequest voterDTO) {
        RegisterVoterErrorCode registerVoterErrorCode = new RegisterVoterErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if(voter.getLogin().equals(voterDTO.getLogin())) {
                return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getSameLogin()));
            }
        }
        return "";
    }

    private String checkSameVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterVoterErrorCode registerVoterErrorCode = new RegisterVoterErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if (voter.hashCode() == voterDTO.hashCode()) {
                return new Gson().toJson(registerVoterErrorCode.setErrorString(registerVoterErrorCode.getSameVoter()));
            }
        }
        return "";
    }

    public String getAllVoters() {
        AllVotersDtoResponse allVotersDtoResponse = new AllVotersDtoResponse(voterDao.getAll());
        return new Gson().toJson(allVotersDtoResponse);
    }

    public void deleteAllVoters() {
        voterDao.deleteAll();
    }
}
