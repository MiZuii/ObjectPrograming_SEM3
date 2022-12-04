package agh.ics.oop;

import agh.ics.oop.interfaces.IPositionChangeObserver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private SortedSet<Vector2d> xSet = new TreeSet<Vector2d>(Comparator.comparing( (Vector2d el1) -> el1.x));
    private SortedSet<Vector2d> ySet = new TreeSet<Vector2d>(Comparator.comparing( (Vector2d el1) -> el1.y));
    private ArrayList<Vector2d> grassList = new ArrayList<Vector2d>();

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

    public void positionChangedGrass(Vector2d prev, Vector2d next){
        grassList.remove(prev);
        grassList.add(next);
    }

    public void addGrass(Vector2d newGrass){
        grassList.add(newGrass);
    }

    public Vector2d[] getDimentions() {
        Vector2d GrassLL = grassList.get(0);
        Vector2d GrassUR = grassList.get(0);

        for (Vector2d position : grassList) {
            GrassLL = GrassLL.lowerLeft(position);
            GrassUR = GrassUR.upperRight(position);
        }

        return new Vector2d[]{xSet.first().lowerLeft(ySet.first()).lowerLeft(GrassLL),
                xSet.last().upperRight(ySet.last()).upperRight(GrassUR)};
    }
}
