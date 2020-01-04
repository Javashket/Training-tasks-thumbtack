package net.thumbtack.school.elections.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MayorCandidate {

    private int id;
    private Voter voter;
    private boolean consentOnNomination;
    private Map<Voter, Boolean> votedVoters;
    private List<Offer> programm;

    public MayorCandidate() {

    }

    public MayorCandidate(Voter condidate) {
        this.voter = condidate;
        this.consentOnNomination = false;
        this.votedVoters = new HashMap<>();
    }

    public List<Offer> getProgramm() {
        return programm;
    }

    public void addOfferToProgramm(Offer offer) {
        this.programm.add(offer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Map<Voter, Boolean> getVotedVoters() {
        return votedVoters;
    }

    public void setVotedVoters(HashMap<Voter, Boolean> votedVoters) {
        this.votedVoters = votedVoters;
    }

    public void addVotedVoter(Voter voter, Boolean voice) {
        this.votedVoters.put(voter,voice);
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

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public boolean isConsentOnNomination() {
        return consentOnNomination;
    }

    public void setConsentOnNomination(boolean consentOnNomination) {
        this.consentOnNomination = consentOnNomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MayorCandidate)) return false;
        MayorCandidate that = (MayorCandidate) o;
        return Objects.equals(getVoter(), that.getVoter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter());
    }
}
