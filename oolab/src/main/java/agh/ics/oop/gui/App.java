package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application implements IPositionChangeObserver{

    Thread engineThread;
    Stage appStage;
    AppGridCreator displayCreator;
    IWorldMap map;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // init appStage variable
        appStage = primaryStage;
        displayCreator = new AppGridCreator(makeScene(), this.map);
        appStage.show();
        engineThread.start();
    }

    @Override
    public void init(){
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        this.map = new GrassField(10);
        map.setAppObserver(this);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(0, 1), new Vector2d(4, 1)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions, 500);
        engineThread = new Thread(engine);
    }

    @Override
    public void positionChanged(Vector2d prevPosition, Vector2d nextPosition) {
        Vector2d[] newBounds = new Vector2d[]{new Vector2d(0, 0), new Vector2d(0, 0)};
        if (this.map instanceof GrassField) {
            newBounds = ((GrassField) this.map).mapBoundary.getDimentions();
        }
        if (this.map instanceof RectangularMap) {
            newBounds = ((RectangularMap) this.map).mapBoundary.getDimentions();
        }
        this.displayCreator.setNewBounds(newBounds[0], newBounds[1]);
        Platform.runLater(this.displayCreator);
    }

    private GridPane makeScene(){
        // Generateing scene. Runs only once and returns GridPane
        // The GridPane is then used to regenerate the scene without running this methode
        //initials

        /* --------------------- Structure ------------------- */
        // setting grid
        GridPane grid = new GridPane();

        // creating display container
        HBox display = new HBox();
        display.getChildren().add(grid);

        // creating info panel insides
        Button animStart = new Button("Start");
        TextField input = new TextField();

        // creating info panel
        HBox controls = new HBox();
        controls.getChildren().addAll(animStart, input);

        // setting main vbox
        VBox root = new VBox();
        root.getChildren().addAll(controls, display);

        // setting main scene
        Scene scene = new Scene(root, 400, 400);


        /* ----------------- Structure Properties --------------- */

        // grid Properties
        grid.setGridLinesVisible(true);
        grid.alignmentProperty().set(Pos.CENTER);

        // controls Properties
//        controls.minHeight(30);

        // info panel Properties
        input.minWidth(200);

        // root Properties
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        root.minWidth(400);
        root.minHeight(400);




        // finishing -------------
        appStage.setScene(scene);
        return grid;
    }
}
