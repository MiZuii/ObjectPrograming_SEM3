package agh.ics.oop.gui;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Disassembler implements EventHandler<WindowEvent> {

    private final App app;

    public Disassembler(App app) {
        this.app = app;
    }

    @Override
    public void handle(WindowEvent event) {
        // Interrupts engineThread after shutting down the window
        this.app.engineThread.interrupt();
    }
}
