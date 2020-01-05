package net.thumbtack.school.elections.errors.json;

public class SyntaxJsonErrorCode {

    private String errorSyntax = "Ошибка синтаксиса JSON.";

    private String errorString;

    public SyntaxJsonErrorCode() {

    }

    public String getErrorSyntax() {
        return errorSyntax;
    }

    public void setErrorSyntax(String errorSyntax) {
        this.errorSyntax = errorSyntax;
    }

    public String getErrorString() {
        return errorString;
    }

    public SyntaxJsonErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
