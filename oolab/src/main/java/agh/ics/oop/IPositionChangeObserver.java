package agh.ics.oop;

public interface IPositionChangeObserver {
    /**
     * Update elements in map at given vectors
     *
     * @param prevPosition
     *            Previous position of the object.
     * @param nextPosition
     *            Desired position of the object.
     * @return True if was completed. False if something failed.
     */
    boolean positionChanged(Vector2d prevPosition, Vector2d nextPosition);
}
