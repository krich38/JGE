package org.jge.panel.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelScreen extends Screen {
    private Label players;
    private TextArea messages;
    private Label status;
    private ListView playerList;

    @Override
    protected Scene buildScene() {

        Label header = new Label("JGE Control Panel");
        header.setLayoutX(255);
        header.setLayoutY(15);

        players = new Label();
        players.setLayoutX(445);
        players.setLayoutY(50);
        players.setPrefWidth(115);

        Label recent = new Label("Recent messages");
        recent.setLayoutX(15);
        recent.setLayoutY(255);

        messages = new TextArea();
        messages.setLayoutX(15);
        messages.setLayoutY(275);
        messages.setPrefHeight(115);
        messages.setPrefWidth(575);

        status = new Label();
        status.setLayoutX(445);
        status.setLayoutY(255);

        playerList = new ListView();
        playerList.setLayoutX(445);
        playerList.setLayoutY(64);
        playerList.setPrefHeight(185);
        playerList.setPrefWidth(145);

        Label admin = new Label("Administration");
        admin.setLayoutX(65);
        admin.setLayoutY(50);

        Button kickAll = new Button("Kick All");
        kickAll.setLayoutX(15);
        kickAll.setLayoutY(65);
        kickAll.setPrefWidth(75);


        Button saveAll = new Button("Save All");
        saveAll.setLayoutX(15);
        saveAll.setLayoutY(95);
saveAll.setPrefWidth(75);
        Button broadcast = new Button("Broadcast");
        broadcast.setLayoutX(15);
        broadcast.setLayoutY(130);
broadcast.setPrefWidth(75);

        Button kickPlayer = new Button("Kick Player");
        kickPlayer.setLayoutX(15);
        kickPlayer.setLayoutY(165);
        kickPlayer.setPrefWidth(75);

        Button banList = new Button("Ban List");
        banList.setLayoutX(15);
        banList.setLayoutY(200);
        banList.setPrefWidth(75);

        Button closeServer = new Button("Close Server");
        closeServer.setLayoutX(105);
        closeServer.setLayoutY(65);


        Button shutdown = new Button("Shutdown");
        shutdown.setLayoutX(105);
        shutdown.setLayoutY(100);
        shutdown.setPrefWidth(81);

        Button openServer = new Button("Open Server");
        openServer.setLayoutX(105);
        openServer.setLayoutY(135);

        Button banPlayer = new Button("Ban Player");
        banPlayer.setLayoutX(105);
        banPlayer.setLayoutY(165);
        banPlayer.setPrefWidth(81);

        Button diagnostic = new Button("Diagnostics");
        diagnostic.setLayoutX(105);
        diagnostic.setLayoutY(200);
        diagnostic.setPrefWidth(81);

        Button refreshPanel = new Button("Refresh");
        refreshPanel.setLayoutX(275);
        refreshPanel.setLayoutY(125);

        Pane pane = new Pane(header, players, recent, messages,status,playerList,admin, kickAll, saveAll, broadcast, kickPlayer, banList,closeServer,shutdown,openServer,banPlayer, diagnostic,refreshPanel);
        Scene scene =
        new Scene(pane, 600,400);
        return scene;
    }
}
