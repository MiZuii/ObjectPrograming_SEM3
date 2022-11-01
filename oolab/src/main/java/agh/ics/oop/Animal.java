package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.map.place(this);
    }

    @Override
    public String toString() {
        switch (this.orientation) {
            case NORTH: return "N";
            case EAST: return "E";
            case SOUTH: return "S";
            case WEST: return "W";
            default: return "Unknown direction";
        }
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void move(MoveDirection direction){
        Vector2d before_vec = this.position;

        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector());
                if (!this.map.canMoveTo(this.position)) { this.position = before_vec; }
            }
            case BACKWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector().oposite());
                if (!this.map.canMoveTo(this.position)) { this.position = before_vec; }
            }
        }
    }
}
