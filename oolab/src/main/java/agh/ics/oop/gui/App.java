package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application implements IPositionChangeObserver {

    SimulationEngine engineThread;
    Stage appStage;
    public AppGridCreator displayCreator;
    IWorldMap map;
    String[] arguments;
    EventHandler<ActionEvent> startButtonH;
    TextField textInput;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // init appStage variable
        appStage = primaryStage;
        displayCreator = new AppGridCreator(makeScene());
        displayCreator.setNewMap(this.map);
        positionChanged(new Vector2d(0, 0), new Vector2d(0, 0)); // runs only to generate initial grid
        appStage.show();
    }

    @Override
    public void init(){
        startButtonH = new StartButtonEventHandler(this);
        arguments = getParameters().getRaw().toArray(String[]::new);
        engineThread = generateNewThread(400, 8,
                new Vector2d[]{new Vector2d(1, 1)});
    }

    public SimulationEngine generateNewThread(int moveDelay, int mapSize, Vector2d[] animalsPositions){
        MoveDirection[] directions = new OptionsParser().parse(arguments);
        this.map = new GrassField(mapSize);
        this.map.setAppObserver(this);
        return new SimulationEngine(directions, map, animalsPositions, moveDelay);
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

        /* --------------------- Structure ------------------- */
        // setting grid
        GridPane grid = new GridPane();

        // create display wraper
        VBox displayWraper = new VBox();
        displayWraper.getChildren().add(grid);

        // creating display container
        HBox display = new HBox();
        display.getChildren().add(displayWraper);

        // creating info panel insides
        Button animStart = new Button("Start");
        TextField input = new TextField();
        textInput = input;

        // creating info panel
        HBox controls = new HBox();
        controls.getChildren().addAll(animStart, input);

        // setting main vbox
        VBox root = new VBox();
        root.getChildren().addAll(controls, display);

        // setting main scene
        Scene scene = new Scene(root, 1000, 600);
        String path = this.getClass().getResource("/Styles/GuiStyles.css").toExternalForm();
        scene.getStylesheets().add(path);


        /* ----------------- Structure Properties --------------- */

        // controls Properties
        controls.getStyleClass().add("hbox");
        input.getStyleClass().add("text-field");
        HBox.setHgrow(input, Priority.SOMETIMES);
        HBox.setHgrow(animStart, Priority.SOMETIMES);

        // display Properties
        display.getStyleClass().add("hbox");
        display.alignmentProperty().set(Pos.CENTER);

        // displayWraper Properties
        displayWraper.getStyleClass().add("vbox");
        displayWraper.alignmentProperty().set(Pos.CENTER);

        // grid Properties
        grid.setGridLinesVisible(true);
        grid.getStyleClass().add("grid");

        // root Properties
        root.alignmentProperty().set(Pos.TOP_LEFT);
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        root.getStyleClass().add("vbox");
        VBox.setVgrow(display, Priority.ALWAYS);

        /* ------------- functionalities --------------- */
        animStart.setOnAction(startButtonH);

        // finishing -------------
        appStage.setScene(scene);
        return grid;
    }
}
