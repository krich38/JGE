package org.jge.server.io;

import org.jge.model.User;
import org.jge.model.world.Player;
import org.jge.model.world.Tile;
import org.jge.model.world.World;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PlayerLoader {
    public Player loadPlayer(User user) {
        return newPlayer(user);
    }

    public Player newPlayer(User user){
//        Player player = new Player();
//        player.setTile(World.tileOf(60, 30));
//        player.setUser(user);
        return null;
    }
}
