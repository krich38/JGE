package org.jge.model.world;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Tile extends RenderableEntity {
    private List<Entity> entities;
    private int tileId;
    private static final Image image = new Image("Assets/1.png");

    public Tile(int tileId, Waypoint waypoint) {
        this.tileId = tileId;
        setSprite(Tile.image);
        super.waypoint = waypoint;
        super.tile = this;
        entities = new ArrayList<>(5); // 5 init capacity?

    }



    public boolean isOccupied() {
        return !entities.isEmpty();
    }


    @Override
    public void render(GraphicsContext g) {

    }

    @Override
    public void process(long delta) {

    }
}
