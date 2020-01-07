package net.thumbtack.school.elections.errors.voter;

public class TokenVoterErrorCode {

    private String notFoundToken = "Токен не действителен.Для получения токена " +
            "вам нужно авторизоваться, либо зарегистрироваться в системе.";

    private String errorString;

    public TokenVoterErrorCode() {

    }

    public String getNotFoundToken() {
        return notFoundToken;
    }

    public void setNotFoundToken(String notFoundToken) {
        this.notFoundToken = notFoundToken;
    }

    public String getErrorString() {
        return errorString;
    }

    public TokenVoterErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
