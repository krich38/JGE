package org.jge.server.io;

import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.*;
import org.jge.protocol.game.PlayerLoad;

import java.sql.SQLException;

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
        Id<Entity> id = DatabaseAdapter.getId(user);
        return id;
    }

    public PlayerEncap load(Id<Entity> id, PlayerLoad loadRequest) {
        PlayerEncap pe = new PlayerEncap();
        pe.setId(id);
        pe.setPlayerType(loadRequest.getPlayerType());
        pe.setUser(loadRequest.getUser());
        DatabaseAdapter.fillPlayerEncap(pe);


        return pe;
    }

    public boolean savePlayer(PlayerEncap pe) {
        try {
            DatabaseAdapter.updatePlayer(pe);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User.AccessRights getRightsFor(Id<Entity> id) {
        return DatabaseAdapter.getRightsById(id);
    }
}
