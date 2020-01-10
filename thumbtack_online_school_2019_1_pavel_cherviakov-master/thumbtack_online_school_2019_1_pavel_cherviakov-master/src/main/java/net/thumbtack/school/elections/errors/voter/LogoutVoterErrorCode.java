package net.thumbtack.school.elections.errors.voter;

public class LogoutVoterErrorCode {

    private String notFoundToken = "Токен не действителен. Либо пользователя не существует.";

    private String errorString;

    public LogoutVoterErrorCode() {

    }

    public String getNotFoundToken() {
        return notFoundToken;
    }

    public String getErrorString() {
        return errorString;
    }

    public LogoutVoterErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }
}
