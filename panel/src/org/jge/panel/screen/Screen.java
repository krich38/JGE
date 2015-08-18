package org.jge.panel.screen;

import javafx.scene.Scene;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {
    private final Scene scene;

    public Screen() {
        scene = buildScene();
    }

    protected abstract Scene buildScene();

    public Scene getScene() {
        return scene;
    }
}
