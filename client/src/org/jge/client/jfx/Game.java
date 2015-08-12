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
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.client.jfx.screen.Screen;
import org.jge.model.User;

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

    public static Game getGame() {
        return INSTANCE;
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setResizable(false);
        client = new GameClient();
        loginScreen();
        stage.show();
    }

    private void loginScreen() {
        currentScreen = new LoginScreen(this, client);
updateScene(currentScreen.buildScreen());

    }

    public void updateScene(Scene scene) {
Platform.runLater(() -> {
    stage.setScene(scene);
    stage.centerOnScreen();
});

    }

    public static void main(String[] args) {
       launch(args);

    }

    public Game() {
        INSTANCE = this;
    }


}
