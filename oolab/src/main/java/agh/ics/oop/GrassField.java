package agh.ics.oop;
import java.lang.Math;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private int n; // number of grass
    final int mapLength; // grass x,y is in range <0,mapLength>
    private Random randomizer;

    public GrassField(int numOfGrass) {
        this.n = numOfGrass;
        this.mapLength = (int)Math.sqrt(10*n);
        this.map = new HashMap<>();
        this.randomizer = new Random();

        // placeing grass
        int i=0;
        while(i<n){
            IMapElement newItem = new Grass(new Vector2d(randomizer.nextInt(mapLength), randomizer.nextInt(mapLength)));
            if(this.place(newItem)) {
                // if item is possible to place, do it and generate next
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
        return !this.isOccupied(position);
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
        return true;
    }

    @Override
    protected Vector2d[] toStringComponents() {
        Vector2d lowerLeft;
        Vector2d upperRight;

        // generate iterator with this.map key values
        Iterator<Vector2d> mapKeys = this.map.keySet().iterator();

        // set initial lower and upper vectors
        if (mapKeys.hasNext()) {
            lowerLeft = mapKeys.next();
            upperRight = lowerLeft;
        }
        else {
            return new Vector2d[]{new Vector2d(0, 0), new Vector2d(0, 0)};
        }

        while (mapKeys.hasNext()) {
            Vector2d vec = mapKeys.next();

            lowerLeft = lowerLeft.lowerLeft(vec);
            upperRight = upperRight.upperRight(vec);
        }

        return new Vector2d[]{lowerLeft, upperRight};
    }

    public void relocate(Vector2d position) {
        while (true) {
            IMapElement newItem = new Grass(new Vector2d(randomizer.nextInt(mapLength), randomizer.nextInt(mapLength)));
            if(this.positionUpdate(position, newItem.getPosition())) {
                // if item is possible to place, do it and return
                return;
            }
        }
    }
}
