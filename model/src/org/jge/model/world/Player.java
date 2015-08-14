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
    private Rectangle2D[] spriteArray = new Rectangle2D[12];
    private User user;
    private String[] characterList = {"male_1", "male_2", "female_1", "mage_1",
            "mage_2", "mage_3"};
    private ImageView imageView = new ImageView();
    private int stepCounter;
    private boolean spriteFlag;
    private double translateY;

    public Player(Id<Entity> id) {

        super.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void render(GraphicsContext g) {

    }


    public void loadSprites(int i, double w, double h) {
        Image img = new Image("Characters/" + characterList[i] + ".png");
        imageView.setImage(img);
        imageView.setLayoutX((w - imageView.getFitWidth()) / 2);
        imageView.setLayoutY((h-imageView.getFitHeight())/2);

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
        imageView.setViewport(spriteArray[1]);
        node = imageView;
    }

    @Override
    public void process(long delta) {
        stepCounter++;//increment the timer.

        if (stepCounter >= 4) {
            stepCounter = 0; //reset the counter
            //This changes the sprite with movement.
            EntityStatus status = getStatus();

            switch (status) {

                case MOVEMENT_UP:

                    if (spriteFlag) {
                        imageView.setViewport(spriteArray[11]);

                    } else {
                        imageView.setViewport(spriteArray[9]);

                    }
                    setTranslateY(getTranslateY()-(delta*MOV_PER_MILLI));
                    break;
                case MOVEMENT_LEFT:
                    if (spriteFlag) { //This is the left foot
                        imageView.setViewport(spriteArray[5]);
                        break;
                    } else {      //This is the right foot
                        imageView.setViewport(spriteArray[3]);
                        break;
                    }
                case MOVEMENT_RIGHT:if (spriteFlag) {
                    imageView.setViewport(spriteArray[6]);
                    break;
                } else {
                    imageView.setViewport(spriteArray[8]);
                    break;
                }

                case MOVEMENT_DOWN:
                    if (spriteFlag) {
                        imageView.setViewport(spriteArray[2]);
                        break;
                    } else {
                        imageView.setViewport(spriteArray[0]);
                        break;
                    }
                // flag to stop movement
                case STATIONARY:
                    stoppedMoving(getLastStatus());

            }
            setLastStatus(status); //Track the last direction
            spriteFlag = !spriteFlag; //Alternate feet.
    }

}

    private void stoppedMoving(EntityStatus status) {
        switch(status) {
            case MOVEMENT_UP:
                imageView.setViewport(spriteArray[10]);
                setStatus(EntityStatus.FACING_UP);
                break;
            case MOVEMENT_LEFT:
                imageView.setViewport(spriteArray[4]);
                setStatus(EntityStatus.FACING_LEFT);
                break;
            case MOVEMENT_RIGHT:
                imageView.setViewport(spriteArray[7]);
                setStatus(EntityStatus.FACING_RIGHT);
                break;
            case MOVEMENT_DOWN:
                imageView.setViewport(spriteArray[1]);
                setStatus(EntityStatus.FACING_DOWN);
                break;
        }
    }

}


