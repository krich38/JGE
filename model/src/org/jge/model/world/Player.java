package org.jge.model.world;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jge.model.Id;
import org.jge.model.User;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Player extends RenderableEntity {
    private  Rectangle2D[] spriteArray= new Rectangle2D[12];
    private User user;
    private String[] characterList = {"male_1", "male_2", "female_1", "mage_1",
            "mage_2", "mage_3"};private ImageView imageView = new ImageView();
    public Player(Id<Entity> id) {
        super.id = id;
    }

    public Player(int i,  Waypoint waypoint) {

        setWaypoint(waypoint);
        Image img = new Image("Characters/" + characterList[i] + ".png");
        imageView.setImage(img);

        //Get the dimensions of the image
        double width = img.getWidth();
        double height = img.getHeight();

        //Set current height and width
        double currentX = 0.0;
        double currentY = 0.0;

        int arrayPosition = 0;

        while (currentY < height) {
            while (currentX < width) {
                spriteArray[arrayPosition] = new Rectangle2D(currentX, currentY, 32, 32);
                arrayPosition++;
                currentX += 32;
            }//end width loop
            currentX = 0.0;
            currentY += 32;
        }//end height loop
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void render(GraphicsContext g) {

    }

}
