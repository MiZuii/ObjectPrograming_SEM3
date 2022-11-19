package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    IWorldMap map;
    int map_width;
    int map_height;
    boolean[] animal_places;
    Vector2d[] emptyPositions;
    Vector2d[] fullPositions;

    @BeforeEach
    void setUp() {
        map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(3, 2)));
        map.place(new Animal(map, new Vector2d(1, 4)));
        map.place(new Animal(map, new Vector2d(1, 0)));
        map.place(new Animal(map, new Vector2d(4, 3)));
        map.place(new Animal(map, new Vector2d(2, 1)));
        map_height = 5;
        map_width = 5;

        fullPositions = new Vector2d[]{new Vector2d(0, 0),
            new Vector2d(3, 2), new Vector2d(1, 4),
            new Vector2d(1, 0), new Vector2d(4, 3),
            new Vector2d(2, 1)};

        emptyPositions = new Vector2d[]{new Vector2d(2, 0),
            new Vector2d(3, 0), new Vector2d(4, 0),
            new Vector2d(0, 1), new Vector2d(1, 1),
            new Vector2d(3, 1), new Vector2d(4, 1),
            new Vector2d(0, 2), new Vector2d(1, 2),
            new Vector2d(2, 2), new Vector2d(4, 2),
            new Vector2d(0, 3), new Vector2d(1, 3),
            new Vector2d(2, 3), new Vector2d(3, 3),
            new Vector2d(0, 4), new Vector2d(2, 4),
            new Vector2d(3, 4), new Vector2d(4, 4)};

        animal_places = new boolean[]{true, true, false, false, false,
                false, false, true, false, false,
                false, false, false, true, false,
                false, false, false, false, true,
                false, true, false, false, false};
    }

    @Test
    void canMoveTo() {
        for (int i=0; i<map_height*map_width; i++) {
            assertEquals(!animal_places[i], map.canMoveTo(new Vector2d(i%map_width, i/map_height)));
        }
    }

    @Test
    void objectAt() {
        for (int i=0; i<map_height*map_width; i++) {
            if (animal_places[i]) {
                assertNotNull(map.objectAt(new Vector2d(i%map_width, i/map_height)));
            }
            else {
                assertNull(map.objectAt(new Vector2d(i%map_width, i/map_height)));
            }
        }
    }

    @Test
    void place() {
        for (int i=0; i<map_height*map_width; i++) {
            Animal tmp = new Animal(map, new Vector2d(i % map_width, i / map_height));
            assertEquals(!animal_places[i], map.place(tmp));
            if (!animal_places[i]) {
                assertEquals(map.objectAt(new Vector2d(i % map_width, i / map_height)), tmp);
            }
        }
    }

    @Test
    void isOccupied() {
        for (int i=0; i<map_height*map_width; i++) {
            assertEquals(animal_places[i], map.isOccupied(new Vector2d(i%map_width, i/map_height)));
        }
    }

    @Test
    void positionUpdate() {
        for(Vector2d position : emptyPositions) {
            assertFalse(this.map.positionUpdate(position, new Vector2d(-1, -1)));
        }
        for(int i=0; i<fullPositions.length -1; i++) {
            assertFalse(this.map.positionUpdate(fullPositions[i], fullPositions[i+1]));
        }

        // check moves above borders
        assertFalse(this.map.positionUpdate(fullPositions[0], new Vector2d(-1, 2)));
        assertFalse(this.map.positionUpdate(fullPositions[0], new Vector2d(-1, -3)));
        assertFalse(this.map.positionUpdate(fullPositions[0], new Vector2d(5, 2)));
        assertFalse(this.map.positionUpdate(fullPositions[0], new Vector2d(0, 7)));

        // sequence of possible moves
        assertTrue(this.map.positionUpdate(new Vector2d(0, 0), new Vector2d(1, 2)));
        assertTrue(this.map.positionUpdate(new Vector2d(3, 2), new Vector2d(0, 0)));
        assertFalse(this.map.positionUpdate(new Vector2d(0, 0), new Vector2d(1, 2)));
        assertTrue(this.map.positionUpdate(new Vector2d(1, 4), new Vector2d(3, 2)));
        assertTrue(this.map.positionUpdate(new Vector2d(1, 0), new Vector2d(1, 4)));
        assertTrue(this.map.positionUpdate(new Vector2d(1, 4), new Vector2d(4, 0)));
        assertTrue(this.map.positionUpdate(new Vector2d(3, 2), new Vector2d(1, 4)));
        assertFalse(this.map.positionUpdate(new Vector2d(2, 1), new Vector2d(4, 0)));
        assertFalse(this.map.positionUpdate(new Vector2d(2, 1), new Vector2d(1, 4)));
    }

    @Test
    void toStringComponents() {

    }
}