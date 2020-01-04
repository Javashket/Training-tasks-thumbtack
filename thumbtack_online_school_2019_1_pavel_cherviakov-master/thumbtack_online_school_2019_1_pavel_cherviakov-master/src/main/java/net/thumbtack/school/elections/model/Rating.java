package net.thumbtack.school.elections.model;

import java.util.Objects;

public class Rating {

    private int id;
    private Voter voter;
    private int rating;

    public Rating() {

    }

    public Rating(Voter voter, int rating) {
        this.voter = voter;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return getRating() == rating1.getRating() &&
                Objects.equals(getVoter(), rating1.getVoter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter(), getRating());
    }
}

