package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void run_animal() {
        Animal cat = new Animal();
        assertEquals("{polozenie: (2,2), orientacja: Poludnie}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.BACKWARD});
        assertEquals("{polozenie: (1,2), orientacja: Wschod}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT});
        assertEquals("{polozenie: (1,4), orientacja: Polnoc}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT});
        assertEquals("{polozenie: (1,0), orientacja: Zachod}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD});
        assertEquals("{polozenie: (0,0), orientacja: Zachod}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD});
        assertEquals("{polozenie: (4,0), orientacja: Zachod}", cat.toString());
        World.run_animal(cat, new MoveDirection[]{MoveDirection.RIGHT ,MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});
        assertEquals("{polozenie: (4,4), orientacja: Poludnie}", cat.toString());
    }
}