package agh.ics.oop;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positions;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;

        for (Vector2d position: positions) {
            map.place(new Animal(this.map, position));
        }
    }

    @Override
    public void run() {
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
