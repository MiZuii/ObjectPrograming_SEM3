package agh.ics.oop;

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
}
