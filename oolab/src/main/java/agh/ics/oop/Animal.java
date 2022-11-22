package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal implements IMapElement{
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;
    private ArrayList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map) {
        this.map = map;
        this.observers = new ArrayList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.observers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            default -> "X";
        };
    }

    public void addObserver(IPositionChangeObserver newObserver) {
        this.observers.add(newObserver);
    }

    public void removeObserver(IPositionChangeObserver observerToRemove) {
        this.observers.remove(observerToRemove);
    }

    private boolean positionChanged(Vector2d prev, Vector2d next) {
        // this is only needed if we place one animal on multiple arrays -> multidimentional stuf c:
        boolean[] returnedVals = new boolean[observers.size()];
        int rvIter = 0;
        // -------------

        for(IPositionChangeObserver observer : observers) {
            returnedVals[rvIter++] = observer.positionChanged(prev, next);
        }

        for(boolean rv : returnedVals) {
            if(!rv) { return false; }
        }
        return true;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
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
        if (!this.positionChanged(before_vec, this.position)) {
            this.position = before_vec;
        }
    }
}
