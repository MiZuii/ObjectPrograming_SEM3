package agh.ics.oop;

import java.util.HashMap;

abstract class AbstractWorldMap implements IWorldMap{
    protected HashMap<Vector2d, IMapElement> map;

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    @Override
    public boolean positionUpdate(Vector2d prev, Vector2d next) {
        if (!(this.isOccupied(prev))) { return false; } // there is no Object on key 'prev' -> invali function call or error in code
        if (!(this.canMoveTo(next))) { return false; } // if next is taken, don't move there

        IMapElement mapObject = this.map.get(prev);
        this.map.remove(prev);
        this.map.put(next, mapObject);
        return true;
    }

    protected abstract Vector2d[] toStringComponents();

    @Override
    public String toString() {
        MapVisualizer graphics = new MapVisualizer(this);
        Vector2d[] components = toStringComponents();
        return graphics.draw(components[0], components[1]);
    }
}
