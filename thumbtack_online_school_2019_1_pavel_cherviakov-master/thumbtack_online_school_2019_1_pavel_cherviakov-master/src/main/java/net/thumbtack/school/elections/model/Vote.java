package net.thumbtack.school.elections.model;

import java.util.Objects;

public class Vote {

    private int id;
    private Voter voter;
    private boolean vote;

    public Vote(Voter voter, boolean vote) {
        this.voter = voter;
        this.vote = vote;
    }

    public Vote(Integer a, Integer b, Boolean c, Integer d) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return Objects.equals(getVoter(), vote.getVoter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter());
    }
}
