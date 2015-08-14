package org.jge.model.server;

import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.Entity;
import org.jge.model.world.Waypoint;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerEncap extends Entity {
    private final int playerType;
    private final Waypoint waypoint;

    public PlayerEncap(Id<Entity> id, int playerType, Waypoint waypoint, User user) {
        this.playerType = playerType;
        this.waypoint = waypoint;
        super.id = id;

    }

}
