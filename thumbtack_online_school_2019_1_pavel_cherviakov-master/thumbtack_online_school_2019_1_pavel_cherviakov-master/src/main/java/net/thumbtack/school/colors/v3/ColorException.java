package net.thumbtack.school.colors.v3;

public class ColorException extends Exception {

    private ColorErrorCode colorErrorCode;

    public ColorException(ColorErrorCode colorErrorCode) {
        this.colorErrorCode = colorErrorCode;
    }

    public ColorErrorCode getColorErrorCode() {
        return colorErrorCode;
    }
}
