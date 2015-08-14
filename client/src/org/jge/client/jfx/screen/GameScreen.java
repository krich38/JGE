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
import org.jge.model.world.Ground;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.model.world.World;
import org.jge.protocol.packet.PlayerLoad;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameScreen extends Screen {
    public static final double SCREEN_HEIGHT = 650;
    public static final double SCREEN_WIDTH=900;
    private final Game game;
    private final List<RenderableEntity> renderable;
    private final GameClient client;
    private final Player player;

    private Set<KeyCode> keys;
    private GridPane gp;
    private boolean chatFlag;
    private GraphicsContext g;

    public GameScreen() {

        this.game = getGame();

        keys = new HashSet<>(5);
        renderable = new CopyOnWriteArrayList<>();
        client = game.getClient();
        player = Game.getGame().getPlayer();

        PlayerLoad request = new PlayerLoad();
        request.setId(game.getPlayer().getId());
        client.send(request);
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

            Ground ground = new Ground(player);
Canvas canvas = ground.getCanvas();
        g=canvas.getGraphicsContext2D();
        Button sendButton = new Button("Send");
        sendButton.setPrefHeight(25.0);
        sendButton.setPrefWidth(70.0);
        vb.getChildren().add(gp);
        TextField chatField = new TextField();
        chatField.setPrefHeight(25);
        chatField.setPrefWidth(canvas.getWidth() - sendButton.getPrefWidth());
        sendButton.setLayoutX(canvas.getWidth() - sendButton.getPrefWidth());
        sendButton.setLayoutY(canvas.getHeight() - sendButton.getPrefHeight());
        chatField.setLayoutX(0);
        chatField.setLayoutY(canvas.getHeight() - chatField.getPrefHeight());
        Group chatGroup = new Group(sendButton, chatField);
        Group g = new Group(vb, canvas, player.getNode());
        sendButton.setOnAction(event -> {
            String chatMessage = chatField.getText();

            g.getChildren().remove(chatGroup);
            chatField.clear();
            client.sendChatMessage(chatMessage);

        });
renderable.add(ground);

        Scene scene = new Scene(g, SCREEN_WIDTH, SCREEN_HEIGHT);
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
                    chatField.requestFocus();

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
        if (!renderable.isEmpty()) {

            for (RenderableEntity e : renderable) {

                e.render(g);
            }

        }

    }

    public List<RenderableEntity> getRenderable() {
        return renderable;
    }
}
