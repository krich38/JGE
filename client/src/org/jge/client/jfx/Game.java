package org.jge.client.jfx;

import com.esotericsoftware.minlog.Log;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jge.client.GameClient;
import org.jge.client.GameEngine;
import org.jge.client.jfx.screen.GameScreen;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.client.jfx.screen.Screen;
import org.jge.model.world.Player;
import org.jge.protocol.common.ChatMessage;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    private Queue<ChatMessage> messageQueue;

    public static Game getGame() {
        return INSTANCE;
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        //stage.setResizable(false);
        stage.setOnCloseRequest((event) -> destroy());
        client = new GameClient();
        currentScreen = new LoginScreen();
        stage.setScene(currentScreen.buildScreen());
        Log.set(Log.LEVEL_ERROR);
        stage.show();
    }

    private void destroy() {
        if (client.isConnected()) {
            client.logout();engine.stop();
        }

        currentScreen.destroy();
        stage.setScene(null);
    }


    public void updateScreen(Screen screen) {
        Platform.runLater(() -> {
            currentScreen = screen;

            stage.setScene(screen.buildScreen());
            stage.centerOnScreen();
        });

    }

    public static void main(String[] args) {
        launch(args);

    }

    public Game() {
        INSTANCE = this
        ;

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
        messageQueue = new ConcurrentLinkedQueue<>();
    }

    public GameEngine getEngine() {
        return engine;
    }

    public void addChatMessage(ChatMessage msg) {
        messageQueue.add(msg);
    }

    public Queue<ChatMessage> getMessageQueue() {
        return messageQueue;
    }

    public Screen getScreen() {
        return currentScreen;
    }
}
