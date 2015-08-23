package org.jge.map;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class MapEditor extends Application {
    private static MapEditor INSTANCE;

    public static void main(String[] args) {
        launch(args);
    }

    public static MapEditor getInstance() {
        return INSTANCE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public MapEditor() {
        INSTANCE = this;
    }
}
