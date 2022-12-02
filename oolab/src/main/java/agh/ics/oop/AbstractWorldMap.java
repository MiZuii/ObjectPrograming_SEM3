package agh.ics.oop;

import java.util.HashMap;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected HashMap<Vector2d, IMapElement> map;
    protected IPositionChangeObserver appObserver;
    public MapBoundary mapBoundary;

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    @Override
    public void positionChanged(Vector2d prev, Vector2d next) {
        IMapElement mapObject = (IMapElement)this.objectAt(prev);
        this.map.remove(prev);
        this.map.put(next, mapObject);
    }

    @Override
    public String toString() {
        MapVisualizer graphics = new MapVisualizer(this);
        Vector2d[] components = mapBoundary.getDimentions();
        return graphics.draw(components[0], components[1]);
    }

    @Override
    public void setAppObserver(IPositionChangeObserver appObserver) {
        this.appObserver = appObserver;
    }
}
