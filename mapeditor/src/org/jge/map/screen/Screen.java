package org.jge.map.screen;

import javafx.scene.Scene;
import org.jge.map.MapEditor;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Screen {
    private final Scene scene;
    private final MapEditor editor;

    public Screen() {

        scene = buildScene();
        editor = MapEditor.getInstance();
    }

    protected abstract Scene buildScene();

    public Scene getScene() {
        return scene;
    }

    protected MapEditor getEditor() {
        return editor;
    }
}
