package org.jge.panel.screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.jge.panel.Panel;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class BanPlayerInputScreen extends Screen {
    private TextField reason;
    private CheckBox anonymous;
    private ChoiceBox<Integer> months;
    private ChoiceBox<Integer> days;
    private ChoiceBox<Integer> hours;
    private ChoiceBox<Integer> minutes;
    private CheckBox indefinite;
    private static final int PADDING =10;


    @Override
    protected Scene buildScene() {Label header = new Label("Ban Player");
        header.setLayoutY(7);
        header.setLayoutX(147);



        Label playerLabel = new Label("Player");
        playerLabel.setLayoutX(PADDING);
        playerLabel.setLayoutY(30);

        ChoiceBox<String> players = new ChoiceBox<>();
        //players.setItems(this.players);
        players.setItems(((PanelScreen)Panel.getInstance().getScreen()).getPlayerList().getItems());
                players.setLayoutX(60);
        players.setLayoutY(30);
        players.setPrefWidth(150);
        reason = new TextField();
        reason.setLayoutX(60);
        reason.setLayoutY(60);
        reason.setPrefWidth(265);

        anonymous = new CheckBox("Anonymous");
        anonymous.setLayoutX(240);
        anonymous.setLayoutY(30);

        Label reasonLabel = new Label("Reason");
        reasonLabel.setLayoutX(PADDING);
        reasonLabel.setLayoutY(60);

        Label length = new Label("Length");
        length.setLayoutX(PADDING);
        length.setLayoutY(95);

        months = new ChoiceBox<>();
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for(int i = 1; i < 13; i++) {
            options.add(i);
        }
        months.setItems(options);
        months.setLayoutX(60);
        months.setLayoutY(90);
        months.setPrefWidth(41);
        Label monthsLabel = new Label("Months");
        monthsLabel.setLayoutX(60);
        monthsLabel.setLayoutY(115);

        days = new ChoiceBox<Integer>();
        options = FXCollections.observableArrayList();
        for(int i = 1; i < 31; i++) {
            options.add(i);
        }
        days.setItems(options);
        days.setLayoutX(110);
        days.setLayoutY(90);
        days.setPrefWidth(41);
        Label dayLabel = new Label("Days");
        dayLabel.setLayoutX(117);
        dayLabel.setLayoutY(115);

        hours = new ChoiceBox<Integer>();
        options = FXCollections.observableArrayList();
        for(int i = 1; i < 25; i++) {
            options.add(i);
        }
        hours.setItems(options);
        hours.setLayoutX(160);
        hours.setLayoutY(90);
        hours.setPrefWidth(41);
        Label hoursLabel = new Label("Hours");
        hoursLabel.setLayoutX(165);
        hoursLabel.setLayoutY(115);

        minutes = new ChoiceBox<Integer>();
options = FXCollections.observableArrayList();
        for(int i = 1; i < 61; i++) {
            options.add(i);
        }
        minutes.setItems(options);
        minutes.setLayoutX(210);
        minutes.setLayoutY(90);
        minutes.setPrefWidth(41);
        Label minutesLabel = new Label("Minutes");
        minutesLabel.setLayoutX(210);
        minutesLabel.setLayoutY(115);

        indefinite = new CheckBox("Indefinite");
        indefinite.setLayoutX(256);
        indefinite.setLayoutY(95);

        Button send = new Button("Ban");
        send.setLayoutY(135);
        send.setLayoutX(155);
        Pane pane = new Pane(playerLabel, reason, anonymous, reasonLabel, length, this.months, monthsLabel, days, dayLabel, hours, hoursLabel, minutes, minutesLabel,indefinite, header, players, send);

        Scene scene = new Scene(pane, 345, 165);
        return scene;
    }
}
