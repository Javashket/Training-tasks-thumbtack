package net.thumbtack.school.elections.dto.request;

public class VoteDtoRequest {

    private String token;
    private RegisterVoterDtoRequest registerVoterDtoRequest;

    public VoteDtoRequest(String token, RegisterVoterDtoRequest registerVoterDtoRequest) {
        this.token = token;
        this.registerVoterDtoRequest = registerVoterDtoRequest;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterVoterDtoRequest getRegisterVoterDtoRequest() {
        return registerVoterDtoRequest;
    }

    public void setRegisterVoterDtoRequest(RegisterVoterDtoRequest registerVoterDtoRequest) {
        this.registerVoterDtoRequest = registerVoterDtoRequest;
    }
}
