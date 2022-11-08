package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private ArrayList<Animal> animal_order = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;

        for (Vector2d position: positions) {
            Animal new_anim = new Animal(this.map, position);
            if (map.place(new_anim)) {
                this.animal_order.add(new_anim);
            }
        }
    }

    @Override
    public void run() {
        int iter = 0;

        for (MoveDirection move: this.moves) {
            switch (move) {
                case FORWARD -> animal.move(MoveDirection.FORWARD);
                case BACKWARD -> animal.move(MoveDirection.BACKWARD);
                case LEFT -> animal.move(MoveDirection.LEFT);
                case RIGHT -> animal.move(MoveDirection.RIGHT);
                default -> {continue;}
            }
        }
    }
}
