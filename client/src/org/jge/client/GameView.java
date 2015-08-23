package org.jge.client;

import org.jge.client.jfx.Game;
import org.jge.client.jfx.screen.GameScreen;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.protocol.common.ChatMessage;

import java.util.List;
import java.util.Queue;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameView {
    private final Player player;
    private final Game game;


    private List<RenderableEntity> renderable;
    private GameScreen screen;


    public GameView(GameScreen screen) {
        this.screen = screen;
        this.renderable = screen.getRenderable();
        game = Game.getGame();
        player = game.getPlayer();


    }

    // todo: load tiles, player, etc all that is viewable, into the renderable of game screen
    public void tick(long delta) {

        Queue<ChatMessage> msgs = game.getMessageQueue();
        if (!msgs.isEmpty()) {

            screen.getChatWindow().process(msgs);
            //msgs.clear();

        }
        //game.getEngine().getWorld().getMap().fillTiles(renderable);
        for (RenderableEntity e : renderable) {
            e.process(delta);


        }
    }
}
