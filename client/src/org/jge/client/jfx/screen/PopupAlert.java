package org.jge.client.jfx.screen;

import javafx.scene.Scene;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PopupAlert extends Screen {
    private final String text;

    public PopupAlert(String text) {
        this.text = text;
    }

    @Override
    public Scene buildScreen() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
