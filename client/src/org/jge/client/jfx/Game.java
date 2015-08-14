package org.jge.client.jfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jge.client.GameClient;
import org.jge.client.GameEngine;
import org.jge.client.jfx.screen.GameScreen;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.client.jfx.screen.Screen;
import org.jge.model.User;
import org.jge.model.world.Player;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Game extends Application {
    private static Game INSTANCE;
    private Stage stage;
    private GameClient client;
    private Screen currentScreen;
    private Player player;
    private GameEngine engine;

    public static Game getGame() {
        return INSTANCE;
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest((event) -> System.exit(0));
        client = new GameClient();
        currentScreen = new LoginScreen();
        stage.setScene(currentScreen.buildScreen());
        stage.centerOnScreen();
        stage.show();
    }


    public void updateScreen(Screen screen) {
        Platform.runLater(() -> {
            currentScreen = screen;
            stage.setScene(screen.buildScreen());
        });

    }

    public static void main(String[] args) {
        launch(args);

    }

    public Game() {
        INSTANCE = this;
    }


    public GameClient getClient() {
        return client;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void connected() {
        engine = new GameEngine((GameScreen) currentScreen);
        engine.setPlayer(player);
    }

    public GameEngine getEngine() {
        return engine;
    }
}
