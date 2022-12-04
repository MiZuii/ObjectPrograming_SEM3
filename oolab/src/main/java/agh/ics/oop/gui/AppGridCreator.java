package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class AppGridCreator implements Runnable{

    GridPane grid;
    IWorldMap map;
    private Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = new Vector2d(0, 0);
    static final int size = 20;

    public AppGridCreator(GridPane grid, IWorldMap map){
        this.grid = grid;
        this.map = map;
    }

    public void setNewBounds(Vector2d lL, Vector2d uR) {
        this.lowerLeft = lL;
        this.upperRight = uR;
    }

    @Override
    public void run() {
        GridPane grid = this.grid;

        // clearing gread
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        // making new grid
        for (int row = 0; row <= upperRight.y - lowerLeft.y + 1; row++) {
            for (int column = 0; column <= upperRight.x - lowerLeft.x + 1; column++) {
                if (row == upperRight.y - lowerLeft.y + 1 || column == 0) {
                    grid.add(gridAxElement(column, row, upperRight.y - lowerLeft.y + 1, lowerLeft.x - 1, lowerLeft.y - 1), column, row);
                } else if (this.map.isOccupied(new Vector2d(column + lowerLeft.x - 1, upperRight.y - row))) {
                    grid.add(gridMapElement(new Vector2d(column + lowerLeft.x - 1, upperRight.y - row)), column, row);
                }
                else {
                    grid.add(gridEmptyElement(), column, row);
                }
            }
        }
        for (int row = 0; row <= upperRight.y - lowerLeft.y + 1; row++) {
            grid.getRowConstraints().add(new RowConstraints(size));
        }
        for (int column = 0; column <= upperRight.x - lowerLeft.x + 1; column++) {
            grid.getColumnConstraints().add(new ColumnConstraints(size));
        }
    }

    private VBox gridAxElement(int xCord, int yCord, int yCorner, int xShift, int yShift){
        VBox nvbox = new VBox();
        Label ins;
        if (xCord == 0 && yCord == yCorner) ins = new Label("x/y");
        else if (xCord == 0) ins = new Label(String.valueOf(yCorner - yCord + yShift));
        else ins = new Label(String.valueOf(xCord + xShift));
        nvbox.setAlignment(Pos.CENTER);
        nvbox.getChildren().add(ins);
        nvbox.getStyleClass().add("grid-cell");
        ins.getStyleClass().add("grid-cell-insides");
        return nvbox;
    }

    private VBox gridMapElement(Vector2d position){
        VBox nvbox = new VBox();
        nvbox.setAlignment(Pos.CENTER);
        Label ins = new Label(((IMapElement) this.map.objectAt(position)).toString());
        nvbox.getChildren().add(ins);
        nvbox.getStyleClass().add("grid-cell");
        ins.getStyleClass().add("grid-cell-insides");
        return nvbox;
    }

    private VBox gridEmptyElement(){
        VBox nvbox = new VBox();
        nvbox.getStyleClass().add("grid-cell");
        return nvbox;
    }
}
