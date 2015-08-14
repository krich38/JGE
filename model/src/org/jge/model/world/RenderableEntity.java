package org.jge.model.world;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class RenderableEntity extends Entity {
    protected Node node;
    private Image sprite;

    public Node getNode() {
        return node;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public abstract void render(GraphicsContext g);


}
