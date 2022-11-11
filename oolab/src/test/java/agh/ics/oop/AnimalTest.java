package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    // tested animals
    Animal animal1;
    Animal animal2;
    Animal animal3;
    Animal animal4;
    Animal animal5;
    Animal animal6;
    Animal animal7;
    IWorldMap map;

    @BeforeEach
    void setUp(){
        map = new RectangularMap(5, 5);

        // tested animals setup
        animal1 = new Animal(map, new Vector2d(2, 2));
        animal2 = new Animal(map, new Vector2d(0, 0));
        animal2.move(MoveDirection.RIGHT);
        animal3 = new Animal(map, new Vector2d(1, 4));
        animal3.move(MoveDirection.LEFT);
        animal4 = new Animal(map, new Vector2d(0, 3));
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.RIGHT);
        animal5 = new Animal(map, new Vector2d(1, 0));
        animal5.move(MoveDirection.LEFT);
        animal5.move(MoveDirection.LEFT);
        animal6 = new Animal(map, new Vector2d(4, 3));
        animal7 = new Animal(map, new Vector2d(4, 4));
    }

    @Test
    void testToString() {
        assertEquals("N", animal1.toString());
        assertEquals("E", animal2.toString());
        assertEquals("W", animal3.toString());
        assertEquals("S", animal4.toString());
        assertEquals("S", animal5.toString());
        assertEquals("N", animal6.toString());
        assertEquals("N", animal7.toString());
    }

    @Test
    void isAt() {
        assertTrue(animal1.isAt(new Vector2d(2, 2)));
        assertTrue(animal3.isAt(new Vector2d(1, 4)));
        assertTrue(animal5.isAt(new Vector2d(1, 0)));
        assertTrue(animal7.isAt(new Vector2d(4, 4)));
        assertFalse(animal2.isAt(new Vector2d(-1, 0)));
        assertFalse(animal4.isAt(new Vector2d(2, 3)));
        assertFalse(animal6.isAt(new Vector2d(0, 0)));
    }

    @Test
    void getPosition() {
        assertEquals(new Vector2d(2, 2), animal1.getPosition());
        assertEquals(new Vector2d(0, 0), animal2.getPosition());
        assertEquals(new Vector2d(1, 4), animal3.getPosition());
        assertEquals(new Vector2d(0, 3), animal4.getPosition());
        assertEquals(new Vector2d(1, 0), animal5.getPosition());
        assertEquals(new Vector2d(4, 3), animal6.getPosition());
        assertEquals(new Vector2d(4, 4), animal7.getPosition());
    }

    @Test
    void move() {
        // setup
        map.place(animal1);
        MoveDirection[] moves = {MoveDirection.FORWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.LEFT,
                                MoveDirection.FORWARD,
                                MoveDirection.BACKWARD,
                                MoveDirection.LEFT,
                                MoveDirection.BACKWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.FORWARD,
                                MoveDirection.FORWARD};
        Vector2d[] moveVectors = {new Vector2d(2, 3),
                                new Vector2d(2, 4),
                                new Vector2d(2, 4),
                                new Vector2d(2, 4),
                                new Vector2d(1, 4),
                                new Vector2d(2, 4),
                                new Vector2d(2, 4),
                                new Vector2d(2, 4),
                                new Vector2d(2, 3),
                                new Vector2d(2, 2),
                                new Vector2d(2, 1),
                                new Vector2d(2, 0),
                                new Vector2d(2, 0)};

        // tests
        for (int i=0; i< moves.length; i++){
            animal1.move(moves[i]);
            assertNotNull(map.objectAt(animal1.getPosition()));
            assertEquals(moveVectors[i], animal1.getPosition());
        }
    }
}