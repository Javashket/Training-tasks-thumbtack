package net.thumbtack.school.elections.dto.request;

public class DeleteRatingDtoRequest {

    private String contextOffer;
    private String token;

    public DeleteRatingDtoRequest(String contextOffer, String token) {
        this.contextOffer = contextOffer;
        this.token = token;
    }

    public String getContextOffer() {
        return contextOffer;
    }

    public void setContextOffer(String contextOffer) {
        this.contextOffer = contextOffer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
