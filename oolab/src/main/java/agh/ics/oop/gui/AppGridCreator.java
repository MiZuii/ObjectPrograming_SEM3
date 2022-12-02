package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class AppGridCreator implements Runnable{

    GridPane grid;
    IWorldMap map;
    private Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = new Vector2d(0, 0);

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

        // making new grid
        for (int row = 0; row <= upperRight.y- lowerLeft.y; row++) {
            for (int column = 0; column <= upperRight.x - lowerLeft.x; column++) {
                if (this.map.isOccupied(new Vector2d(column + lowerLeft.x, row + lowerLeft.y))) {
                    grid.add(new Label(((IMapElement) this.map.objectAt(new Vector2d(column + lowerLeft.x, row + lowerLeft.y))).toString()), column, row);
                }
            }
        }
        for (int row = 0; row <= upperRight.y - lowerLeft.y; row++) {
            grid.getColumnConstraints().add(new ColumnConstraints(40));
        }
        for (int column = 0; column <= lowerLeft.x - upperRight.x; column++) {
            grid.getRowConstraints().add(new RowConstraints(40));
        }
        grid.setGridLinesVisible(true);
    }
}
