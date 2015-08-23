package org.jge.panel.screen;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jge.model.server.PlayerEncap;
import org.jge.panel.Panel;
import org.jge.panel.PanelClient;
import org.jge.panel.net.PanelScreenListener;
import org.jge.protocol.common.ChatMessage;
import org.jge.protocol.serverstatus.AdminEvent;
import org.jge.protocol.serverstatus.ServerDiagnostics;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelScreen extends Screen {
    private Label players;
    private TextArea messages;
    private Label status;
    private ListView<String> playerList;
    private final PanelClient client = Panel.getInstance().getClient();
    private List<PlayerEncap> encapList;
    private Pane pane;

    public PanelScreen() {
        client.setListener(new PanelScreenListener(this));
        client.sendRefreshRequest(true);
        setTitle("JGE Control Panel");
    }

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
        messages.setWrapText(true);

        status = new Label();
        status.setLayoutX(445);
        status.setLayoutY(255);

        playerList = new ListView<String>();
        playerList.setLayoutX(445);
        playerList.setLayoutY(64);
        playerList.setPrefHeight(185);
        playerList.setPrefWidth(145);
        playerList.setOnMouseClicked((event) -> {
            if (event.getClickCount() >= 2) {
                String name = playerList.getSelectionModel().getSelectedItem();

            }
        });

        Label admin = new Label("Administration");
        admin.setLayoutX(65);
        admin.setLayoutY(50);

        Button kickAll = new Button("Kick All");
        kickAll.setLayoutX(15);
        kickAll.setLayoutY(65);
        kickAll.setPrefWidth(75);
        kickAll.setOnAction((event) -> client.sendEvent(AdminEvent.EventType.KICK_ALL));

        Button saveAll = new Button("Save All");
        saveAll.setLayoutX(15);
        saveAll.setLayoutY(95);
        saveAll.setPrefWidth(75);
        saveAll.setOnAction((event -> client.sendEvent(AdminEvent.EventType.SAVE_ALL)));
        //saveAll.setOnAction();
        Button broadcast = new Button("Broadcast");
        broadcast.setLayoutX(15);
        broadcast.setLayoutY(130);
        broadcast.setPrefWidth(75);
        broadcast.setOnAction((event -> showWindow(new BroadcastInputScreen().getScene())));

        Button kickPlayer = new Button("Kick Player");
        kickPlayer.setLayoutX(15);
        kickPlayer.setLayoutY(165);
        kickPlayer.setPrefWidth(75);
        kickPlayer.setOnAction((event -> showWindow(new KickPlayerInput(playerList).getScene())));

        Button banList = new Button("Ban List");
        banList.setLayoutX(15);
        banList.setLayoutY(200);
        banList.setPrefWidth(75);
        banList.setOnAction(event -> showBanList());

        Button closeServer = new Button("Close Server");
        closeServer.setLayoutX(105);
        closeServer.setLayoutY(65);
        closeServer.setOnAction(event -> client.sendEvent(AdminEvent.EventType.CLOSE_SERVER));


        Button shutdown = new Button("Shutdown");
        shutdown.setLayoutX(105);
        shutdown.setLayoutY(100);
        shutdown.setPrefWidth(81);
        shutdown.setOnAction(event -> client.sendEvent(AdminEvent.EventType.SHUT_DOWN));

        Button openServer = new Button("Open Server");
        openServer.setLayoutX(105);
        openServer.setLayoutY(135);
        openServer.setOnAction(event -> client.sendEvent(AdminEvent.EventType.OPEN_SERVER));

        Button banPlayer = new Button("Ban Player");
        banPlayer.setLayoutX(105);
        banPlayer.setLayoutY(165);
        banPlayer.setPrefWidth(81);
        banPlayer.setOnAction(event -> {

            showWindow(new BanPlayerInputScreen().getScene());
        }
        );

        Button diagnostic = new Button("Diagnostics");
        diagnostic.setLayoutX(105);
        diagnostic.setLayoutY(200);
        diagnostic.setPrefWidth(81);
        diagnostic.setOnAction(event -> client.sendEvent(AdminEvent.EventType.DIAGNOSTICS));

        Button refreshPanel = new Button("Refresh");
        refreshPanel.setLayoutX(275);
        refreshPanel.setLayoutY(125);
        refreshPanel.setOnAction(event -> client.sendRefreshRequest(true));

        pane = new Pane(header, players, recent, messages, status, playerList, admin, kickAll, saveAll, broadcast, kickPlayer, banList, closeServer, shutdown, openServer, banPlayer, diagnostic, refreshPanel);
        Scene scene =
                new Scene(pane, 600, 400);
        return scene;
    }

    private void showBanPrompt() {

    }

    private void showBanList() {

    }

    private void showKickPlayer() {

    }


    public void appendMessages(List<ChatMessage> msgs) {
        for (ChatMessage m : msgs) {
            messages.appendText(m.getUser().getUsername() + ": " + m.getMessage() + "\n");
        }
    }

    public void updatePlayerList(List<PlayerEncap> playerList) {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (PlayerEncap pe : playerList) {
            names.add(pe.getUser().getUsername());
        }
        this.encapList = playerList;
        Platform.runLater(() -> {
            players.setText("Players online: " + playerList.size());
            this.playerList.setItems(names);
        });
    }

    public void updateStatus(boolean open) {

    }

    public void showDiagnostics(ServerDiagnostics sd) {
        //long freeMemory, long usedMemory, long totalMemory
        Scene scene = new DiagnosticScreen(sd.getUpTime(), sd.getFreeMemory(), sd.getUsedMemory(), sd.getTotalMemory(), sd.getExceptions()).buildScene();


        ;
        showWindow(scene);

    }

    private void showWindow(Scene scene) {
        if(Platform.isFxApplicationThread()) {
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            });
        }
    }

    public ListView<String> getPlayerList() {
        return playerList;
    }
}
