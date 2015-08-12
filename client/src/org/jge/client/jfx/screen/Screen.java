package org.jge.client.jfx.screen;

import javafx.scene.Scene;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {
    private final Scene scene;

    public Screen() {
        scene = buildScreen();

    }

    public abstract Scene buildScreen();

    public Scene getScene() {
        return scene;
    }
}
