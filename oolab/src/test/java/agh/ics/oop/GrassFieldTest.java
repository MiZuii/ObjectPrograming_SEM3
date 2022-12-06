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
    void positionChanged() {
        Vector2d animalPosition = animalPositions[0];
        Vector2d prevPosition;
        Vector2d startingPosition = animalPosition;

        // place the first animal on every grass position
        for(Vector2d position : grassPositions) {
            // make a position update
            prevPosition = animalPosition;
            this.map.positionChanged(animalPosition, position);
            animalPosition = position;

            // check if positions got updated
            assertTrue(this.map.objectAt(animalPosition) instanceof Animal);
            assertEquals(n, countGrass()); // checks if every grass got put back onto map
        }
        map.positionChanged(animalPosition, startingPosition); // returns the animal to its original position
        animalPosition = startingPosition;

        // place the first animal on every empty position
        // !! There can be grass fields on empty positions at this point
        for(Vector2d position : emptyPositions) {
            // make a position update
            prevPosition = animalPosition;
            this.map.positionChanged(animalPosition, position);
            animalPosition = position;

            // check if positions got updated
            assertNull(this.map.objectAt(prevPosition));
            assertTrue(this.map.objectAt(animalPosition) instanceof Animal);
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
}