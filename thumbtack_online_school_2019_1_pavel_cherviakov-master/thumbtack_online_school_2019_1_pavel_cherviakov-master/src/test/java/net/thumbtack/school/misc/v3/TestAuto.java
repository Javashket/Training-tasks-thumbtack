package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAuto {

    @Test
    public void testAuto1() {
        Auto auto = new Auto(Color.BLUE);
        assertAll(
                () -> assertEquals(Color.BLUE, auto.getColor())
        );
    }
}
