package org.jge.panel.event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jge.model.User;
import org.jge.panel.Panel;
import org.jge.panel.PanelClient;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginEventHandler {
    private final PanelClient client;
    public static Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public LoginEventHandler() {
        client = Panel.getInstance().getClient();
    }
    @FXML
    public void login(ActionEvent actionEvent) {
        User user = new User(username.getText(), password.getText());
        try {
            client.connect(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
