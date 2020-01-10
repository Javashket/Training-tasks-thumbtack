package net.thumbtack.school.elections.errors.election;

public class VotingOperationsErrorCode {

    private String startVoting = "Голосование начато.Запрещена регистрация новых избирателей," +
            " любые изменения в списках кандидатов, предложений.";

    private String errorString;

    public VotingOperationsErrorCode() {

    }

    public String getStartVoting() {
        return startVoting;
    }

    public void setStartVoting(String startVoting) {
        this.startVoting = startVoting;
    }

    public String getErrorString() {
        return errorString;
    }

    public VotingOperationsErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
