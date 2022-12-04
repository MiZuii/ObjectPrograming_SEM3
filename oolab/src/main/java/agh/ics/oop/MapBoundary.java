package agh.ics.oop;

import agh.ics.oop.interfaces.IPositionChangeObserver;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private SortedSet<Vector2d> xSet = new TreeSet<Vector2d>(Comparator.comparing( (Vector2d el1) -> el1.x));
    private SortedSet<Vector2d> ySet = new TreeSet<Vector2d>(Comparator.comparing( (Vector2d el1) -> el1.y));

    @Override
    public void positionChanged(Vector2d prevPosition, Vector2d nextPosition) {
        xSet.remove(prevPosition);
        ySet.remove(prevPosition);
        xSet.add(nextPosition);
        ySet.add(nextPosition);
    }

    public void addElement(Vector2d newElement) {
        xSet.add(newElement);
        ySet.add(newElement);
    }

    public Vector2d[] getDimentions() {
        return new Vector2d[]{xSet.first().lowerLeft(ySet.first()), xSet.last().upperRight(ySet.last())};
    }
}
