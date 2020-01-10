package net.thumbtack.school.elections.dto.request;

public class PutOnMayorDtoRequest {

    private String pushing_voter_token;
    private RegisterVoterDtoRequest registerVoterDtoRequest;

    public PutOnMayorDtoRequest() {
    }

    public PutOnMayorDtoRequest(String pushing_voter_token, RegisterVoterDtoRequest registerVoterDtoRequest) {
        this.pushing_voter_token = pushing_voter_token;
        this.registerVoterDtoRequest = registerVoterDtoRequest;
    }

    public String getPushing_voter_token() {
        return pushing_voter_token;
    }

    public void setPushing_voter_token(String pushing_voter_token) {
        this.pushing_voter_token = pushing_voter_token;
    }

    public RegisterVoterDtoRequest getRegisterVoterDtoRequest() {
        return registerVoterDtoRequest;
    }

    public void setRegisterVoterDtoRequest(RegisterVoterDtoRequest registerVoterDtoRequest) {
        this.registerVoterDtoRequest = registerVoterDtoRequest;
    }
}
