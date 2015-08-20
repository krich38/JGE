package org.jge.panel.screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.jge.panel.Panel;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class BroadcastInputScreen extends Screen {
    @Override
    protected Scene buildScene() {


        Label broadcast = new Label("Broadcast Message");
        broadcast.setLayoutX(14);
        broadcast.setLayoutY(5);

        TextField message = new TextField();
        message.setLayoutX(14);
        message.setLayoutY(26);
        message.setPrefWidth(266);

        CheckBox anonymous = new CheckBox("Anonymous");
        anonymous.setLayoutX(255);
        anonymous.setLayoutY(6);
        Button send = new Button("Send");
        send.setLayoutX(287);
        send.setLayoutY(25);
        send.setOnAction((event) -> {
            Panel.getInstance().getClient().sendBroadcast(message.getText(), anonymous.isSelected());
        });
        Pane pane = new Pane(send,broadcast,message,anonymous);
        Scene scene = new Scene(pane, 355, 65);
        return scene;


    }
}
