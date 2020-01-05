package net.thumbtack.school.elections.model;

import java.util.Objects;

public class Rating {

    private int id;
    private String token_evaluating_voter;
    private int rating;

    public Rating() {

    }

    public Rating(String token_evaluating_voter, int rating) {
        this.token_evaluating_voter = token_evaluating_voter;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken_evaluating_voter() {
        return token_evaluating_voter;
    }

    public void setToken_evaluating_voter(String token_evaluating_voter) {
        this.token_evaluating_voter = token_evaluating_voter;
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
                Objects.equals(getToken_evaluating_voter(), rating1.getToken_evaluating_voter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken_evaluating_voter(), getRating());
    }
}

