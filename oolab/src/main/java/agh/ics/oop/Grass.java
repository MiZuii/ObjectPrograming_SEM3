package agh.ics.oop;

import agh.ics.oop.interfaces.IMapElement;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class Grass implements IMapElement {

    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public Image getImage(){
        Image image = new Image(getClass().getResourceAsStream("/Images/grass.png"));
        return image;
    }

    @Override
    public String toString() {
        return "Trawa";
    }
}
