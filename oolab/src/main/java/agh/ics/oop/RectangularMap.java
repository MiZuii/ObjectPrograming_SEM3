package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap{

    private int height;
    private int width;
    private Animal[] map;
    private ArrayList<Animal> animal_order = new ArrayList<Animal>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new Animal[width*height];
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x < width && position.x >= 0 &&
                position.y < height && position.y >= 0);
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) {
            return false;
        }
        else {
            this.map[animal.getPosition().y*width + animal.getPosition().x] = animal;
            this.animal_order.add(animal);
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map[position.y*width+ position.x] == null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map[position.y*width+ position.x];
    }

    @Override
    public String toString(){
        MapVisualizer graphics = new MapVisualizer(this);
        return graphics.draw(new Vector2d(0,0),
                new Vector2d(this.width -1, this.height -1));
    }

    public ArrayList<Animal> getAnimal_order() {
        return this.animal_order;
    }
}
