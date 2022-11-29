package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        // basic tests
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD}, new OptionsParser().parse(new String[]{"forward", "f"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.BACKWARD}, new OptionsParser().parse(new String[]{"backward", "b"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.RIGHT}, new OptionsParser().parse(new String[]{"right", "r"}));
        assertArrayEquals(new MoveDirection[]{MoveDirection.LEFT, MoveDirection.LEFT}, new OptionsParser().parse(new String[]{"left", "l"}));

        // exceptions tests
        IllegalArgumentException thrown;
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            new OptionsParser().parse(new String[]{"left", "g", "RIGHT", "Forward", "f", "", "BACK b", "b", "right"});
        });
        assertEquals("g is not legal move specification", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            new OptionsParser().parse(new String[]{"left", "f", "", "BACK b", "b", "right"});
        });
        assertEquals(" is not legal move specification", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            new OptionsParser().parse(new String[]{"left", "f", "BACK b", "b", "right"});
        });
        assertEquals("BACK b is not legal move specification", thrown.getMessage());
    }
}