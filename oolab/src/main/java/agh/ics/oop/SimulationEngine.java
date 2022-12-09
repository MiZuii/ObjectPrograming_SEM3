package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationEngine extends Thread implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private Animal[] animalOrder;
    private int moveDelay;
    public volatile AtomicBoolean running = new AtomicBoolean(false);
    private App app;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, int moveDelay, App app){
        this.moves = moves;
        this.map = map;
        animalOrder = new Animal[positions.length];
        this.moveDelay = moveDelay;
        this.running.set(false);
        this.app = app;

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

    private synchronized void pause(){
        if (!this.running.get()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            running.set(true);
        }
    }

    @Override
    public void run() {
        running.set(true); // seting running to true just to make sure the thread doesn't stop imidietly
        for (int iter = 0; iter < moves.length; iter++) {
            try {
                this.pause(); // checks if GUI button send a pause request -> if tes the thread is paused and waits for notify
            }
            catch (RuntimeException e){
                this.interrupt();
            }

            switch (moves[iter]) {
                case FORWARD -> this.animalOrder[iter % animalOrder.length].move(MoveDirection.FORWARD);
                case BACKWARD -> this.animalOrder[iter % animalOrder.length].move(MoveDirection.BACKWARD);
                case LEFT -> this.animalOrder[iter % animalOrder.length].move(MoveDirection.LEFT);
                case RIGHT -> this.animalOrder[iter % animalOrder.length].move(MoveDirection.RIGHT);
                default -> {
                }
            }

            try {
                sleep(moveDelay);
            } catch (Exception e) {
                // catching the exception
                this.interrupt();
            }
        }
    }
}
