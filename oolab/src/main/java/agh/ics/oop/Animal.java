package agh.ics.oop;

import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IPositionChangeObserverHolder;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Animal implements IMapElement, IPositionChangeObserverHolder {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map) {
        this.map = map;
        observers = new ArrayList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        observers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Z " + this.position.toString();
    }

    @Override
    public void addObserver(IPositionChangeObserver newObserver) {
        this.observers.add(newObserver);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observerToRemove) {
        this.observers.remove(observerToRemove);
    }

    private void positionChanged(Vector2d prev, Vector2d next) {
        for(IPositionChangeObserver observer : observers) {
            observer.positionChanged(prev, next);
        }
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public Image getImage(){
        String path = "";
        switch (this.orientation) {
            case NORTH -> path = "/Images/up.png";
            case SOUTH -> path = "/Images/down.png";
            case EAST -> path = "/Images/right.png";
            case WEST -> path = "/Images/left.png";
            case NO_DIRECTION -> path = "";
        }

        Image image = new Image(getClass().getResourceAsStream(path));
        return image;
    }

    public void move(MoveDirection direction){
        Vector2d before_vec = this.position;

        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector());
            }
            case BACKWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector().oposite());
            }
        }
        if (!map.canMoveTo(this.position)) {
            this.position = before_vec;
        }
        this.positionChanged(before_vec, this.position);
    }
}
