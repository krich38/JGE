package org.jge.model.server;

import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.Entity;
import org.jge.model.world.Waypoint;


/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerEncap {
    private int playerType;
    private Waypoint waypoint;
    private Id<Entity> id;
    private User user;
    private boolean ponged;

    public PlayerEncap(Id<Entity> id, int playerType, Waypoint waypoint, User user) {
        this.playerType = playerType;
        this.waypoint = waypoint;
        this.id = id;

        this.user = user;
    }

    public PlayerEncap() {

    }

    public void setPonged(boolean ponged) {
        this.ponged = ponged;
    }

    public boolean getPonged() {
        return ponged;
    }

    public Id<Entity> getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setId(Id<Entity> id) {
        this.id = id;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }
}
