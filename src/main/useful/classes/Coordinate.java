package main.useful.classes;

public class Coordinate {
    final int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(String commaSeparated) {
        String[] split = commaSeparated.split(", ");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

    // Override hashing and equals methods so can use hashset.contains
    @Override
    public int hashCode() {
        return 1009 * this.getX() + this.getY();
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Coordinate)) {
            return false;
        }
        Coordinate coord_other = (Coordinate) other;
        return coord_other.getX() == this.getX() && coord_other.getY() == this.getY();
    }

    public int getManhattan(Coordinate otherCoord){
        return Math.abs(this.x - otherCoord.getX()) + Math.abs(this.y - otherCoord.getY());
    }
}
