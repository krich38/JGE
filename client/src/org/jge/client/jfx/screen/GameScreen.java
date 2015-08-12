package org.jge.client.jfx.screen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.jge.client.jfx.Game;
import org.jge.client.GameEngine;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameScreen extends Screen {
    private final Game game;
    private final GameEngine engine;
    private int character;
    private Set<KeyCode> keys;
    private Group chatGroup;
    private GridPane gp;
    private boolean chatFlag;

    public GameScreen(int character) {

        this.character = character;
        this.game = getGame();
        engine = new GameEngine(this);
        keys = new HashSet<>(5);
    }

    @Override
    public Scene buildScreen() {

        VBox vb = new VBox();
        gp = new GridPane();
        gp.setPrefHeight(400.0);
        gp.setPrefWidth(600.0);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.SOMETIMES);
        cc.setMaxWidth(600.0);
        cc.setMinWidth(10.0);
        cc.setPrefWidth(530.0);
        gp.getColumnConstraints().add(cc);

//        ColumnConstraints cc1 = new ColumnConstraints();
//        cc1.setHgrow(Priority.SOMETIMES);
//        cc1.setMaxWidth(328.0);
//        cc1.setMinWidth(0.0);
//        cc1.setPrefWidth(70.0);
//        gp.getColumnConstraints().add(cc1);


        RowConstraints rc = new RowConstraints();
        rc.setMaxHeight(371);
        rc.setMinHeight(0.0);
        rc.setPrefHeight(371.0);
        rc.setVgrow(Priority.SOMETIMES);
        gp.getRowConstraints().add(rc);

//        RowConstraints rc1 = new RowConstraints();
//        rc1.setMaxHeight(373);
//        rc1.setMinHeight(0.0);
//        rc1.setPrefHeight(0.0);
//        rc1.setVgrow(Priority.SOMETIMES);
//        gp.getRowConstraints().add(rc1);

//        RowConstraints rc2 = new RowConstraints();
//        rc1.setMaxHeight(220);
//        rc1.setMinHeight(10.0);
//        rc1.setPrefHeight(29.0);
//        rc1.setVgrow(Priority.SOMETIMES);
//        gp.getRowConstraints().add(rc1);


        TextField chatField = new TextField();


        Button sendButton = new Button("Send");
        sendButton.setPrefHeight(25.0);
        sendButton.setPrefWidth(70.0);


        Canvas canvas = new Canvas();

        canvas.setHeight(400);
        canvas.setWidth(600);
        sendButton.setOnAction(event -> {


        });


        vb.getChildren().add(gp);

        Group g = new Group(vb, canvas);
Scene scene = new Scene(g, 600,400);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            KeyCode code = event.getCode();

            if(code == KeyCode.ENTER) {
                if (chatFlag) {
                    // process chat text here
                    gp.getChildren().remove(chatField);
                    gp.getChildren().remove(sendButton);
                } else {
                    gp.add(sendButton, 1, 2);
                    gp.add(chatField, 0, 2);
                }
                chatFlag = !chatFlag;


            } else {
                code = parseKey(code);if (code != null) {
                    keys.add(code);
                }
            }
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> {
            KeyCode code = parseKey(event.getCode());


            if(code != null) {
                keys.remove(code);
            }
        });

        return scene;
    }

    private KeyCode parseKey(KeyCode code) {
        KeyCode toReturn = null;
        switch(code) {
            case W:
                toReturn = KeyCode.UP;
                break;
            case S:
                toReturn = KeyCode.DOWN;
                break;
            case A:
                toReturn= KeyCode.LEFT;
                break;
            case D:
                toReturn = KeyCode.RIGHT;
                break;
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                toReturn = code;

        }
        return toReturn;
    }

    public Set<KeyCode> getKeys() {
        return keys;
    }
}
