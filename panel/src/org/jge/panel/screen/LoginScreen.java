package org.jge.panel.screen;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.jge.model.User;
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

        Label header = new Label("JGE Control Panel");
        header.setLayoutX(85);
        header.setLayoutY(5);

        loginStatus = new Label("Please login!");
        loginStatus.setLayoutX(100);
        loginStatus.setLayoutY(115);
        Label user = new Label("Username:");
        user.setLayoutX(15);
        user.setLayoutY(30);

        Label pass = new Label("Password:");
        pass.setLayoutX(15);
        pass.setLayoutY(60);


        TextField username = new TextField();
        username.setLayoutX(75);
        username.setLayoutY(30);
        username.setPrefHeight(25);
        username.setPrefWidth(175);

        PasswordField password = new PasswordField();
        password.setLayoutX(75);
        password.setLayoutY(60);
        password.setPrefHeight(25);
        password.setPrefWidth(175);
        Button login = new Button("Login");
        login.setLayoutX(110);
        login.setLayoutY(90);
        login.setOnAction((event -> {
            User userConnect = new User(username.getText(), password.getText());
            try {
                Panel.getInstance().getClient().connect(userConnect);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        Pane pane = new Pane(header, user, pass, login, username, password, loginStatus);
        Scene scene = new Scene(pane, 265, 145);
        return scene;
    }

    public LoginScreen() {
        Panel.getInstance().getClient().setListener(new LoginScreenListener(this));
        setTitle("JGE Panel Login");
    }

    public void updateStatus(String text) {
        Platform.runLater(() -> {
            loginStatus.setText(text);
        });

    }
}
