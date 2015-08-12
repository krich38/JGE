package org.jge.client.jfx.screen;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static javafx.stage.Screen.getPrimary;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameScreen extends Screen {
    private int character;

    public GameScreen(int character) {

        this.character = character;
    }

    @Override
    public Scene buildScreen() {
        TextField chatEntry = new TextField();
        chatEntry.setPromptText("Enter your message. Type /help for help.");
        // Define a group so we can add stuff under the scene instead of having one pane occupied the scene.
        Group root = new Group();
        Scene scene = new Scene(root, 600, getPrimary().getVisualBounds().getHeight() / 2, Color.GRAY);
        // some strange border bug for resizable = true.

        BorderPane borderPane = new BorderPane();

        root.getChildren().add(borderPane);




        scene.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {

        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (key) -> {

        });
        return scene;
    }
}
