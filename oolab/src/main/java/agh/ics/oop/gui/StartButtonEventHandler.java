package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    App app;

    public StartButtonEventHandler(App app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        if (app.engineThread.isAlive()) {
            app.engineThread.stop();
        }
        app.engineThread.start();
    }
}
