package agh.ics.oop;

import java.util.Vector;

public class World {

    static void run(Direction[] arguments) {
        System.out.println("Start");
        for (Direction arg : arguments) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
            }
        }
        System.out.println("Stop");
    }

    static Direction[] directions_to_str(String[] arg) {
        Vector<Direction> ans = new Vector<Direction>();
        int i=0;
        for (String s: arg) {
            switch(s){
                case "f" -> ans.add(Direction.FORWARD);
                case "b" -> ans.add(Direction.BACKWARD);
                case "r" -> ans.add(Direction.RIGHT);
                case "l" -> ans.add(Direction.LEFT);
                default -> {continue;}
            }
        }
        // Vector to array
        Direction[] conv = new Direction[ans.size()];
        for (Direction dir : ans) {
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

    }

}
