package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    GrassField map;
    int mapLength;
    int n;
    Vector2d[] grassPlaces;
    Vector2d[] animalPlaces;

    boolean[] elementPlaces;

    @BeforeEach
    void setUp() {
        Random randomizer = new Random();
        n = randomizer.nextInt(20)+10;
        map = new GrassField(n);
        mapLength = (int)Math.sqrt(10*n);
        elementPlaces = new boolean[4+n];

        // placeing animals
        map.place(new Animal(map, new Vector2d(0, 0)));
        elementPlaces[0] = true;
        map.place(new Animal(map, new Vector2d(0, mapLength/2)));
        elementPlaces[mapLength*n/2] = true;
        map.place(new Animal(map, new Vector2d(mapLength/3, 0)));
        elementPlaces[mapLength/3] = true;
        map.place(new Animal(map, new Vector2d(mapLength-5, mapLength-7)));
        elementPlaces[(mapLength-7)*n + mapLength - 5] = true;
        animalPlaces = new Vector2d[]{
                new Vector2d(0,0),
                new Vector2d(0, mapLength/2),
                new Vector2d(mapLength/3, 0),
                new Vector2d(mapLength-5, mapLength-7)
        };

        // creating array of grass placement
        grassPlaces = new Vector2d[n];
        int grassIter = 0;
        for(int row=0; row<mapLength; row++) {
            for (int column=0; column<mapLength; column++) {
                if(map.objectAt(new Vector2d(row, column)) instanceof Grass) {
                    grassPlaces[grassIter++] = new Vector2d(row, column);
                    elementPlaces[row*n+column] = true;
                }
            }
        }
    }

    @Test
    void canMoveTo() {
    }

    @Test
    void objectAt() {
    }

    @Test
    void place() {
    }

    @Test
    void isOccupied() {
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