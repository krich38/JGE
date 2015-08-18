package org.jge.client.jfx.screen;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;

import org.jge.client.listener.LoginScreenListener;
import org.jge.model.CharacterSprite;
import org.jge.model.User;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginScreen extends Screen {


    private Label loginStatus;
    private Timeline timeline;

    public LoginScreen() {


        getClient().setListener(new LoginScreenListener(this));
    }

    @Override
    public Scene buildScreen() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(30, 65, 55, 50));
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 0, 0, 0));
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 0));

        gridPane.setHgap(0);
        gridPane.setVgap(10);
        Label lblUserName = new Label("Username");
        lblUserName.setFont(new Font("Bradley Hand ITC", 15));
        final TextField txtUserName = new TextField();
        txtUserName.setPrefWidth(235);
        Label lblPassword = new Label("Password");
        lblPassword.setFont(new Font("Bradley Hand ITC", 15));

        final PasswordField pf = new PasswordField();
        loginStatus = new Label();
        loginStatus.setFont(new Font("Bradley Hand ITC", 15));

        Button btnLogin = new Button("Login");
        Button btnRego = new Button("Register");
        btnRego.setMinWidth(80);
        Button serverStatus = new Button();
        serverStatus.setMinWidth(65);
        serverStatus.setId("serverStatus");
        updateServerStatus(serverStatus);
        serverStatus.setOnAction((event1 -> {
            updateServerStatus(serverStatus);
        }));

        timeline = new Timeline(new KeyFrame(Duration.minutes(1), (event) -> {
            updateServerStatus(serverStatus);
        }));
        timeline.play();
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(btnRego, 2, 0);
        gridPane.add(loginStatus, 1, 2);
        gridPane.add(serverStatus,0,2);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        GridPane login = new GridPane();
        login.setPadding(new Insets(60, 0, 0, 90));

        login.setHgap(0);
        login.setVgap(0);
        Image image = new Image("graphics/login.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        login.add(imageView, 0, 0);

        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        btnLogin.setMinWidth(btnRego.getMinWidth());
        btnRego.setId("btnRegister");

        bp.setCenter(gridPane);
        bp.setTop(login);
txtUserName.setText("blah");
        pf.setText("lol");

        Scene scene = new Scene(bp, 500, 270);

        btnLogin.setOnAction(event -> onLoginPress(txtUserName.getText(), pf.getText()));
        btnRego.setOnAction(event -> onRegisterPress(txtUserName.getText(), pf.getText()));
        scene.getStylesheets().add(getClass().getResource("/graphics/login.css").toExternalForm());
        return scene;
    }

    @Override
    public void destroy() {
        timeline.stop();

    }

    private void updateServerStatus(Button status) {

        if(getClient().serverOnline()) {
            status.setText("ONLINE");
            status.setTextFill(Color.GREEN);
            updateStatus("Server is currently ONLINE");
        } else {
            status.setText("OFFLINE");
            status.setTextFill(Color.RED);
            updateStatus("Server is currently OFFLINE");
        }
    }

    private void onRegisterPress(String username, String password) {

        try {
            User user = new User(username, password);
            getClient().register(user);
        } catch (IOException e) {
            e.printStackTrace();
            updateStatus("Network error!");
        }
    }

    public void onLoginPress(String username, String password) {
        try {
            User user = new User(username, password);
            getClient().connect(user);
        } catch (IOException e) {
            e.printStackTrace();
            updateStatus("Network error!");
        }
    }

    public void updateStatus(String text) {
        Platform.runLater(() -> loginStatus.setText("\t" +text));
    }

}
