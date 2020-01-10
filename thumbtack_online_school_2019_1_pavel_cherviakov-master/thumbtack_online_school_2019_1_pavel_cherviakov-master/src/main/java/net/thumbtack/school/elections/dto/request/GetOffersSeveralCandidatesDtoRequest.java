package net.thumbtack.school.elections.dto.request;

import java.util.ArrayList;
import java.util.List;

public class GetOffersSeveralCandidatesDtoRequest {

    private List<String> candidates_token;

    public GetOffersSeveralCandidatesDtoRequest() {
        this.candidates_token = new ArrayList<>();
    }

    public List<String> getCandidates_token() {
        return candidates_token;
    }

    public void addCandidateToken(String token) {
        this.candidates_token.add(token);
    }

    public void setCandidates_token(List<String> candidates_token) {
        this.candidates_token = candidates_token;
    }
}
