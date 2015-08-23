package org.jge.panel.screen;

import javafx.scene.Scene;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {
    private final Scene scene;
    private String title;

    public Screen() {
        scene = buildScene();

    }

    protected abstract Scene buildScene();

    public Scene getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
