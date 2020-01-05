package net.thumbtack.school.elections.errors.voter;

public class LoginVoterErrorCode {

    private String notFoundLogin = "Логин не найден.";
    private String errorPassword = "Неверный пароль.";

    private String errorString;

    public LoginVoterErrorCode() {

    }

    public String getNotFoundLogin() {
        return notFoundLogin;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public String getErrorString() {
        return errorString;
    }

    public LoginVoterErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
