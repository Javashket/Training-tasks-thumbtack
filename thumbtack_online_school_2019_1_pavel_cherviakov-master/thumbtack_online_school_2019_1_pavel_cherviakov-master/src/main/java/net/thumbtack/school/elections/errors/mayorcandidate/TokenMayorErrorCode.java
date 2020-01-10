package net.thumbtack.school.elections.errors.mayorcandidate;

public class TokenMayorErrorCode {

    private String notFoundToken = "Токен среди кандидатов в мэры не найден.";

    private String errorString;

    public TokenMayorErrorCode() {

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

    public TokenMayorErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }
}
