package org.jge.panel.screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


/**
 * @author Kyle Richards
 * @version 1.0
 */
public class KickPlayerInput extends Screen {
    private ListView<String> playerList;

    public KickPlayerInput(ListView<String> playerList) {

        this.playerList = playerList;
    }

    @Override
    protected Scene buildScene() {


        Label players = new Label("Player");
        players.setLayoutX(13);
        players.setLayoutY(15);

        TextField reason = new TextField();
        reason.setLayoutX(60);
        reason.setLayoutY(50);
        reason.setPrefWidth(220);

        CheckBox anonymous = new CheckBox("Anonymous");
        anonymous.setLayoutX(241);
        anonymous.setLayoutY(15);

        Label reasonLabel = new Label("Reason");
        reasonLabel.setLayoutX(10);
        reasonLabel.setLayoutY(52);

        ChoiceBox<String> playersList = new ChoiceBox<>();
        playersList.setLayoutX(60);
        playersList.setLayoutY(11);
        playersList.setPrefWidth(150);
        if(this.playerList != null) {
            playersList.setItems(this.playerList.getItems());
        }
        Button send = new Button("Send");
        send
                .setLayoutX(283);
        send.setLayoutY(50);
        Pane pane = new Pane(players,reason,anonymous,reasonLabel,playersList,send);
        Scene scene = new Scene(pane, 345, 80);
        return scene;
    }
}
