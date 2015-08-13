package org.jge.client.jfx.screen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;
import org.jge.client.GameEngine;
import org.jge.client.listener.GameClientListener;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.model.world.World;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameScreen extends Screen {
    private final Game game;
    private final GameEngine engine;
    private final List<RenderableEntity> renderable;
    private final GameClient client;
    private final World world;
    private Set<KeyCode> keys;
    private Group chatGroup;
    private GridPane gp;
    private boolean chatFlag;
    private Canvas canvas;

    public GameScreen() {

        this.game = getGame();
        engine = new GameEngine(this);
        world = engine.getWorld();
        keys = new HashSet<>(5);
        renderable = new CopyOnWriteArrayList<>();
        client = game.getClient();

    }

    @Override
    public Scene buildScreen() {

        VBox vb = new VBox();
        gp = new GridPane();
        gp.setPrefHeight(650.0);
        gp.setPrefWidth(900.0);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.SOMETIMES);
        cc.setMaxWidth(900.0);
        cc.setMinWidth(10.0);
        cc.setPrefWidth(530.0);
        gp.getColumnConstraints().add(cc);


        RowConstraints rc = new RowConstraints();
        rc.setMaxHeight(371);
        rc.setMinHeight(0.0);
        rc.setPrefHeight(371.0);
        rc.setVgrow(Priority.SOMETIMES);
        gp.getRowConstraints().add(rc);






        canvas = new Canvas();

        canvas.setHeight(650);
        canvas.setWidth(900);
        canvas.getGraphicsContext2D().setFill(Color.GREEN);
        canvas.getGraphicsContext2D().fillRect(0, 0, 900, 650);
        Button sendButton = new Button("Send");
        sendButton.setPrefHeight(25.0);
        sendButton.setPrefWidth(70.0);
        TextField chatField = new TextField();
        chatField.setPrefHeight(25);
        chatField.setPrefWidth(canvas.getWidth() - sendButton.getPrefWidth());
        sendButton.setOnAction(event -> {


        });



        sendButton.setLayoutX(canvas.getWidth() - sendButton.getPrefWidth());
        sendButton.setLayoutY(canvas.getHeight()-sendButton.getPrefHeight());
        chatField.setLayoutX(0);
        chatField.setLayoutY(canvas.getHeight()-chatField.getPrefHeight());
        Group chatGroup = new Group(sendButton, chatField);
        vb.getChildren().add(gp);

        Group g = new Group(vb, canvas);
        Scene scene = new Scene(g, 900, 650);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            KeyCode code = event.getCode();

            if (code == KeyCode.ENTER) {
                if (chatFlag) {
                    // process chat text here
                    String chatMessage = chatField.getText();

                    g.getChildren().remove(chatGroup);
                    chatField.clear();
                    client.sendChatMessage(chatMessage);

                } else {



                    g.getChildren().add(chatGroup);


                }
                chatFlag = !chatFlag;


            } else {
                code = parseKey(code);
                if (code != null) {
                    keys.add(code);
                }
            }
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> {
            KeyCode code = parseKey(event.getCode());


            if (code != null) {
                keys.remove(code);
            }
        });

        return scene;
    }

    private KeyCode parseKey(KeyCode code) {
        KeyCode toReturn = null;
        switch (code) {
            case W:
                toReturn = KeyCode.UP;
                break;
            case S:
                toReturn = KeyCode.DOWN;
                break;
            case A:
                toReturn = KeyCode.LEFT;
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

    public void repaint() {
        if(!renderable.isEmpty()) {
            GraphicsContext g = canvas.getGraphicsContext2D();
            for(RenderableEntity e : renderable) {
e.render(g);
            }

        }

    }
}
