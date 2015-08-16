package org.jge.client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
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
    private GameScreen screen;
    private Player player;


    public GameEngine(GameScreen screen) {
        world = new World();
        view = new GameView(screen);
        this.screen = screen;
        Timer ticker = new Timer();
        ticker.schedule(new TimerTask() {
            long lastrun = System.currentTimeMillis();

            @Override
            public void run() {
                long time = System.currentTimeMillis();
                long delta = time - lastrun;
                world.tick(screen.getKeys(), delta);
                lastrun = time;
                view.tick(delta);

            }
        }, 500, 20);


        Timeline ui = new Timeline(new KeyFrame(Duration.millis(20), (event) -> {
            screen.repaint();
        }));
        ui.setCycleCount(Timeline.INDEFINITE);
        ui.play();
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
