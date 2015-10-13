package org.jge.client.jfx.screen;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {

    private final Game game;
    private final GameClient client;

    public Screen() {

        game = Game.getGame();
        client = game.getClient();

    }

    public abstract Scene buildScreen();

    public void changeScreen(Screen screen) {
        destroy();
        game.updateScreen(screen);
    }

    protected Game getGame() {
        return game;
    }

    public GameClient getClient() {
        return client;
    }

    public abstract void destroy();

    public void showPopup(String text) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setScene(new PopupAlert(text).buildScreen());
            stage.show();
        });
    }
}
