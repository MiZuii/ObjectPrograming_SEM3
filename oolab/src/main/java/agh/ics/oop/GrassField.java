package agh.ics.oop;
import agh.ics.oop.interfaces.IMapElement;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    final int n; // number of grass
    final int mapLength; // grass x,y is in range <0,mapLength>
    final Random randomizer;

    public GrassField(int numOfGrass) {
        this.n = numOfGrass;
        this.mapLength = (int)Math.sqrt(10*n);
        this.map = new HashMap<>();
        this.randomizer = new Random();
        this.mapBoundary = new MapBoundary();

        // placeing grass
        int i=0;
        while(i<n){
            IMapElement newItem = new Grass(new Vector2d(randomizer.nextInt(mapLength), randomizer.nextInt(mapLength)));
            if(this.place(newItem)) {
                // if item is possible to place, do it and generate next
                mapBoundary.addElement(newItem.getPosition());
                i++;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        /*
        Map is bounded only by the size of integer.
        Integer overflow doesn't need to be checked here
        because 'Vector2d' 'add' methode prevents it.
         */
        Object tmp = this.objectAt(position);
        if(tmp == null) { return true; }
        return (tmp instanceof Grass);
    }

    @Override
    public void positionChanged(Vector2d prev, Vector2d next) {
        IMapElement mapObject = (IMapElement)this.objectAt(prev);
        this.map.remove(prev);

        // if gras found at the targeted position, relocate it
        if(this.objectAt(next) instanceof Grass) {
            this.relocate(next);
        }

        this.map.put(next, mapObject);
    }

    @Override
    public boolean place(IMapElement newElement) {
        if (isOccupied(newElement.getPosition())) {
            IMapElement tmpElement = (IMapElement) objectAt(newElement.getPosition());
            if (tmpElement instanceof Animal) {
                return false;
            }
            this.relocate(tmpElement.getPosition());
        }
        this.map.put(newElement.getPosition(), newElement);

        // add observer if its animal
        if(newElement instanceof Animal animalElement) {
            animalElement.addObserver(this);
            animalElement.addObserver(this.mapBoundary);
            animalElement.addObserver(appObserver);
            mapBoundary.addElement(newElement.getPosition());
        }
        return true;
    }

    private void relocate(Vector2d position) {
        ArrayList<Vector2d> emptyPositions = new ArrayList<>();

        // gather all possible empty positions
        for(int row=0; row<mapLength; row++) {
            for(int column=0; column<mapLength; column++) {
                if(!(this.isOccupied(new Vector2d(column, row)))) {
                    emptyPositions.add(new Vector2d(column, row));
                }
            }
        }
        // if there are no more empty positions
        // just delete the Grass on initial position
        if(emptyPositions.size() == 0) {
            this.map.remove(position);
            return;
        }

        // choose one empty position randomly
        Vector2d newPosition = emptyPositions.get(randomizer.nextInt(emptyPositions.size()));
        mapBoundary.positionChanged(position, newPosition);
        this.map.remove(position);
        this.map.put(newPosition, new Grass(newPosition));
    }
}
