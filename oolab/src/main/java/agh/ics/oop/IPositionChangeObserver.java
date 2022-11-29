package agh.ics.oop;

public interface IPositionChangeObserver {
    /**
     * Update elements in map at given vectors
     *
     * @param prevPosition
     *            Previous position of the object.
     * @param nextPosition
     *            Desired position of the object.
     */
    void positionChanged(Vector2d prevPosition, Vector2d nextPosition);
}
