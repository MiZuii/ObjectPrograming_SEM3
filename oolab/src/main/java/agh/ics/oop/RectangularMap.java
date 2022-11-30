package agh.ics.oop;
import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap {

    final int height;     // height of the map (max index = height-1)
    final int width;      // width of the map (max index = width-1)

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new HashMap<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x < width && position.x >= 0 &&
                position.y < height && position.y >= 0 &&
                !this.isOccupied(position));
    }

    @Override
    public boolean place(IMapElement newElement) {
        if (isOccupied(newElement.getPosition())) {
            return false;
        }
        this.map.put(newElement.getPosition(), newElement);
        ((Animal) newElement).addObserver(this);
        ((Animal) newElement).addObserver(appObserver);
        return true;
    }

    @Override
    protected Vector2d[] toStringComponents() {
        return new Vector2d[]{new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1)};
    }
}
