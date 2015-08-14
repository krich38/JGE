package org.jge.model.world;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Tile extends RenderableEntity {
    private List<Entity> entities;


    public Tile(int x, int y) {
        this(new Waypoint(x, y));
    }

    public Tile(Waypoint waypoint) {

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
