package org.jge.client.jfx.screen;

import javafx.scene.Scene;
import org.jge.client.jfx.Game;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {
    private final Scene scene;
    private final Game game;

    public Screen() {
        scene = buildScreen();
        game = Game.getGame();

    }

    public abstract Scene buildScreen();

    public Scene getScene() {
        return scene;
    }

    public void changeScreen(Screen screen) {
        game.updateScene(screen.getScene());
    }

    protected Game getGame() {
        return game;
    }
}
