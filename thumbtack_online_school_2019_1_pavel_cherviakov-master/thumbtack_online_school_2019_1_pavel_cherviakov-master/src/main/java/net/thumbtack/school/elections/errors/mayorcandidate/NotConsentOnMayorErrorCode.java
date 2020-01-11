package net.thumbtack.school.elections.errors.mayorcandidate;

public class NotConsentOnMayorErrorCode {

    private String notConsent = "Сначала надо дать согласие на выдвижение.Перед этим выдвинуть кандидатуру.";

    private String errorString;

    public NotConsentOnMayorErrorCode() {

    }

    public String getNotConsent() {
        return notConsent;
    }

    public void setNotConsent(String notConsent) {
        this.notConsent = notConsent;
    }

    public String getErrorString() {
        return errorString;
    }

    public NotConsentOnMayorErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
