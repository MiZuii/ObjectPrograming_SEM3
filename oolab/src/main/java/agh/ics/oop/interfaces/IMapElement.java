package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;
import javafx.scene.image.Image;

/**
 * The interface responsible for provideing compatibility
 * functions between Object and IWorldMap
 *
 * @author MiZuii
 *
 */
public interface IMapElement {
    /**
     * Return position of given object
     *
     * @return Position.
     */
    Vector2d getPosition();

    /**
     * Return image of given element
     *
     * @return Image.
     */
    Image getImage();
}
