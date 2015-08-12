package org.jge.client;

import org.jge.client.jfx.screen.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameEngine {

    private GameScreen screen;

    public GameEngine(GameScreen screen) {
        this.screen = screen;
        Timer ticker = new Timer();
        ticker.schedule(new TimerTask() {

            @Override
            public void run() {
                if(!screen.getKeys().isEmpty()) {
                    System.out.println(screen.getKeys());
                }
            }
        },500, 20);
    }
}
