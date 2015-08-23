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
    private boolean footFlag;
    private double distFlag;
    private int playerType;


    public Player(Id<Entity> id) {

        super.id = id;
        node = imageView;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void render(GraphicsContext g) {

    }


    public void loadSprites(int playerType, double w, double h, int directionFlag) {

        this.playerType = playerType;
        Image img = new Image("Characters/" + characterList[playerType] + ".png");
        imageView.setImage(img);
        imageView.setLayoutX(((w - imageView.getFitWidth()) / 2) - 32);
        imageView.setLayoutY(((h - imageView.getFitHeight()) / 2) - 64);

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


        switch (directionFlag) {
            case 1:
                setLastStatus(EntityStatus.MOVEMENT_UP);

                break;
            case 2:
                setLastStatus(EntityStatus.MOVEMENT_RIGHT);
                break;
            case 3:
                setLastStatus(EntityStatus.MOVEMENT_DOWN);
                break;
            default:
                setLastStatus(EntityStatus.MOVEMENT_LEFT);
                break;
        }

        stoppedMoving(getLastStatus());

    }


    @Override
    public void process(long delta) {
        stepCounter++;//increment the timer.
        if (stepCounter >= 4) {
            spriteFlag = true;
            stepCounter = 0; //reset the counter

        }
        //if (stepCounter >= 4) {

        //This changes the sprite with movement.
        EntityStatus status = getStatus();
        double distMoved = (delta * MOV_PER_MILLI);
        //System.out.println(getWaypoint());
        switch (status) {

            case MOVEMENT_UP:
                if (spriteFlag) {

                    if (footFlag) {
                        imageView.setViewport(spriteArray[11]);

                    } else {
                        imageView.setViewport(spriteArray[9]);

                    }
                }
                setTranslateY(getTranslateY() + distMoved);
                distFlag += distMoved;
                if (distFlag > 32) {
                    getWaypoint().setY(getWaypoint().getY() + 1);
                    distFlag -= 32;
                }
                break;
            case MOVEMENT_LEFT:
                if (spriteFlag) {
                    if (footFlag) { //This is the left foot
                        imageView.setViewport(spriteArray[5]);

                    } else {      //This is the right foot
                        imageView.setViewport(spriteArray[3]);

                    }
                }
                setTranslateX(getTranslateX() + (delta * MOV_PER_MILLI));
                distFlag += distMoved;
                if (distFlag > 32) {
                    getWaypoint().setX(getWaypoint().getX() - 1);
                    distFlag -= 32;
                }
                break;
            case MOVEMENT_RIGHT:
                if (spriteFlag) {
                    if (footFlag) {
                        imageView.setViewport(spriteArray[6]);

                    } else {
                        imageView.setViewport(spriteArray[8]);

                    }
                }
                setTranslateX(getTranslateX() - (delta * MOV_PER_MILLI));
                distFlag += distMoved;

                if (distFlag > 32) {
                    getWaypoint().setX(getWaypoint().getX() + 1);
                    distFlag -= 32;
                }
                break;

            case MOVEMENT_DOWN:
                if (spriteFlag) {
                    if (footFlag) {
                        imageView.setViewport(spriteArray[2]);

                    } else {
                        imageView.setViewport(spriteArray[0]);

                    }
                }
                setTranslateY(getTranslateY() - (delta * MOV_PER_MILLI));
                distFlag += distMoved;

                if (distFlag > 32) {
                    getWaypoint().setY(getWaypoint().getY() - 1);
                    distFlag -= 32;
                }
                break;
            // flag to stop movement
            case STATIONARY:
                stoppedMoving(getLastStatus());


        }
        //System.out.println(spriteFlag);
        if (spriteFlag) {
            spriteFlag = false;
            footFlag = !footFlag; //Alternate feet.
        }

        setLastStatus(status); //Track the last direction


        //}

    }

    private void stoppedMoving(EntityStatus status) {
        switch (status) {
            case MOVEMENT_UP:
                imageView.setViewport(spriteArray[10]);
                setStatus(EntityStatus.FACING_UP);
                setDirectionFlag(FacingDirection.NORTH);
                break;
            case MOVEMENT_LEFT:
                imageView.setViewport(spriteArray[4]);
                setStatus(EntityStatus.FACING_LEFT);
                setDirectionFlag(FacingDirection.WEST);
                break;
            case MOVEMENT_RIGHT:
                imageView.setViewport(spriteArray[7]);
                setStatus(EntityStatus.FACING_RIGHT);
                setDirectionFlag(FacingDirection.EAST);
                break;
            case MOVEMENT_DOWN:
                imageView.setViewport(spriteArray[1]);
                setStatus(EntityStatus.FACING_DOWN);
                setDirectionFlag(FacingDirection.SOUTH);
                break;
        }
    }

    public int getPlayerType() {
        return playerType;
    }

    public User getUser() {
        return user;
    }


}


