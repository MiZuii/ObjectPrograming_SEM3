package agh.ics.oop;

public class World {

    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.runWithSwing();
        }
        catch (IllegalArgumentException arg) {
            System.out.println(arg);
        }
    }
}
