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
      private Id<Entity> id;

    public PlayerLoad() {
        super(PacketType.PLAYER_LOAD);
    }




    public Id<Entity> getId() {
        return id;
    }



    public void setId(Id<Entity> id) {
        this.id = id;
    }



}
