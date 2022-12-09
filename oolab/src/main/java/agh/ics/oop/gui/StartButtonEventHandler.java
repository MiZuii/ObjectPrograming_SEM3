package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    final App app;

    public StartButtonEventHandler(App app){
        this.app = app;
    }

    public void waitSync() throws InterruptedException {
        synchronized (app.engineThread) {
            app.engineThread.wait();
        }
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
        } else if (app.engineThread.getState() == Thread.State.RUNNABLE || app.engineThread.getState() == Thread.State.TIMED_WAITING) {
            System.out.println(Thread.currentThread().getName());
            app.engineThread.running.set(false);
        } else if (app.engineThread.getState() == Thread.State.WAITING) {
            synchronized (app.engineThread) {
                app.engineThread.notify();
            }
        }
    }
}
