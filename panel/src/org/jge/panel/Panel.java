package org.jge.panel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jge.panel.screen.LoginScreen;
import org.jge.panel.screen.PanelScreen;
import org.jge.panel.screen.Screen;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Panel extends Application {
    private static Panel INSTANCE;
    private Stage primaryStage;
    private PanelEngine engine;
    private PanelClient client;

    public static Panel getInstance() {
        return INSTANCE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        engine = new PanelEngine();
        engine.start();
        client = new PanelClient();
        changeScreen(new LoginScreen());
        primaryStage.show();
        primaryStage.setOnCloseRequest((event) -> System.exit(0));

    }

    public void changeScreen(Screen screen) {
        Platform.runLater(() -> {
            primaryStage.setScene(screen.getScene());
            primaryStage.centerOnScreen();
        });

    }

    public static void main(String[] args) {

        launch(args);
    }

    public Panel() {
        INSTANCE = this;
    }

    public PanelEngine getEngine() {
        return engine;
    }

    public void refresh() {

    }

    public PanelClient getClient() {
        return client;
    }

}
