package org.jge.panel.event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.jge.panel.Panel;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelEventHandler {
    @FXML
    private TextArea chat;

    @FXML
    public void refreshPanel(ActionEvent event) {
        Panel.getInstance().refresh();
    }

    @FXML
    public void kickAll(ActionEvent event) {

    }

    @FXML
    public void saveAll(ActionEvent event) {

    }

    @FXML
    public void showDiagnostics(ActionEvent event) {

    }

    @FXML
    public void broadcast(ActionEvent event) {

    }

    @FXML
    public void kickPlayer(ActionEvent event) {

    }

    @FXML
    public void showBans(ActionEvent event) {

    }

    @FXML
    public void closeServer(ActionEvent event) {

    }

    @FXML
    public void shutdown(ActionEvent event) {
    }

    @FXML
    public void openServer(ActionEvent event) {
    }

    @FXML
    public void banPlayer(ActionEvent event) {

    }

}
