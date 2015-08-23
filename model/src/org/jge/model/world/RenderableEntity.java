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
    private double lastTranslateY, lastTranslateX, translateX, translateY;
    private boolean rendered;

    public void setDirectionFlag(FacingDirection directionFlag) {
        this.directionFlag = directionFlag;
    }

    public enum FacingDirection {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private FacingDirection directionFlag;

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


    public boolean requiresRendering() {
        return !rendered || getTranslateX() != lastTranslateX || getTranslateY() != lastTranslateY;
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

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public FacingDirection getDirectionFlag() {
        return directionFlag;
    }
}
