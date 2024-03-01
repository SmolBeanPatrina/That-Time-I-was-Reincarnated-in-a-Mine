package group6.cell;

import group6.entity.MainCharacter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Generic cell class to be place on the map
 */
public abstract class Cell {
    private boolean collision = false;
    private BufferedImage image;
    private String cellName;

    /**
     * Empty constructor
     */
    public Cell(){}

    /**
     * Getter method for cellName
     * @return the name of the cell
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * Setter method for cellName
     * @param cellName the name of the cell
     */
    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    /**
     * Getter method for collison
     * @return the collision of the cell
     */
    public boolean getCollision(){
        return collision;
    }

    /**
     * Setter method for collision
     * @param collision the collision of the cell
     */
    public void setCollision(boolean collision){
        this.collision = collision;
    }

    /**
     * Loads the image of the cell from the given resource path
     * @param path the resource path of the image
     */
    public void loadImage(String path){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for iamge
     * @return the image of the cell
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Checks if cell can have its collision changed. Primarily for the exit cell.
     * @param mc The main character to check their score
     * @return True if the cell can be unlocked
     */
    public abstract boolean isUnlockable(MainCharacter mc);

}
