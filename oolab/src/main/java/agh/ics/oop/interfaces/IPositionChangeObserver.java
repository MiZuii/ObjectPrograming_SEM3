package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;

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
