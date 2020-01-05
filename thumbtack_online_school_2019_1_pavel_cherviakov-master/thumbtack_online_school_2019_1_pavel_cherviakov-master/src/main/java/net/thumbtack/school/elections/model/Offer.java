package net.thumbtack.school.elections.model;

import java.util.*;

public class Offer implements Comparable<Offer>{

    private int id;
    private String author_token;
    private String content;
    private double average_rating;
    private List<Rating> ratings;

    public Offer() {
    }

    public Offer(String author_token, String content) {
        this.author_token = author_token;
        this.content = content;
        this.average_rating = 0;
        Rating rating = new Rating(author_token, 5);
    }

    public void setAuthor() {
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getAuthor_token() {
        return author_token;
    }

    public void setAuthor_token(String author_token) {
        this.author_token = author_token;
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

    public void addRate(Rating rating) {
            this.ratings.removeIf(rating1 -> rating1.getToken_evaluating_voter().equals(rating.getToken_evaluating_voter()));
            this.ratings.add(rating);
            double summRating = 0;
            for (Rating rating1 : this.ratings) {
                summRating += rating1.getRating();
            }
            this.average_rating = summRating / this.ratings.size();
    }

    @Override
    public int compareTo(Offer offer) {
        if(this.average_rating < offer.average_rating) {
            return -1;
        } else if(offer.average_rating < this.average_rating) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(getAuthor_token(), offer.getAuthor_token()) &&
                Objects.equals(getContent(), offer.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor_token(), getContent());
    }
}
