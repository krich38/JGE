package org.jge.model.world;

import javafx.scene.image.Image;
import org.jge.model.Id;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Entity {

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
