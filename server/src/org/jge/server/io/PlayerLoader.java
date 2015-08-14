package org.jge.server.io;

import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.*;
import org.jge.protocol.packet.PlayerLoad;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerLoader {
    public PlayerLoad loadPlayer(User user) {
        return newPlayer(user);
    }

    public PlayerLoad newPlayer(User user) {
//        Player player = new Player();
//        player.setTile(World.tileOf(60, 30));
//        player.setUser(user);
        PlayerLoad load = new PlayerLoad();

        return load;
    }

    public Id<Entity> loadId(User user) {
        return Id.generate();
    }

    public void load(PlayerLoad load) {

        load.setPlayerType(1);
        load.setWaypoint(new Waypoint(10, 15));
    }
}
