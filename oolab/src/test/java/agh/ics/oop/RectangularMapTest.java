package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    RectangularMap map;
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
    void isOccupied() {
        for (int i=0; i<map_height*map_width; i++) {
            assertEquals(animal_places[i], map.isOccupied(new Vector2d(i%map_width, i/map_height)));
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
    void toStringComponents() {
        // initial test
        assertArrayEquals(new Vector2d[]{new Vector2d(0, 0), new Vector2d(4, 4)}, this.map.toStringComponents());

        // test preparation
        Vector2d[] mapVectorSize = new Vector2d[]{new Vector2d(1, 1), new Vector2d(2, 3), new Vector2d(4, 8), new Vector2d(27, 2)};
        int[][] mapSize = new int[][]{{2, 2}, {3, 4}, {5, 9}, {28, 3}};

        // tests
        for(int i=0; i<mapVectorSize.length; i++) {
            RectangularMap tmp = new RectangularMap(mapSize[i][0], mapSize[i][1]);
            assertArrayEquals(new Vector2d[]{new Vector2d(0, 0), mapVectorSize[i]}, tmp.toStringComponents());
        }
    }

    @Test
    void positionChanged() {

    }

    @Test
    void canMoveTo() {
        for (int i=0; i<map_height*map_width; i++) {
            assertEquals(!animal_places[i], map.canMoveTo(new Vector2d(i%map_width, i/map_height)));
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
}