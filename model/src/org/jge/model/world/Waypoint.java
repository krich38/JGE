package org.jge.model.world;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Waypoint {
    private final long key;
    private final int x;
    private final int y;

    public Waypoint(int x, int y) {
        this.x = x;
        this.y = y;
        long key = x << 16 | y;
        this.key = key;
    }
}
