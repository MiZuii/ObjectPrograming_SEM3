package agh.ics.oop;

import java.util.Vector;

public class OptionsParser {

    public MoveDirection[] parse(String[] args){
        Vector<MoveDirection> ans = new Vector<MoveDirection>();
        int i=0;
        for (String s: args) {
            switch(s){
                case "f", "forward" -> ans.add(MoveDirection.FORWARD);
                case "b", "backward" -> ans.add(MoveDirection.BACKWARD);
                case "r", "right" -> ans.add(MoveDirection.RIGHT);
                case "l", "left" -> ans.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(s + " is not legal move specification");
            }
        }
        // Vector to array
        MoveDirection[] conv = new MoveDirection[ans.size()];
        for (MoveDirection dir : ans) {
            conv[i++] = dir;
        }
        return conv;
    }

}
