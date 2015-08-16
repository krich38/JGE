package org.jge.model.world;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class World {
    public static final int TILES_X = 100;
    public static final int TILES_Y = 100;

    private Player player;
    private WorldMap map;
    private Ground ground;

    public World(Ground ground) {

        this.ground = ground;
    }

    public void tick(Set<KeyCode> keys, long delta) {
        if (!keys.isEmpty()) {
            for (KeyCode k : keys) {
                switch (k) {
                    case UP:

                        // if not collision
                        player.setStatus(Entity.EntityStatus.MOVEMENT_UP);
                        // otherwise
                        //player.setStatus(Entity.EntityStatus.FACING_UP);
                        break;
                    case DOWN:
                        player.setStatus(Entity.EntityStatus.MOVEMENT_DOWN);
                        break;
                    case LEFT:
                        player.setStatus(Entity.EntityStatus.MOVEMENT_LEFT);
                        break;
                    case RIGHT:
                        player.setStatus(Entity.EntityStatus.MOVEMENT_RIGHT);
                        break;
                }

            }
        } else {
            player.setStatus(Entity.EntityStatus.STATIONARY);
        }
        player.process(delta);
        map.process();
    }


    public void setPlayer(Player player) {

        this.player = player;
        map = WorldMap.load(player);
        ground.setMap(map);

    }

    public WorldMap getMap() {
        return map;
    }
}
