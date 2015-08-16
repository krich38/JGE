package org.jge.model.world;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Ground extends RenderableEntity {
    private final Canvas canvas;
    private Player player;

    public Ground(Player player) {
        this.player = player;
        canvas = new Canvas();

        canvas.setHeight(650);
        canvas.setWidth(900);



    }
    @Override
    public void render(GraphicsContext g) {
        System.out.println("rendering");
        setLastTranslateY(getTranslateY());
        setLastTranslateX(getTranslateX());
    }

    @Override
    public void process(long delta) {
        setTranslateX(player.getTranslateX());
        setTranslateY(player.getTranslateY());


    }

    public Canvas getCanvas() {
        return canvas;
    }

}
