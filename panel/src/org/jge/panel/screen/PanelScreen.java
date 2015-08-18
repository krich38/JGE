package org.jge.panel.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelScreen extends Screen {
    @Override
    protected Scene buildScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/xml/panel.fxml"));
            Scene scene = new Scene(root);
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
