package agh.ics.oop.interfaces;

import agh.ics.oop.interfaces.IPositionChangeObserver;

public interface IPositionChangeObserverHolder {
    /**
     * Adds an IPositionChangeObserver to
     * this object
     *
     * @param newObserver
     *            Observer to add
     */
    void addObserver(IPositionChangeObserver newObserver);

    /**
     * Removes an IPositionChangeObserver from
     * this object
     *
     * @param observerToRemove
     *          Obeserver to remove
     */
    void removeObserver(IPositionChangeObserver observerToRemove);
}
