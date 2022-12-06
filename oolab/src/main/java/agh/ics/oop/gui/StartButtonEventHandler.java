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
    public synchronized void handle(ActionEvent event) {
        // get new arguments if any
        String[] newArguments = app.textInput.getText().split(" ");
        if (!newArguments[0].equals("")) {
            app.arguments = newArguments;
        }

        if (app.engineThread.getState() == Thread.State.TERMINATED) {
            app.engineThread = app.generateNewThread(400, 8,
                    new Vector2d[]{new Vector2d(1, 1)});
            app.displayCreator.setNewMap(app.map);
            app.engineThread.start();
        } else if (app.engineThread.getState() == Thread.State.NEW) {
            app.engineThread.start();
        } else if (app.engineThread.getState() == Thread.State.RUNNABLE || app.engineThread.getState() == Thread.State.TIMED_WAITING){
            app.engineThread.running.set(false);
        } else if (app.engineThread.getState() == Thread.State.WAITING) {
            app.engineThread.notify();
        }
    }
}
