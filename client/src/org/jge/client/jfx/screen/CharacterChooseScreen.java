package org.jge.client.jfx.screen;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.jge.client.listener.GameClientListener;
import org.jge.model.CharacterSprite;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class CharacterChooseScreen extends Screen {
    @Override
    public Scene buildScreen() {
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

        // Still working on this, when a character is selected it will pass the
        // character selection onto Player class to set sprite to load
        character1.setOnAction(event -> {
            getClient().setListener(new GameClientListener());
            changeScreen(new GameScreen());
        });
//
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
        Scene scene = new Scene(bp, 405, 270);

        scene.getStylesheets().add(getClass().getResource("/graphics/login.css").toExternalForm());
        return scene;
    }
}
