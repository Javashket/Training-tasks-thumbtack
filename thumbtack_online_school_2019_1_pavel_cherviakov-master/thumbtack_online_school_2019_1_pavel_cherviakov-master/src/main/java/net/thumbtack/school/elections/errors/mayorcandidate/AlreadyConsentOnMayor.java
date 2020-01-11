package net.thumbtack.school.elections.errors.mayorcandidate;

public class AlreadyConsentOnMayor {

    private String alreadyConfirm = "Согласие уже подтверждено.";

    private String errorString;

    public AlreadyConsentOnMayor() {

    }

    public String getAlreadyConfirm() {
        return alreadyConfirm;
    }

    public void setAlreadyConfirm(String alreadyConfirm) {
        this.alreadyConfirm = alreadyConfirm;
    }

    public String getErrorString() {
        return errorString;
    }

    public AlreadyConsentOnMayor setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }
}
