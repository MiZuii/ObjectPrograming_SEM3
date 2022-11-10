package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private ArrayList<Animal> animal_order = new ArrayList<Animal>();

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
            if (animal_order.isEmpty()) {
                return;
            }
            switch (move) {
                case FORWARD -> {Animal tmp = animal_order.get(iter);
                                    tmp.move(MoveDirection.FORWARD);}
                case BACKWARD -> {Animal tmp = animal_order.get(iter);
                                    tmp.move(MoveDirection.BACKWARD);}
                case LEFT -> {Animal tmp = animal_order.get(iter);
                                    tmp.move(MoveDirection.LEFT);}
                case RIGHT -> {Animal tmp = animal_order.get(iter);
                                    tmp.move(MoveDirection.RIGHT);}
                default -> {}
            }
            iter += 1;
            iter = iter%animal_order.size();
            System.out.print(this.map.toString());
        }
    }
}
