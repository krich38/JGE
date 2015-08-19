package org.jge.protocol.packet;

import org.jge.model.Id;
import org.jge.model.world.Entity;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerLoad extends Packet {
      private Id<Entity> id;
    private int playerType;

    public PlayerLoad() {
        super(PacketType.PLAYER_LOAD);
    }




    public Id<Entity> getId() {
        return id;
    }



    public void setId(Id<Entity> id) {
        this.id = id;
    }


    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public int getPlayerType() {
        return playerType;
    }
}
