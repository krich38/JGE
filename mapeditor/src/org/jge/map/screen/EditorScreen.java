package org.jge.map.screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.jge.map.MapEditor;


/**
 * @author Kyle Richards
 * @version 1.0
 */
public class EditorScreen extends Screen {
    private static final int PADDING = 7;
    private final MapEditor editor;

    public EditorScreen() {
        this.editor = getEditor();
    }
    @Override
    protected Scene buildScene() {
        MenuBar menu = new MenuBar();

        Menu file = new Menu("File");
        MenuItem close = new MenuItem("Close");
        file.getItems().addAll(close);

        Menu edit = new Menu("Edit");
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        edit.getItems().addAll(undo, redo);

        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        help.getItems().addAll(about);

        TabPane tilesTabPane = new TabPane();
        tilesTabPane.setLayoutX(PADDING);
        tilesTabPane.setLayoutY(80);
        tilesTabPane.setPrefWidth(200);
        tilesTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tiles = new Tab("Tiles");
        AnchorPane content = new AnchorPane();
        return null;
    }
}
