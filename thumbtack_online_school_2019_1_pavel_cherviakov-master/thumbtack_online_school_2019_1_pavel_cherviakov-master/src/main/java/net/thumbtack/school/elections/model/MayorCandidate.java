package net.thumbtack.school.elections.model;

import java.util.*;

public class MayorCandidate {

    private int id;
    private String token_voter;
    private boolean consentOnNomination;
    private List<Vote> votedVoters;
    private List<Offer> program;

    public MayorCandidate() {

    }

    public MayorCandidate(String token) {
        this.token_voter = token;
        this.consentOnNomination = false;
        this.votedVoters = new ArrayList<>();
        this.program = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken_voter() {
        return token_voter;
    }

    public void setToken_voter(String token_voter) {
        this.token_voter = token_voter;
    }

    public boolean isConsentOnNomination() {
        return consentOnNomination;
    }

    public void setConsentOnNomination(boolean consentOnNomination) {
        this.consentOnNomination = consentOnNomination;
    }

    public List<Vote> getVotedVoters() {
        return votedVoters;
    }

    public void setVotedVoters(List<Vote> votedVoters) {
        this.votedVoters = votedVoters;
    }

    public List<Offer> getProgram() {
        return program;
    }

    public void setProgram(List<Offer> program) {
        this.program = program;
    }

    public void addVote(Vote vote) {
        this.votedVoters.add(vote);
    }

    public void addOffer(Offer offer) {
        this.program.add(offer);
    }

    //    public Integer getCountVoicesPros() {
//        int count = 0;
//        for(Map.Entry<Voter, Boolean> entry : votedVoters.entrySet()) {
//            if (entry.getValue().equals(true) && entry.getKey().isActive()) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public Integer getCountVoicesCons() {
//        int count = 0;
//        for(Map.Entry<Voter, Boolean> entry : votedVoters.entrySet()) {
//            if (entry.getValue().equals(false) && entry.getKey().isActive()) {
//                count++;
//            }
//        }
//        return count;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MayorCandidate)) return false;
        MayorCandidate that = (MayorCandidate) o;
        return Objects.equals(getToken_voter(), that.getToken_voter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken_voter());
    }
}
