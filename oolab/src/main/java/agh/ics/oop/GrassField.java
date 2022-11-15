package agh.ics.oop;

public class GrassField implements IWorldMap {

    private int n;

    public GrassField(int numOfGrass) {
        this.n = numOfGrass;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
