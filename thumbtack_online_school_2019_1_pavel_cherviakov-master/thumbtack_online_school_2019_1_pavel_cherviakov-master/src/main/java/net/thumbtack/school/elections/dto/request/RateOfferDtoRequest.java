package net.thumbtack.school.elections.dto.request;

public class RateOfferDtoRequest {

    private String content;
    private String tokenEvaluating;
    private int rating;

    public RateOfferDtoRequest() {
    }

    public RateOfferDtoRequest(String content, String tokenEvaluating, int rating) {
        this.content = content;
        this.tokenEvaluating = tokenEvaluating;
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTokenEvaluating() {
        return tokenEvaluating;
    }

    public void setTokenEvaluating(String tokenEvaluating) {
        this.tokenEvaluating = tokenEvaluating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
