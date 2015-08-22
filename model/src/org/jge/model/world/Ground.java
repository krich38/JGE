package org.jge.model.world;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Ground extends RenderableEntity {
    private final Canvas canvas;
    private Player player;
    Image lol = new Image("Assets/Tiles/1.png");
    private WorldMap map;

    public Ground(Player player) {
        this.player = player;
        canvas = new Canvas();

        canvas.setHeight(650);
        canvas.setWidth(900);


    }

    @Override
    public void render(GraphicsContext g) {

        Waypoint waypoint = player.getWaypoint();

        int xMinTile = (int) player.getWaypoint().getX() - 30;
        int xMaxTile = (int) player.getWaypoint().getX() + 30;
        int yMinTile = (int) player.getWaypoint().getY() - 20;
        int yMaxTile = (int) player.getWaypoint().getY() + 20;


        for (int i = 0; i < 2; ++i) {
            if (i >= xMinTile && i <= xMaxTile) {
                for (int j = 0; j < 2; ++j) {

                    if (j >= yMinTile && j <= yMaxTile) {
                        // draw the map here
                        int x_coor = 0;
                        int y_coor = 0;
                        for (int x = 0; x < (canvas.getWidth() / 32); x++) {
                            for (int y = 0; y < (canvas.getHeight() / 32) + 10; y++) {
                                g.drawImage(map.tileAt(x, y).getSprite(), x_coor, y_coor);
                                x_coor = x_coor + 32;

                                // System.out.println(x_coor + " : " + y_coor);
                            }
                            x_coor = 0;
                            y_coor = y_coor + 32;
                        }
                    }
                }
            }
        }
        setRendered(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void process(long delta) {
        canvas.setTranslateX(player.getTranslateX());
        canvas.setTranslateY(player.getTranslateY());
        setTranslateX(player.getTranslateX());
        setTranslateY(player.getTranslateY());
        setLastTranslateY(player.getTranslateY());
        setLastTranslateX(player.getTranslateX());
    }

    public void setMap(WorldMap map) {
        this.map = map;
    }
}
