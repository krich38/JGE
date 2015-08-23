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
    private Screen screen;

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
        primaryStage.setOnCloseRequest((event) -> destroy());
        primaryStage.show();

    }

    private void destroy() {
        client.disconnect();
        engine.stop();
        primaryStage.setScene(null);

    }

    public void changeScreen(Screen screen) {
        this.screen = screen;
        if (Platform.isFxApplicationThread()) {
            primaryStage.setScene(screen.getScene());
            if (screen.getTitle() != null) {
                primaryStage.setTitle(screen.getTitle());
            }
            primaryStage.centerOnScreen();
        } else {
            Platform.runLater(() -> {
                primaryStage.setScene(screen.getScene());
                if (screen.getTitle() != null) {
                    primaryStage.setTitle(screen.getTitle());
                }
                primaryStage.centerOnScreen();
            });
        }

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

    public Screen getScreen() {
        return screen;
    }
}
