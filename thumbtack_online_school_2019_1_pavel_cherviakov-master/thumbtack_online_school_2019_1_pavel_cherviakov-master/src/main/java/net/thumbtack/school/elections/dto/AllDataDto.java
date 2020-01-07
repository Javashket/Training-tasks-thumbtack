package net.thumbtack.school.elections.dto;

import net.thumbtack.school.elections.model.MayorCandidate;
import net.thumbtack.school.elections.model.Offer;
import net.thumbtack.school.elections.model.Rating;
import net.thumbtack.school.elections.model.Voter;

import java.util.List;

public class AllDataDto {

    private List<MayorCandidate> mayorCandidates;
    private List<Offer> offers;
    private List<Voter> voters;
//    private List<Rating> ratings;

    public AllDataDto() {
    }

    public AllDataDto(List<MayorCandidate> mayorCandidates, List<Offer> offers, List<Voter> voters) {
        this.mayorCandidates = mayorCandidates;
        this.offers = offers;
        this.voters = voters;
//        this.ratings = ratings;
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

//    public List<Rating> getRatings() {
//        return ratings;
//    }

//    public void setRatings(List<Rating> ratings) {
//        this.ratings = ratings;
//    }
}
