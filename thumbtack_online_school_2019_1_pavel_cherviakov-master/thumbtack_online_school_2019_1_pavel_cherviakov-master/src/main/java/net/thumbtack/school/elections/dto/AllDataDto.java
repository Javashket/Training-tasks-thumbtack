package net.thumbtack.school.elections.dto;

import net.thumbtack.school.elections.model.*;

import java.util.List;

public class AllDataDto {

    private List<MayorCandidate> mayorCandidates;
    private List<Offer> offers;
    private List<Voter> voters;
    private List<Vote> votes;

    public AllDataDto() {
    }

    public AllDataDto(List<MayorCandidate> mayorCandidates, List<Offer> offers, List<Voter> voters, List<Vote> votes) {
        this.mayorCandidates = mayorCandidates;
        this.offers = offers;
        this.voters = voters;
        this.votes = votes;
    }

    public List<MayorCandidate> getMayorCandidates() {
        return mayorCandidates;
    }

    public void setMayorCandidates(List<MayorCandidate> mayorCandidates) {
        this.mayorCandidates = mayorCandidates;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
