package net.thumbtack.school.elections.errors.mayorcandidate;

public class LogoutMayorErrorCode {

    private String logoutMayor = "Чтобы сделать логаут вам нужно снять кандидатуру с поста мэра.";

    private String errorString;

    public LogoutMayorErrorCode() {

    }

    public String getLogoutMayor() {
        return logoutMayor;
    }

    public void setLogoutMayor(String logoutMayor) {
        this.logoutMayor = logoutMayor;
    }

    public String getErrorString() {
        return errorString;
    }

    public LogoutMayorErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
