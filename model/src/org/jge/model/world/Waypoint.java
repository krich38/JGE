package org.jge.model.world;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Waypoint {
    private long key;
    private int x;
    private int y;

    public Waypoint(int x, int y) {
        this.x = x;
        this.y = y;

        long key = x << 16 | y;
        this.key = key;
    }

    public Waypoint() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + getX() + ", " + getY() + "]";
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

}
