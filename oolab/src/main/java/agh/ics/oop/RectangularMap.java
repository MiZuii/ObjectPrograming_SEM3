package agh.ics.oop;

public class RectangularMap implements IWorldMap{

    private int height;     // height of the map (max index = height-1)
    private int width;      // width of the map (max index = width-1)
    private Animal[] map;   // array where map[y*width+x] return Object on (x, y)

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new Animal[width*height];
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
            this.map[animal.getPosition().y*width + animal.getPosition().x] = animal;
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {return this.map[position.y*width+ position.x] != null;}

    @Override
    public Object objectAt(Vector2d position) {
        return this.map[position.y*width+ position.x];
    }

    @Override
    public String toString(){
        MapVisualizer graphics = new MapVisualizer(this);
        return graphics.draw(new Vector2d(0,0), new Vector2d(this.width -1, this.height -1));
    }

    @Override
    public void updatePosition(Vector2d prevPos){
        if (!(objectAt(prevPos) instanceof Animal)) { return; }
        Animal tomove = (Animal)objectAt(prevPos);
        this.map[prevPos.y*width+ prevPos.x] = null;
        this.map[tomove.getPosition().y*width + tomove.getPosition().x] = tomove;
    }
}
