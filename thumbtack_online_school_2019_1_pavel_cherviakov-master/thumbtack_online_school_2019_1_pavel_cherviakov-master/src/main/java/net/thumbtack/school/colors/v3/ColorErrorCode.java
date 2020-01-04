package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {

    WRONG_COLOR_STRING,
    NULL_COLOR;

    private String errorString;

    ColorErrorCode() {

    }

    ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
