package org.jge.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class CharacterSprite {
    private int stepCounter; //holds a counter to delay sprite animation
    private double lastDirection; //The last direction the character faced
    private boolean leftFoot = true; //Used to change foot sprite

    //This is the space in time before the sprite is updated.
    private int PAUSE_SPACING = 7;

    //This will deconstruct the sprite page.
    private final int SPRITE_SIZE = 32;

    //A list of all available characters
    private String[] characterList = {"male_1", "male_2", "female_1", "mage_1",
            "mage_2", "mage_3"};

    //A list of available enemies
    private String[] enemyList = {"EnemyGoblin1", "EnemyGoblin2", "EnemyGoblin3", "EnemyGoblin4", "EnemyGoblin5"};

    //This array holds the selected characters sprites
    private Rectangle2D[] spriteArray = new Rectangle2D[12];

    //The imageView object
    private ImageView imageView = new ImageView();

    /**
     * Constructor that will allow use of character movement.
     */
    public CharacterSprite() {
        stepCounter = 0;
        lastDirection = 0.00;
    }

    /**
     * Will change the sprite to the direction they are moving.
     *
     * @param direction The direction they are facing now
     */
    public void updateCharacterSprite(double direction) {
        stepCounter++;//increment the timer.

        if (stepCounter >= PAUSE_SPACING) {
            stepCounter = 0; //reset the counter

            //This changes the sprite with movement.
            switch ((int) direction) {
                case 180:
                    if (leftFoot) { //This is the left foot
                        imageView.setViewport(spriteArray[2]);
                        break;
                    } else {      //This is the right foot
                        imageView.setViewport(spriteArray[0]);
                        break;
                    }
                case 270:
                    if (leftFoot) {
                        imageView.setViewport(spriteArray[5]);
                        break;
                    } else {
                        imageView.setViewport(spriteArray[3]);
                        break;
                    }
                case 90:
                    if (leftFoot) {
                        imageView.setViewport(spriteArray[6]);
                        break;
                    } else {
                        imageView.setViewport(spriteArray[8]);
                        break;
                    }
                case 0:
                    if (leftFoot) {
                        imageView.setViewport(spriteArray[11]);
                        break;
                    } else {
                        imageView.setViewport(spriteArray[9]);
                        break;
                    }
                case -1: {
                    //Changes sprite to standing
                    switch ((int) lastDirection) {
                        case 180:
                            imageView.setViewport(spriteArray[1]);
                            break;
                        case 270:
                            imageView.setViewport(spriteArray[4]);
                            break;
                        case 90:
                            imageView.setViewport(spriteArray[7]);
                            break;
                        case 0:
                            imageView.setViewport(spriteArray[10]);
                            break;
                    }
                }
            }
            lastDirection = direction; //Track the last direction
            leftFoot = !leftFoot; //Alternate feet.
        }
    }//end function

    /**
     * This method loads sprites from the list of characters
     * within the character list. For now it only supports one
     * kind of sprite image.
     *
     * @param i The number of the character selected to play.
     */
    public void selectCharSprite(int i) {
        //Get the image from the resources folder
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
                spriteArray[arrayPosition] = new Rectangle2D(currentX, currentY, SPRITE_SIZE, SPRITE_SIZE);
                arrayPosition++;
                currentX += SPRITE_SIZE;
            }//end width loop
            currentX = 0.0;
            currentY += SPRITE_SIZE;
        }//end height loop

        //set starting sprite
        imageView.setViewport(spriteArray[1]);
    }

    /**
     * This method loads sprites from the list of enemies
     * within the enemy list.
     * This is mostly duplicate code from previous method,need to tidy
     * up later.
     *
     * @param i The number of the enemy selected to display.
     */
    public void selectEnemySprite(int i) {
        //Get the image from the resources folder
        Image img = new Image("Enemies/" + enemyList[i] + ".png");
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
                spriteArray[arrayPosition] = new Rectangle2D(currentX, currentY, SPRITE_SIZE, SPRITE_SIZE);
                arrayPosition++;
                currentX += SPRITE_SIZE;
            }//end width loop
            currentX = 0.0;
            currentY += SPRITE_SIZE;
        }//end height loop

        //set starting sprite
        imageView.setViewport(spriteArray[1]);
    }


    /**
     * Current usage is for adding the ImageView to the stack
     * for player display.
     *
     * @return A reference to the imageView.
     */
    public ImageView getIView() {
        return imageView;
    }

    /**
     * Get the list of characters
     *
     * @return A String array of characters.
     */
    public String[] getCharacterList() {
        return characterList;
    }

    public ImageView getStandaloneCharImage(int i) {
        Image img = new Image("Characters/" + characterList[i] + ".png");
        ImageView tempIView = new ImageView();
        tempIView.setImage(img);
        //Set the image
        tempIView.setViewport(new Rectangle2D(SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE));
        return tempIView;
    }
}
