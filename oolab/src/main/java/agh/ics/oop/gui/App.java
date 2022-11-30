package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class App extends Application implements IPositionChangeObserver{

    Thread engineThread;
    Stage appStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // init appStage variable
        appStage = primaryStage;

        Label label1 = new Label("X");
        Label label2 = new Label("X");
        Label label3 = new Label("X");
        Label label4 = new Label("X");

        // setting grid
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.add(label1, 0, 0, 1, 1);
        grid.add(label2, 1, 0, 1, 1);
        grid.add(label3, 0, 1, 1, 1);
        grid.add(label4, 1, 1, 1, 1);

        Scene scene = new Scene(grid, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
        engineThread.start();
    }

    @Override
    public void init(){
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        IWorldMap map = new GrassField(10);
        map.setAppObserver(this);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(0, 1), new Vector2d(4, 1)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions, 300);
        engineThread = new Thread(engine);
    }

    @Override
    public void positionChanged(Vector2d prevPosition, Vector2d nextPosition) {
        Platform.runLater(generateNewScene());
    }

    public Runnable generateNewScene(){
        return new Runnable(){
            @Override public void run() {
                // Generateing new scene
                Label labelx = new Label("X");
                Scene scene = new Scene(labelx, 400, 400);
                appStage.setScene(scene);

                appStage.show();
                // ---------------------
            }
        };
    }
}
