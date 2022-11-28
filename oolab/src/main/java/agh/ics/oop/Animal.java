package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal implements IMapElement, IPositionChangeObserverHolder{
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;
    private IPositionChangeObserver observer;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
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

    @Override
    public void addObserver(IPositionChangeObserver newObserver) {
        this.observer = newObserver;
    }

    @Override
    public void removeObserver() {
        this.observer = null;
    }

    private boolean positionChanged(Vector2d prev, Vector2d next) {
        if(observer == null) {return false;}
        return observer.positionChanged(prev, next);
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
