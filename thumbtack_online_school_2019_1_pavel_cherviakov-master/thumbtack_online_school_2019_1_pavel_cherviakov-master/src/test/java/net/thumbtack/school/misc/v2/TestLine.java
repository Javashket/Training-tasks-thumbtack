package net.thumbtack.school.misc.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLine {

    @Test
    public void testAuto1() {
        Line line = new Line(10);
        line.resize(2);
        assertAll(
                () -> assertEquals(20, line.getLength())
        );
    }
}
