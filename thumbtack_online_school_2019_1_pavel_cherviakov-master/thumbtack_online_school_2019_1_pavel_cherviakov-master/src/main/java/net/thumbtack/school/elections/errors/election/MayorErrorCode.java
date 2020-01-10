package net.thumbtack.school.elections.errors.election;

public class MayorErrorCode {

    private String errorVote = "Кандидат не может голосовать сам за себя.";

    private String errorString;

    public MayorErrorCode() {

    }

    public String getErrorVote() {
        return errorVote;
    }

    public void setErrorVote(String errorVote) {
        this.errorVote = errorVote;
    }

    public String getErrorString() {
        return errorString;
    }

    public MayorErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
