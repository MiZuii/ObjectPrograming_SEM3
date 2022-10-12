package agh.ics.oop;

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
        Direction[] ans = new Direction[arg.length];
        int i=0;
        for (String s: arg) {
            switch(s){
                case "f" -> ans[i++] = Direction.FORWARD;
                case "b" -> ans[i++] = Direction.BACKWARD;
                case "r" -> ans[i++] = Direction.RIGHT;
                case "l" -> ans[i++] = Direction.LEFT;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("system wystartowal");
//        String[] tmp_array = new String[4];
//        tmp_array[0] = "f";
//        tmp_array[1] = "b";
//        tmp_array[2] = "r";
//        tmp_array[3] = "l";
        run(directions_to_str(args));
        System.out.println("system zakonczyl dzialanie");
    }

}
