package org.jge.model.world;

import javafx.scene.image.Image;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Entity {

    public Waypoint location;
    public Tile tile;

    public Waypoint getLocation() {
        return location;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

}
