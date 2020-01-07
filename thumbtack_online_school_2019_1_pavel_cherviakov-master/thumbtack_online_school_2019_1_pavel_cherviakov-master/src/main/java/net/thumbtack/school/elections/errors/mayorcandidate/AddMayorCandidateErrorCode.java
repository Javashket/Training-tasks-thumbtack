package net.thumbtack.school.elections.errors.mayorcandidate;

import net.thumbtack.school.elections.errors.voter.TokenVoterErrorCode;

public class AddMayorCandidateErrorCode {

    private String notMayorCandidate = "Кандидат в мэры не найден, он либо не существует, либо его токен не действителен.";

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
