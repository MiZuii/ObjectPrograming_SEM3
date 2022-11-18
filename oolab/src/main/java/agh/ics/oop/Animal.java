package agh.ics.oop;

public class Animal implements IMapElement{
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            default -> "X";
        };
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    @Override
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
            }
            case BACKWARD -> {
                this.position = this.position.add(this.orientation.toUnitVector().oposite());
            }
        }
        if (!this.map.canMoveTo(this.position)) {
            this.position = before_vec;
            return;
        }
        this.map.positionUpdate(before_vec, this.position);
    }
}
