package org.jge.panel.screen;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.jge.panel.Panel;
import org.jge.panel.event.LoginEventHandler;
import org.jge.panel.net.LoginScreenListener;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginScreen extends Screen {
    private Label loginStatus;

    @Override
    protected Scene buildScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/xml/login.fxml"));
            //      <Label fx:id="loginStatus" layoutX="98.0" layoutY="112.0" text="Please login!" />
            loginStatus = new Label("Please login!");
            loginStatus.setLayoutX(98);
            loginStatus.setLayoutY(112);
            Group g = new Group(root, loginStatus);
            Scene scene = new Scene(g);

            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginScreen() {
        Panel.getInstance().getClient().setListener(new LoginScreenListener(this));
    }

    public void updateStatus(String text) {
        Platform.runLater(() -> {
            loginStatus.setText(text);
        });

    }
}
