package agh.ics.oop;

import java.util.Vector;

public class World {

    static void run(MoveDirection[] arguments) {
        System.out.println("Start");
        for (MoveDirection arg : arguments) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
            }
        }
        System.out.println("Stop");
    }

    static MoveDirection[] str_to_Direction(String[] arg) {
        Vector<MoveDirection> ans = new Vector<MoveDirection>();
        int i=0;
        for (String s: arg) {
            switch(s){
                case "f" -> ans.add(MoveDirection.FORWARD);
                case "b" -> ans.add(MoveDirection.BACKWARD);
                case "r" -> ans.add(MoveDirection.RIGHT);
                case "l" -> ans.add(MoveDirection.LEFT);
                default -> {continue;}
            }
        }
        // Vector to array
        MoveDirection[] conv = new MoveDirection[ans.size()];
        for (MoveDirection dir : ans) {
            conv[i++] = dir;
        }
        return conv;
    }

    public static void main(String[] args) {

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        run(str_to_Direction(args));

    }

}
