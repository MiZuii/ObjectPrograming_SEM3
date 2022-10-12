package agh.ics.oop;

import javax.lang.model.type.NullType;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        switch(this){
            case EAST: return "Wschod";
            case WEST: return "Zachod";
            case NORTH: return "Poludnie";
            case SOUTH: return "Plunoc";
        }
        return "";
    }

    public MapDirection next() {
        switch(this){
            case EAST: return SOUTH;
            case WEST: return NORTH;
            case SOUTH: return WEST;
            case NORTH: return EAST;
        }
    }

    public MapDirection previous() {
        switch(this){
            case WEST: return SOUTH;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            case NORTH: return WEST;
        }
    }

    public Vector2d toUnitVector() {
        switch(this){
            case NORTH: return new Vector2d(0, 1);
            case EAST: return new Vector2d(1, 0);
            case SOUTH: return new Vector2d(0, -1);
            case WEST: return new Vector2d(-1, 0);
        }
    }

}
