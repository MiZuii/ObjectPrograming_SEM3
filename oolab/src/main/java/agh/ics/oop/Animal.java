package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;

    @Override
    public String toString() {
        return "{polozenie: " + position.toString() + ", orientacja: " + orientation.toString() + "}";
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    private boolean inBounds(){
        if (this.position.y < 0 || this.position.y > 4 || this.position.x < 0 || this.position.x > 4) { return false; }
        return true;
    }

    public void move(MoveDirection direction){
        Vector2d before_vec = this.position;

        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector());
                if (this.inBounds()) { this.position = before_vec; }
            }
            case BACKWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector().oposite());
                if (this.inBounds()) { this.position = before_vec; }
            }
        }
    }
}
