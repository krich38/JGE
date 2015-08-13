package org.jge.client.jfx.screen;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        loginStatus = new Label("Please login!");
        loginStatus.setWrapText(true);
        Button btnLogin = new Button("Login");
        Button btnRego = new Button("Register");
        btnRego.setMinWidth(80);

        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(btnRego, 2, 0);
        gridPane.add(loginStatus, 1, 2);

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


        Scene scene = new Scene(bp, 405, 270);

        btnLogin.setOnAction(event -> onLoginPress(txtUserName.getText(), pf.getText()));
        btnRego.setOnAction(event -> onRegisterPress(txtUserName.getText(), pf.getText()));
        scene.getStylesheets().add(getClass().getResource("/graphics/login.css").toExternalForm());
        return scene;
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
        Platform.runLater(() -> loginStatus.setText(text));
    }

}
