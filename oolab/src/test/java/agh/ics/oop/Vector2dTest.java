package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        assertEquals("(1,2)", new Vector2d(1, 2).toString());
        assertEquals("(0,0)", new Vector2d(0, 0).toString());
        assertEquals("(-2,-15)", new Vector2d(-2, -15).toString());
    }

    @Test
    void precedes() {
        assertTrue(new Vector2d(1, 3).precedes(new Vector2d(2, 4)));
        assertTrue(new Vector2d(1, 3).precedes(new Vector2d(1, 4)));
        assertTrue(new Vector2d(1, 3).precedes(new Vector2d(2, 3)));
        assertFalse(new Vector2d(1, 3).precedes(new Vector2d(0, 4)));
        assertFalse(new Vector2d(1, 3).precedes(new Vector2d(2, 2)));
        assertTrue(new Vector2d(-1, -2).precedes(new Vector2d(0, -1)));
        assertTrue(new Vector2d(-1, -2).precedes(new Vector2d(-1, -2)));
        assertTrue(new Vector2d(4, 6).precedes(new Vector2d(4, 6)));
    }

    @Test
    void follows() {
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(2, 4)));
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(2, 3)));
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(1, 4)));
        assertTrue(new Vector2d(1, 3).follows(new Vector2d(0, 3)));
        assertTrue(new Vector2d(1, 3).follows(new Vector2d(1, 2)));
        assertTrue(new Vector2d(1, 3).follows(new Vector2d(0, -3)));
        assertTrue(new Vector2d(-3, -2).follows(new Vector2d(-4, -2)));
        assertTrue(new Vector2d(5, 5).follows(new Vector2d(5, 5)));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(3, 3), new Vector2d(1, 3).upperRight(new Vector2d(3, 1)));
        assertEquals(new Vector2d(3, 3), new Vector2d(3, 3).upperRight(new Vector2d(3, 1)));
        assertEquals(new Vector2d(-4, -1), new Vector2d(-4, -2).upperRight(new Vector2d(-5, -1)));
        assertEquals(new Vector2d(1, -1), new Vector2d(1, -1).upperRight(new Vector2d(1, -1)));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(3, 3), new Vector2d(5, 3).lowerLeft(new Vector2d(3, 5)));
        assertEquals(new Vector2d(3, 3), new Vector2d(3, 3).lowerLeft(new Vector2d(3, 5)));
        assertEquals(new Vector2d(-5, -2), new Vector2d(-4, -2).lowerLeft(new Vector2d(-5, -1)));
        assertEquals(new Vector2d(1, -1), new Vector2d(1, -1).lowerLeft(new Vector2d(1, -1)));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(3, 3), new Vector2d(1, 3).add(new Vector2d(2, 0)));
        assertEquals(new Vector2d(3, 3), new Vector2d(3, 3).add(new Vector2d(0, 0)));
        assertEquals(new Vector2d(-4, -1), new Vector2d(-4, 0).add(new Vector2d(0, -1)));
    }

    @Test
    void substract() {
        assertEquals(new Vector2d(3, 3), new Vector2d(1, 3).substract(new Vector2d(-2, 0)));
        assertEquals(new Vector2d(3, 3), new Vector2d(3, 3).substract(new Vector2d(0, 0)));
        assertEquals(new Vector2d(-4, -1), new Vector2d(-4, 0).substract(new Vector2d(0, 1)));
    }

    @Test
    void testEquals() {
        assertFalse(new Vector2d(1, 3).equals(new Vector2d(2, 3)));
        assertFalse(new Vector2d(1, 3).equals(new Object()));
        assertFalse(new Vector2d(1, 3).follows(new Vector2d(-22, 33)));
        assertTrue(new Vector2d(1, 3).follows(new Vector2d(1, 3)));
        assertTrue(new Vector2d(0, 0).follows(new Vector2d(0, 0)));
        Vector2d testVec = new Vector2d(9, 9);
        assertTrue(testVec.equals(testVec));
    }

    @Test
    void oposite() {
        assertEquals(new Vector2d(0,0), new Vector2d(0, 0).oposite());
        assertEquals(new Vector2d(1,1), new Vector2d(-1, -1).oposite());
        Vector2d testVec = new Vector2d(9, 9);
        assertEquals(testVec, testVec.oposite().oposite());
    }
}