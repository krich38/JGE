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
    private double translateY, translateX, lastTranslateY, lastTranslateX;
    private boolean rendered;

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


    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public boolean requiresRendering() {
        return translateX != lastTranslateX || translateY != lastTranslateY || !rendered;
    }

    public void setLastTranslateY(double lastTranslateY) {

        this.lastTranslateY = lastTranslateY;
    }

    public void setLastTranslateX(double lastTranslateX) {

        this.lastTranslateX = lastTranslateX;

    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
}
