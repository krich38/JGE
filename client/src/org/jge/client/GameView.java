package org.jge.client;

import org.jge.client.jfx.Game;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.model.world.WorldMap;

import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameView {
    private final Player player;

    private List<RenderableEntity> renderable;


    public GameView(List<RenderableEntity> renderable) {
        this.renderable = renderable;
player = Game.getGame().getPlayer();


    }
    // todo: load tiles, player, etc all that is viewable, into the renderable of game screen
    public void tick() {

    }
}
