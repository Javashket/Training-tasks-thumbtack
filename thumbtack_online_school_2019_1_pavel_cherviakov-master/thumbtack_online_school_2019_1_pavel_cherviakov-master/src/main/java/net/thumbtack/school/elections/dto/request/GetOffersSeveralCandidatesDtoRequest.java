package net.thumbtack.school.elections.dto.request;

import java.util.ArrayList;
import java.util.List;

public class GetOffersSeveralCandidatesDtoRequest {

    private String token;
    private List<String> candidates_token;

    public GetOffersSeveralCandidatesDtoRequest(String token) {
        this.candidates_token = new ArrayList<>();
        this.token = token;
    }

    public List<String> getCandidates_token() {
        return candidates_token;
    }

    public void addCandidateToken(String token) {
        this.candidates_token.add(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCandidates_token(List<String> candidates_token) {
        this.candidates_token = candidates_token;
    }
}
