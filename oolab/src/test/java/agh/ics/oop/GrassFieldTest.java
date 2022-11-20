package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    GrassField map;
    int mapLength;
    int n;
    Vector2d[] grassPositions;
    Vector2d[] animalPositions;
    Vector2d[] emptyPositions;
    Random randomizer;


    @BeforeEach
    void setUp() {
        randomizer = new Random();
        n = randomizer.nextInt(20)+10;
        map = new GrassField(n);
        mapLength = (int)Math.sqrt(10*n);

        // placeing animals
        map.place(new Animal(map, new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(0, mapLength/2)));
        map.place(new Animal(map, new Vector2d(mapLength/3, 0)));
        map.place(new Animal(map, new Vector2d(mapLength-5, mapLength-7)));
        animalPositions = new Vector2d[]{
                new Vector2d(0,0),
                new Vector2d(0, mapLength/2),
                new Vector2d(mapLength/3, 0),
                new Vector2d(mapLength-5, mapLength-7)
        };

        // creating array of grass placement
        grassPositions = new Vector2d[n];
        emptyPositions = new Vector2d[mapLength*mapLength-n-4];
        int grassIter = 0;
        int emptyIter = 0;
        for(int row=0; row<mapLength; row++) {
            for (int column=0; column<mapLength; column++) {
                Vector2d rcVector = new Vector2d(row, column);
                if(map.objectAt(rcVector) instanceof Grass) {
                    grassPositions[grassIter++] = rcVector;
                }
                else if(map.objectAt(rcVector) == null) {
                    emptyPositions[emptyIter++] = rcVector;
                }
            }
        }
    }

    @Test
    void canMoveTo() {
        // all animal places are unable to be moved to
        for(Vector2d position : animalPositions) {
            assertFalse(map.canMoveTo(position));
        }

        // all grass places should be possible to be moved to
        for(Vector2d position : grassPositions) {
            assertTrue(map.canMoveTo(position));
        }

        // all empty places should be possible to be moved to
        for(Vector2d position : emptyPositions) {
            assertTrue(map.canMoveTo(position));
        }
    }

    @Test
    void objectAt() {
        // check all animal places
        for(Vector2d position : animalPositions) {
            assertTrue(map.objectAt(position) instanceof Animal);
        }

        // check all grass places
        for(Vector2d position : grassPositions) {
            assertTrue(map.objectAt(position) instanceof Grass);
        }

        // check all empty places
        for(Vector2d position : emptyPositions) {
            assertNull(map.objectAt(position));
        }
    }

    @Test
    void place() {
        // check if animals can't be placed on other animals
        for(Vector2d position : animalPositions) {
            assertFalse(map.place(new Animal(map, position)));
        }
    }

    @Test
    void isOccupied() {
        // check all animal places
        for(Vector2d position : animalPositions) {
            assertTrue(map.isOccupied(position));
        }

        // check all grass places
        for(Vector2d position : grassPositions) {
            assertTrue(map.isOccupied(position));
        }

        // check all empty places
        for(Vector2d position : emptyPositions) {
            assertFalse(map.isOccupied(position));
        }
    }

    @Test
    void positionUpdate() {

    }

    @Test
    void toStringComponents() {
    }

    @Test
    void relocate() {
    }
}