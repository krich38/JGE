package org.jge.protocol.packet;

import org.jge.model.Id;
import org.jge.model.world.Entity;
import org.jge.model.world.Player;
import org.jge.model.world.Waypoint;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerLoad extends Packet {
    private int playerType;
    private Id<Entity> id;
    private Waypoint waypoint;

    public PlayerLoad() {
        super(PacketType.PLAYER_LOAD);
    }


    public int getPlayerType() {
        return playerType;
    }

    public Id<Entity> getId() {
        return id;
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    public void setId(Id<Entity> id) {
        this.id = id;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public void setWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }
}
