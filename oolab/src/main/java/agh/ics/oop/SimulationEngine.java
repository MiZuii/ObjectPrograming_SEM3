package agh.ics.oop;

import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;

import java.lang.Thread;

public class SimulationEngine implements IEngine, Runnable{

    private MoveDirection[] moves;
    private IWorldMap map;
    private Animal[] animalOrder;
    public int moveDelay;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, int moveDelay){
        this.moves = moves;
        this.map = map;
        animalOrder = new Animal[positions.length];
        this.moveDelay = moveDelay;

        for (int i=0; i< positions.length; i++) {
            Animal new_anim = new Animal(this.map, positions[i]);
            if (map.place(new_anim)) {
                animalOrder[i] = new_anim;
            }
            else {
                throw new IllegalArgumentException(positions[i] + " is not legal place for animal");
            }
        }
    }

    @Override
    public void run() {
        for (int iter=0; iter<moves.length; iter++) {
            try {
                Thread.sleep(moveDelay);
            }
            catch (Exception e) {
                // catching the exception
                System.out.println(e);
            }

            switch (moves[iter]) {
                case FORWARD -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.FORWARD);
                case BACKWARD -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.BACKWARD);
                case LEFT -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.LEFT);
                case RIGHT -> this.animalOrder[iter%animalOrder.length].move(MoveDirection.RIGHT);
                default -> {}
            }
        }
    }
}
