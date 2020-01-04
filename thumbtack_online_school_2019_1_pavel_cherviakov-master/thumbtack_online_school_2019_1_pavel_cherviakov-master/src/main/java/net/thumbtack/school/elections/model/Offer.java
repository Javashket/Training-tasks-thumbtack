package net.thumbtack.school.elections.model;

import java.util.*;

public class Offer {

    private int id;
    private Voter voter;
    private String content;
    private List<Rating> ratings;

    public Offer() {
    }

    public Offer(Voter voter, String content) {
        this.voter = voter;
        this.content = content;
        this.ratings = new ArrayList<>();
    }

    public Voter getAuthor() {
        return voter;
    }

    public void setAuthor() {
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }


    public void setRatingForVoter(Rating rating) {
//        this.ratings.remove(rating.getVoter());
//        this.addRating(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(getVoter(), offer.getVoter()) &&
                Objects.equals(getContent(), offer.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter(), getContent());
    }
}
