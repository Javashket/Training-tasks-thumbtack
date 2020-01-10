package net.thumbtack.school.elections.errors.mayorcandidate;

public class AddMayorCandidateErrorCode {

    private String notMayorCandidate = "Кандидат в мэры не найден, он либо не существует, либо он выполнил логаут.";

    private String errorString;

    public AddMayorCandidateErrorCode() {

    }

    public String getNotMayorCandidate() {
        return notMayorCandidate;
    }

    public void setNotMayorCandidate(String notMayorCandidate) {
        this.notMayorCandidate = notMayorCandidate;
    }

    public String getErrorString() {
        return errorString;
    }

    public AddMayorCandidateErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
