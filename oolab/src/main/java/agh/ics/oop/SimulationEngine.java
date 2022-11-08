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
                case FORWARD -> animal_order.get(iter).move(MoveDirection.FORWARD);
                case BACKWARD -> animal_order.get(iter).move(MoveDirection.BACKWARD);
                case LEFT -> animal_order.get(iter).move(MoveDirection.LEFT);
                case RIGHT -> animal_order.get(iter).move(MoveDirection.RIGHT);
                default -> {}
            }
            iter += 1;
            iter = iter%animal_order.size();
        }
    }
}
