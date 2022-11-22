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

        // special positions that are not in <0, mapLength> should also be possible to be moved to
        assertTrue(map.canMoveTo(new Vector2d(-3, 2)));
        assertTrue(map.canMoveTo(new Vector2d(-3, -34)));
        assertTrue(map.canMoveTo(new Vector2d(0, -7)));
        assertTrue(map.canMoveTo(new Vector2d(mapLength+23, 2)));
        assertTrue(map.canMoveTo(new Vector2d(4, mapLength+34)));
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

        // place an animal on every empty position
        for(Vector2d position : emptyPositions) {
            Animal tmp = new Animal(map, position);
            assertTrue(map.place(tmp));
            assertEquals(tmp, map.objectAt(position));
        }

        // place an animal on every grass position
        for(Vector2d position : grassPositions) {
            Animal tmp = new Animal(map, position);
            assertTrue(map.place(tmp));
            assertEquals(tmp, map.objectAt(position));
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

    private int countGrass() {
        // counts all gras on the current map
        int counter = 0;

        for(int row=0; row<mapLength; row++) {
            for (int column=0; column<mapLength; column++) {
                if(map.objectAt(new Vector2d(column, row)) instanceof Grass) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Test
    void toStringComponents() {
        // setup custom map
        GrassField customMap = new GrassField(1);
        mapLength = (int)Math.sqrt(10);
        Vector2d initialGrassPosition = new Vector2d(0, 0);

        // find initial placement of the grass
        for(int row=0; row<mapLength; row++) {
            for (int column=0; column<mapLength; column++) {
                if(customMap.isOccupied(new Vector2d(column, row))) {
                    initialGrassPosition = new Vector2d(column, row);
                    break;
                }
            }
        }
        // initial test
        assertArrayEquals(new Vector2d[]{initialGrassPosition, initialGrassPosition}, customMap.toStringComponents());

        // create parameters for test -> both arrays must have the same size
        Vector2d upperRight = initialGrassPosition;
        Vector2d lowerLeft = initialGrassPosition;
        Vector2d[] upperRightBound = new Vector2d[]{
                new Vector2d(1, 1).add(initialGrassPosition),
                new Vector2d(1, 3).add(initialGrassPosition),
                new Vector2d(5, 1).add(initialGrassPosition),
                new Vector2d(7, 2).add(initialGrassPosition),
                new Vector2d(8, 8).add(initialGrassPosition),
                new Vector2d(8, 9).add(initialGrassPosition),
                new Vector2d(13, 6).add(initialGrassPosition),
                new Vector2d(9, 15).add(initialGrassPosition),
        };
        Vector2d[] lowerLeftBound = new Vector2d[]{
                new Vector2d(0, 0).add(initialGrassPosition),
                new Vector2d(-2, 0).add(initialGrassPosition),
                new Vector2d(0, -3).add(initialGrassPosition),
                new Vector2d(2, -5).add(initialGrassPosition),
                new Vector2d(-7, -7).add(initialGrassPosition),
                new Vector2d(-4, -8).add(initialGrassPosition),
                new Vector2d(-13, 0).add(initialGrassPosition),
                new Vector2d(6, -15).add(initialGrassPosition),
        };

        for(int i=0; i<upperRightBound.length; i++) {
            // update grass positions
            if(!(customMap.isOccupied(upperRightBound[i]))) {
                customMap.place(new Grass(upperRightBound[i]));
            }
            if(!(customMap.isOccupied(lowerLeftBound[i]))) {
                customMap.place(new Grass(lowerLeftBound[i]));
            }
            upperRight = upperRight.upperRight(upperRightBound[i]);
            lowerLeft = lowerLeft.lowerLeft(lowerLeftBound[i]);

            assertArrayEquals(new Vector2d[]{lowerLeft, upperRight}, customMap.toStringComponents());
        }
    }
}