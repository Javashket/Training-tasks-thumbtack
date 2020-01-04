package net.thumbtack.school.elections.response;

import java.util.Objects;

public class RegisterVoterDtoResponse {

    private String token;

    public RegisterVoterDtoResponse() {
    }

    public RegisterVoterDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterVoterDtoResponse)) return false;
        RegisterVoterDtoResponse that = (RegisterVoterDtoResponse) o;
        return Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }
}
