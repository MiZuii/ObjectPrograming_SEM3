package agh.ics.oop;
import java.util.Vector;

public class World {

    static void run_animal(Animal animal, MoveDirection[] arguments) {
        for (MoveDirection arg : arguments) {
            switch (arg) {
                case FORWARD -> animal.move(MoveDirection.FORWARD);
                case BACKWARD -> animal.move(MoveDirection.BACKWARD);
                case LEFT -> animal.move(MoveDirection.LEFT);
                case RIGHT -> animal.move(MoveDirection.RIGHT);
                default -> {continue;}
            }
        }
    }

    public static void main(String[] args) {
        Animal cat = new Animal();
        run_animal(cat, new OptionsParser().parse(args));
        System.out.println(cat.toString());
    }

}
