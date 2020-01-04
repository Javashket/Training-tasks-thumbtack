package net.thumbtack.school.elections.service;

import com.google.gson.*;
import net.thumbtack.school.elections.errors.voter.LoginErrorCode;
import net.thumbtack.school.elections.errors.voter.LogoutErrorCode;
import net.thumbtack.school.elections.errors.voter.RegisterErrorCode;
import net.thumbtack.school.elections.mybatis.daoimpl.OfferDaoImpl;
import net.thumbtack.school.elections.mybatis.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.model.Voter;
import net.thumbtack.school.elections.request.TokenVoterDtoRequest;
import net.thumbtack.school.elections.response.AllVotersDtoResponse;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

import java.util.UUID;

public class VoterService {

    private VoterDaoImpl voterDao;
    private OfferDaoImpl offerDao;

    public VoterService() {
        this.voterDao = new VoterDaoImpl();
        this.offerDao = new OfferDaoImpl();
    }

    public String registerVoter(String requestJson) {
//        if(Server.isStartingServer()) {
//            return "{'error':'Сервер не запущен.'}";
//        }
//        if(Server.isStartingVoting()) {
//            return "{'error':'регистрация закрыта. Идет голосование.'}";
//        }
//        String response = isValidJson(requestJsonString);
//        if(!response.equals("")) {
//            return response;
//        }
        if(!validateVoter(requestJson).equals("")) {
            return validateVoter(requestJson);
        }
//        if(response.equals("")) {
            Voter voter = new Gson().fromJson(requestJson, Voter.class);
            voter.setTokenIsNewValue();
            try {
                voterDao.insert(voter);
            }catch (IllegalArgumentException e) {
                return "{'error':'" + e.getMessage() + "'}";
            }
            return new Gson().toJson(new RegisterVoterDtoResponse(voter.getToken()));
//        }

    }

    private String validateVoter(String jsonRequest) {
//        if(Server.isStartingServer()) {
//            return "{'error':'Сервер не запущен.'}";
//        }
//        String response = isValidJson(jsonRequest);
//        if(!response.equals("")) {
//            return response;
//        }
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
////        if(Server.isStartingServer()) {
////            return "{'error':'Сервер не запущен.'}";
////        }
////        String response = isValidJson(requestJsonString);
////        if(!response.equals("")) {
////            return response;
////        }
        RegisterVoterDtoRequest voterDTO  = new Gson().fromJson(requestJson, RegisterVoterDtoRequest.class);
        LoginErrorCode loginErrorCode = new LoginErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if(voter.getLogin().equals(voterDTO.getLogin())) {
                if(voter.getPassword().equals(voterDTO.getPassword())) {
                            voterDao.loginAndSetNewToken(voterDTO.getLogin(), UUID.randomUUID().toString());
                    return "";
                } else {
                    return new Gson().toJson(loginErrorCode.setErrorString(loginErrorCode.getErrorPassword()));
                }
            }
        }
        return new Gson().toJson(loginErrorCode.setErrorString(loginErrorCode.getNotFoundLogin()));
    }

    public String logout(String jsonRequest) {
//        if(Server.isStartingServer()) {
//            return "{'error':'Сервер не запущен.'}";
//        }
//        String response = isValidJson(requestJsonString);
//        if(!response.equals("")) {
//            return response;
//        }
        TokenVoterDtoRequest tokenVoterDto = new Gson().fromJson(jsonRequest, TokenVoterDtoRequest.class);
        // добавить если token не найден либо не найден пользователь по токену
        LogoutErrorCode logoutErrorCode = new LogoutErrorCode();
        if(voterDao.getByToken(tokenVoterDto.getToken()) == null) {
            return new Gson().toJson(logoutErrorCode.setErrorString(logoutErrorCode.getNotFoundToken()));
        }
       voterDao.logoutByToken(tokenVoterDto.getToken());
//        for(Offer offer : offerDao.getAll()) {
//            // удалить автора если автор
//            if () {
//
//            }
//            // удалить оценки если ставил их
//            if() {
//
//            }
//        }
//        // удалить оценки предложений
////        voterDao.delete(voterDao.getByToken(token));
////        voterDao.insert(voter);
//        //обнулить авторство предложений
        return "";
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

    private String checkLengthFieldsVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterErrorCode registerErrorCode = new RegisterErrorCode();
        if (voterDTO.getFirstName().length() > 30 || voterDTO.getFirstName().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthFirstName()));
        }
        if (voterDTO.getLastName().length() > 30 || voterDTO.getLastName().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthLastName()));
        }
        if (voterDTO.getPatronymic().length() > 30 || voterDTO.getPatronymic().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthPatronymic()));
        }
        if (voterDTO.getStreet().length() > 30 || voterDTO.getStreet().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthStreet()));
        }
        if (voterDTO.getHouse().length() > 30 || voterDTO.getHouse().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthHouse()));
        }
        if (voterDTO.getApartment().length() > 30 || voterDTO.getApartment().length() < 1) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthApartment()));
        }
        if (voterDTO.getLogin().length() > 30 || voterDTO.getLogin().length() < 6) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthLogin()));
        }
        if (voterDTO.getPassword().length() > 30 || voterDTO.getPassword().length() < 6) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getLengthPassword()));
        }
        return "";
    }

    private String checkIsEmptyFieldsVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterErrorCode registerErrorCode = new RegisterErrorCode();
        if (voterDTO.getFirstName().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullFirstName()));
        }
        if (voterDTO.getLastName().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullLastName()));
        }
        if (voterDTO.getStreet().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullStreet()));
        }
        if (voterDTO.getHouse().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullHouse()));
        }
        if (voterDTO.getLogin().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullLogin()));
        }
        if (voterDTO.getPassword().isEmpty()) {
            return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getNullPassword()));
        }
        return "";
    }

    private String checkSameLogin(RegisterVoterDtoRequest voterDTO) {
        RegisterErrorCode registerErrorCode = new RegisterErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if(voter.getLogin().equals(voterDTO.getLogin())) {
                return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getSameLogin()));
            }
        }
        return "";
    }

    private String checkSameVoter(RegisterVoterDtoRequest voterDTO) {
        RegisterErrorCode registerErrorCode = new RegisterErrorCode();
        for (Voter voter : voterDao.getAll()) {
            if (voter.hashCode() == voterDTO.hashCode()) {
                return new Gson().toJson(registerErrorCode.setErrorString(registerErrorCode.getSameVoter()));
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
