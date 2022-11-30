package agh.ics.oop;

import agh.ics.oop.gui.*;
import javafx.application.Application;

public class World {
//f b r l f f r r f f f f f f f f
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        }
        catch (IllegalArgumentException arg) {
            System.out.println(arg);
        }
    }
}
