package net.thumbtack.school.elections.dto.request;

public class PutOnMayorDtoRequest {

    private String pushing_voter_token;
    private int hashcode_voter_on_mayor;

    public PutOnMayorDtoRequest() {
    }

    public PutOnMayorDtoRequest(String pushing_voter_token, int hashcode_voter_on_mayor) {
        this.pushing_voter_token = pushing_voter_token;
        this.hashcode_voter_on_mayor = hashcode_voter_on_mayor;
    }

    public String getPushing_voter_token() {
        return pushing_voter_token;
    }

    public void setPushing_voter_token(String pushing_voter_token) {
        this.pushing_voter_token = pushing_voter_token;
    }

    public int getHashcode_voter_on_mayor() {
        return hashcode_voter_on_mayor;
    }

    public void setHashcode_voter_on_mayor(int hashcode_voter_on_mayor) {
        this.hashcode_voter_on_mayor = hashcode_voter_on_mayor;
    }
}
