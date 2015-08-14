package org.jge.model.world;

import javafx.scene.image.Image;
import org.jge.model.Id;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Entity {
    private EntityStatus status;
    private EntityStatus lastStatus = EntityStatus.FACING_DOWN;
    public static final double MOV_PER_MILLI = 0.2;

    public void setLastStatus(EntityStatus lastStatus) {
        this.lastStatus = lastStatus;
    }
    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    public abstract void process(long delta);

    public EntityStatus getStatus() {
        return status;
    }

    public EntityStatus getLastStatus() {
        return lastStatus;
    }

    public enum EntityStatus {
        FACING_UP, STATIONARY, FACING_DOWN, MOVEMENT_DOWN, MOVEMENT_RIGHT, MOVEMENT_LEFT, FACING_LEFT, FACING_RIGHT, MOVEMENT_UP

}
    public Waypoint waypoint;
    public Tile tile;
    protected Id<Entity> id;

    public Waypoint getWaypoint() {
        return waypoint;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Id<Entity> getId() {
        return id;
    }

    public void setWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }
}
