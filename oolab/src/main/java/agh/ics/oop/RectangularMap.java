package agh.ics.oop;
import java.util.HashMap;

public class RectangularMap implements IWorldMap{

    private int height;     // height of the map (max index = height-1)
    private int width;      // width of the map (max index = width-1)
    private HashMap<Vector2d, Animal> map;   // hashmap

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x < width && position.x >= 0 &&
                position.y < height && position.y >= 0 &&
                !this.isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) {
            return false;
        }
        else {
            this.map.put(animal.getPosition(), animal);
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) { return this.map.get(position) != null; }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer graphics = new MapVisualizer(this);
        return graphics.draw(new Vector2d(0,0), new Vector2d(this.width -1, this.height -1));
    }
}
