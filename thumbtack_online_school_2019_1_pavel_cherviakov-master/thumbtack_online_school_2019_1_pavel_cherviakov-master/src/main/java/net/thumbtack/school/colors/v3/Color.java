package net.thumbtack.school.colors.v3;

public enum Color {
    RED,
    GREEN,
    BLUE;

    public static void coloredEqualsNull (Color color) throws ColorException {
        if(color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
    }

    public static Color colorFromString(String colorString) throws ColorException {
        if(colorString == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        for (Color color : Color.values()) {
            if(colorString.equals(String.valueOf(color))) {
                return color;
            }
        }
        throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
    }
}
