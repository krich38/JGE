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
    private static final Map<Long, Tile> tiles = new HashMap<>(TILES_X * TILES_Y);

    public void tick(Set<KeyCode> keys, long delta) {

    }

    public static Tile tileOf(int x, int y) {
        long key = x << 16 | y;
        Tile tile = tiles.get(key);
        if (tile == null) {
            tile = new Tile(x, y);
            tiles.put(key, tile);
        }
        return tile;
    }
}
