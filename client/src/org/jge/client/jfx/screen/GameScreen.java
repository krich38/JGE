package org.jge.client.jfx.screen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;
import org.jge.model.world.Ground;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.protocol.game.PlayerLoad;

import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameScreen extends Screen {
    public static final double SCREEN_HEIGHT = 775;
    public static final double SCREEN_WIDTH = 900;
    private final Game game;
    private final List<RenderableEntity> renderable;
    private final GameClient client;
    private final Player player;

    private Set<KeyCode> keys;
    private GridPane gp;
    private boolean chatFlag;
    private GraphicsContext g;
    private ChatWindow chatWindow;
    private Scene scene;
    private Group group;
    private Canvas canvas;
    private Ground ground;

    public GameScreen(int characterType) {

        this.game = getGame();

        keys = new HashSet<>(5);
        renderable = new CopyOnWriteArrayList<>();
        client = game.getClient();
        player = Game.getGame().getPlayer();
        ground = new Ground(player);
        PlayerLoad request = new PlayerLoad();
        request.setId(game.getPlayer().getId());
        request.setPlayerType(characterType);
        client.send(request);
    }

    @Override
    public Scene buildScreen() {

        VBox vb = new VBox();
        gp = new GridPane();
        gp.setPrefHeight(775.0);
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


        canvas = ground.getCanvas();

        g = canvas.getGraphicsContext2D();
        vb.getChildren().add(gp);
        chatWindow = new ChatWindow(canvas.getWidth(), canvas.getHeight(), this);
        group = new Group(vb, canvas, player.getNode(), chatWindow.getWindow());

        renderable.add(ground);

        scene = new Scene(group, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setOnMouseClicked((event1 -> {
            canvas.requestFocus();
        }));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            KeyCode code = event.getCode();
            if (chatWindow.isFocused()) {
                if (code.equals(KeyCode.ENTER)) {
                    chatWindow.onSend();
                } else if (code.equals(KeyCode.ESCAPE)) {
                    canvas.requestFocus();
                }
            } else {
                if(event.isControlDown() && code.equals(KeyCode.L)) {


                    changeScreen(new LoginScreen());
                } else {
                    code = parseKey(code);
                    if (code != null) {
                        keys.add(code);
                    }
                }
            }

        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> {
            KeyCode code = parseKey(event.getCode());


            if (code != null) {
                keys.remove(code);
            }
        });
        canvas.requestFocus();
        scene.getStylesheets().add(getClass().getResource("/graphics/game.css").toExternalForm());
        return scene;
    }

    @Override
    public void destroy() {
        client.logout();
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
        // System.out.println(player.getWaypoint());
        // System.out.println(renderable.size());
        if (!renderable.isEmpty()) {

            for (RenderableEntity e : renderable) {

                if (e.requiresRendering()) {
                    e.render(g);
                }
            }

        }

    }

    public List<RenderableEntity> getRenderable() {
        return renderable;
    }


    public ChatWindow getChatWindow() {
        return chatWindow;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Ground getGround() {
        return ground;
    }
}
