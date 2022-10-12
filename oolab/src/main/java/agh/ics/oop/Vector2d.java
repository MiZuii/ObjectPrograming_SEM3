package agh.ics.oop;

import java.util.Vector;

public class Vector2d {
    final int x;
    final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y) { return true; }
        return false;
    }

    public boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y) { return true; }
        return false;
    }

    public Vector2d upperRight(Vector2d other) {
        int x;
        int y;

        if (this.x >= other.x) { x=this.x; }
        else { x = other.x; }

        if (this.y >= other.y) { y=this.y; }
        else { y = other.y; }

        return new Vector2d(x, y);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int x;
        int y;

        if (this.x <= other.x) { x=this.x; }
        else { x = other.x; }

        if (this.y <= other.y) { y=this.y; }
        else { y = other.y; }

        return new Vector2d(x, y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (!(other instanceof Vector2d)) { return false; }
        Vector2d that = (Vector2d) other;

        if (this.x == that.x && this.y == that.y) { return true; }
        return false;
    }

    public Vector2d oposite() {
        return new Vector2d(this.x*(-1), this.y*(-1));
    }

}