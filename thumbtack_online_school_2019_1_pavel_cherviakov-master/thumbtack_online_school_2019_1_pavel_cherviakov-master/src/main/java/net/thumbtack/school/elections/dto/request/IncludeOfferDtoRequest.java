package net.thumbtack.school.elections.dto.request;

public class IncludeOfferDtoRequest {

    String token;
    String content;

    public IncludeOfferDtoRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
