package net.thumbtack.school.elections.response;

import net.thumbtack.school.elections.model.Voter;

import java.util.List;

public class AllVotersDtoResponse {

    private List<Voter> voters;

    public AllVotersDtoResponse() {
    }

    public AllVotersDtoResponse(List<Voter> voters) {
        this.voters = voters;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }
}
