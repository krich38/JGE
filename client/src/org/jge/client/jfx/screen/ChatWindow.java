package org.jge.client.jfx.screen;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;
import org.jge.model.User;
import org.jge.model.world.RenderableEntity;
import org.jge.protocol.packet.ChatMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ChatWindow {
    private final Group chatGroup;
    private final TextField chatField;

    private final GameScreen gameScreen;
    private final GameClient client;
    private final TextArea chatArea;
    private boolean visible;

    public ChatWindow(double canvasWidth, double canvasHeight, GameScreen gameScreen) {
        this.client = Game.getGame().getClient();
        this.gameScreen = gameScreen;
        Button sendButton = new Button("Send");
        sendButton.setPrefHeight(25.0);
        sendButton.setPrefWidth(70.0);


        chatArea = new TextArea();
        chatArea.setPrefRowCount(5);

        //chatArea.setMaxHeight(100);
        chatArea.setPrefHeight(100);
        chatArea.setPrefWidth(canvasWidth);
        chatArea.setLayoutY(canvasHeight);
        chatArea.setFocusTraversable(false);
        chatArea.setId("chatArea");
        chatArea.focusedProperty().addListener((listener) -> {
            gameScreen.getCanvas().requestFocus();
        });
        chatField = new TextField();
        chatField.setPrefHeight(25);
        chatField.setPrefWidth(canvasWidth - sendButton.getPrefWidth());
        chatField.setLayoutX(0);
        chatField.setLayoutY(chatArea.getLayoutY() + chatArea.getPrefHeight());
        chatField.setFocusTraversable(false);
        sendButton.setLayoutX(canvasWidth - sendButton.getPrefWidth());
        sendButton.setLayoutY(chatField.getLayoutY());
        sendButton.setId("btnSend");


        sendButton.setOnAction(event -> {
            onSend();

        });

        chatGroup = new Group(sendButton, chatField, chatArea);
    }

    public void onSend() {
        String chatMessage = chatField.getText();
        if (chatMessage.length() > 0) {
            chatField.clear();
            client.sendChatMessage(chatMessage);
        }
    }

    public Group getWindow() {
        return chatGroup;
    }


    public boolean isVisible() {
        return visible;
    }

    public void process(Queue<ChatMessage> msgs) {

        Platform.runLater(() -> {

            for (ChatMessage msg : msgs) {
                System.out.println(msg);
                User user = msg.getUser();
                chatArea.appendText(user.getUsername() + ": " + msg.getMessage() + "\n");

            }
            msgs.clear();
        });


    }

    public boolean isFocused() {
        return chatField.isFocused();
    }
}
