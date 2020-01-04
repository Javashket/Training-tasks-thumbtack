package net.thumbtack.school.misc.v3;

import net.thumbtack.school.iface.v3.Colored;
import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Resizable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTypes {
    @Test
    public void testTypes() {
        assertTrue(Colored.class.isAssignableFrom(Auto.class));
        assertTrue(HasArea.class.isAssignableFrom(House.class));
        assertTrue(Resizable.class.isAssignableFrom(Line.class));
    }
}
