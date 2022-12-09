package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    final App app;

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

        // this block is run when the engine thread has been terminated
        // soo a new thread needs to be generated and run
        if (app.engineThread.getState() == Thread.State.TERMINATED) {
            app.engineThread = app.generateNewThread(400, 8,
                    new Vector2d[]{new Vector2d(1, 1)});
            app.displayCreator.setNewMap(app.map);
            app.engineThread.start();

        // this block is run when application is executed for the first time
        // the thread is new and doesn't need to be generated
        } else if (app.engineThread.getState() == Thread.State.NEW) {
            app.engineThread.start();

        // this block is run when engine thread is running (or waiting between moves)
        // it pauses the engine thread
        } else if (app.engineThread.getState() == Thread.State.RUNNABLE || app.engineThread.getState() == Thread.State.TIMED_WAITING) {
            app.engineThread.running.set(false);

        // this block is run when engine thread was stopped while running
        // it unpauses the engine thread
        } else if (app.engineThread.getState() == Thread.State.WAITING) {
            synchronized (app.engineThread) {
                app.engineThread.notify();
            }
        }
    }
}
