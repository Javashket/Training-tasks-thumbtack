package net.thumbtack.school.elections.dto.request;

public class DeleteOfferInYourProgramDtoRequest {

    String token;
    String context;

    public DeleteOfferInYourProgramDtoRequest(String token, String context) {
        this.token = token;
        this.context = context;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
