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

    private Stage stage;
    private Game game;
    private GameClient client;
    private Label loginStatus;

    public LoginScreen(Game game, GameClient client) {
        this.game = game;


        this.client = client;
        client.setListener(new LoginScreenListener(game, this));
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

        try {User user = new User(username, password);
            client.register(user);
        } catch (IOException e) {
            e.printStackTrace();
            updateStatus("Network error!");
        }
    }

    public void onLoginPress(String username, String password) {
        try {
            User user = new User(username, password);
            client.connect(user);
        } catch (IOException e) {
            e.printStackTrace();
            updateStatus("Network error!");
        }
    }

    public void updateStatus(String text) {
        Platform.runLater(() -> loginStatus.setText(text));
    }

    private Scene characterChoose() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(30, 65, 55, 50));
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 0, 0, 0));

        // Padding spaced between characters and centered within grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        // Creating buttons with character image as icon
        CharacterSprite cS = new CharacterSprite();
        //Button 1
        Button character1 = new Button();
        character1.setGraphic(cS.getStandaloneCharImage(0));
        //Button 2
        Button character2 = new Button();
        character2.setGraphic(cS.getStandaloneCharImage(1));
        //Button 3
        Button character3 = new Button();
        character3.setGraphic(cS.getStandaloneCharImage(2));
        //Button 4
        Button character4 = new Button();
        character4.setGraphic(cS.getStandaloneCharImage(3));
        //Button 5
        Button character5 = new Button();
        character5.setGraphic(cS.getStandaloneCharImage(4));
        //Button 6
        Button character6 = new Button();
        character6.setGraphic(cS.getStandaloneCharImage(5));

        gridPane.add(character1, 0, 0);
        gridPane.add(character2, 1, 0);
        gridPane.add(character3, 2, 0);
        gridPane.add(character4, 0, 1);
        gridPane.add(character5, 1, 1);
        gridPane.add(character6, 2, 1);

        character1.setOnAction(event -> {
            //game.
        });

//        character2.setOnAction(event -> {
//            Platform.runLater(() -> initGameScreen(primaryStage, 1));
//        });
//
//        character3.setOnAction(event -> {
//            Platform.runLater(() -> initGameScreen(primaryStage, 2));
//        });
//
//        character4.setOnAction(event -> {
//            Platform.runLater(() -> initGameScreen(primaryStage, 3));
//        });
//
//        character5.setOnAction(event -> {
//            Platform.runLater(() -> initGameScreen(primaryStage, 4));
//        });
//
//        character6.setOnAction(event -> {
//            Platform.runLater(() -> initGameScreen(primaryStage, 5));
//        });

        //  hb.getChildren().add(text);
        bp.setId("bp");
        gridPane.setId("rootChar");

        // bp.setTop(hb);
        bp.setCenter(gridPane);
        Scene s = new Scene(bp);

        s.getStylesheets().add(getClass().getResource("/login.css").toExternalForm());
        return s;
    }

}
