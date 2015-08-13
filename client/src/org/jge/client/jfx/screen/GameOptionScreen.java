package org.jge.client.jfx.screen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.jge.client.GameClient;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameOptionScreen extends Screen {


    @Override
    public Scene buildScreen() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(30, 65, 55, 50));
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 0, 0, 0));


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Button btnSave = new Button();

        Button btnNew = new Button();

        gridPane.add(btnSave, 1, 0);
        gridPane.add(btnNew, 1, 1);
        // Reflection r = new Reflection();
        // r.setFraction(0.7f);
        // gridPane.setEffect(r);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        // text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        // text.setEffect(dropShadow);

        GridPane login = new GridPane();
        login.setPadding(new Insets(60, 0, 0, 15));
        login.setHgap(0);
        login.setVgap(0);
        Image loadbuttongraphic = new Image("graphics/load.png");
        Image startNewGameBtnGraphic = new Image("graphics/new_game.png");
        Image loadscreen = new Image("graphics/load_screen.png");


        ImageView loadgraphicview = new ImageView(loadbuttongraphic);
        ImageView startNewGraphic = new ImageView(startNewGameBtnGraphic);
        ImageView imageView = new ImageView();
        imageView.setImage(loadscreen);

        btnSave.setPadding(new Insets(0, 0, 0, 0));
        btnNew.setPadding(new Insets(0, 0, 0, 0));
        btnSave.setGraphic(loadgraphicview);
        btnNew.setGraphic(startNewGraphic);
        //btnNew.setOnMousePressed(btnEvent);
        //btnSave.setOnMousePressed(btnEvent);

        login.add(imageView, 0, 0);

        //  hb.getChildren().add(text);
        bp.setId("bp");
        gridPane.setId("root");
        btnSave.setId("btnSave");
        btnNew.setId("btnNew");

        // This will be the start to load a previous saved game for a user
        // GroupA developing save feature
        btnSave.setOnAction(event -> {
            //Platform.runLater(() -> initGameScreen(primaryStage, 0));
        });

        // This will be the event that loads the character selection screen
        // Still a working progress
        btnNew.setOnAction(event -> {
            changeScreen(new CharacterChooseScreen());
        });

        // bp.setTop(hb);
        bp.setCenter(gridPane);
        bp.setTop(login);
        Scene scene = new Scene(bp, 405, 270);

        scene.getStylesheets().add(getClass().getResource("/graphics/login.css").toExternalForm());
        return scene;
    }
}
