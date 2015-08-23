package org.jge.client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.jge.client.jfx.Game;
import org.jge.client.jfx.screen.GameScreen;
import org.jge.model.world.Player;
import org.jge.model.world.World;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameEngine {

    private final World world;
    private final GameView view;
    private final Timer ticker;
    private final Timeline uiTicker;
    private GameScreen screen;
    private Player player;


    public GameEngine(GameScreen screen) {
        world = new World(screen.getGround());
        view = new GameView(screen);
        this.screen = screen;
        GameClient client = Game.getGame().getClient();
        ticker = new Timer();

        ticker.schedule(new TimerTask() {


            long lastrun = System.currentTimeMillis();

            @Override
            public void run() {
                if (client.isConnected()) {
                    long time = System.currentTimeMillis();
                    long delta = time - lastrun;
                    world.tick(screen.getKeys(), delta);
                    lastrun = time;
                    view.tick(delta);
                }

            }
        }, 500, 20);


        uiTicker = new Timeline(new KeyFrame(Duration.millis(20), (event) -> {
            if (client.isConnected()) {
                screen.repaint();
        }
        }));
        uiTicker.setCycleCount(Timeline.INDEFINITE);
        uiTicker.play();
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void stop() {
        ticker.cancel();
        uiTicker.stop();
    }
}
