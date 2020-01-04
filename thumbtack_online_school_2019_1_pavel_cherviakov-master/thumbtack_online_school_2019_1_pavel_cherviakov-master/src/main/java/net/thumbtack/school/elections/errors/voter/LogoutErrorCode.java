package net.thumbtack.school.elections.errors.voter;

public class LogoutErrorCode {

    private String notFoundToken = "Токен не действителен.";

    private String errorString;

    public LogoutErrorCode() {

    }

    public String getNotFoundToken() {
        return notFoundToken;
    }

    public String getErrorString() {
        return errorString;
    }

    public LogoutErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }
}
