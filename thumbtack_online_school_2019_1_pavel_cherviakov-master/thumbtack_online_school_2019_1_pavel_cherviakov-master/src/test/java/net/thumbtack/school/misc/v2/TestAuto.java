package net.thumbtack.school.misc.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAuto {

    @Test
    public void testAuto1() {
        Auto auto = new Auto(10);
        assertAll(
                () -> assertEquals(10, auto.getColor())
        );
    }
}
