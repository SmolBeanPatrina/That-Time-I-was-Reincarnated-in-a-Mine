package group6.entity;

import group6.main.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.awt.Rectangle;

/**
 * Generic entity class
 */
public abstract class Entity {
    private Position screenPos = new Position();
    private Position mapPos = new Position();
    private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    private Rectangle hitBox = new Rectangle();
    private int hitBoxDefaultX;
    private int hitBoxDefaultY;

    /**
     * Empty entity constructor
     */
    public Entity(){}

    /**
     * Getter method for entity's absolute map position
     * @return the entity's map position
     */
    public Position getMapPos(){
        return mapPos;
    }

    /**
     * Setter method for entity's absolute map position
     * @param x the entity's absolute x coord
     * @param y the entity's absolute y coord
     */
    public void setMapPos(int x, int y){
        mapPos.setX(x);
        mapPos.setY(y);
    }

    /**
     * Getter method for entity's relative screen position
     * @return the entity's screen position
     */
    public Position getScreenPos(){
        return screenPos;
    }

    /**
     * Setter method for entity's relative screen position
     * @param x the entity's relative x coord
     * @param y the entity's relative y coord
     */
    public void setScreenPos(int x, int y){
        screenPos.setX(x);
        screenPos.setY(y);
    }

    /**
     * Setter method for hitBox's x coord
     * @param x the x coord
     */
    public void setHitBoxDefaultX(int x){
        hitBoxDefaultX = x;
    }

    /**
     * Getter method for hitBox's x coord
     * @return the entity's hit box's x coord
     */
    public int getHitBoxDefaultX(){
        return hitBoxDefaultX;
    }

    /**
     * Setter method for hitBox's y coord
     * @param y the y coord
     */
    public void setHitBoxDefaultY(int y){
        hitBoxDefaultY = y;
    }

    /**
     * Getter method for hitBox's y coord
     * @return the entity's hit box's y coord
     */
    public int getHitBoxDefaultY(){
        return hitBoxDefaultY;
    }

    /**
     * Getter method for entity's image given a key
     * @param key the key to the images HashMap
     * @return the image of the respective key
     */
    public BufferedImage getImage(String key) {
        return images.get(key);
    }

    /**
     * Loads an image frame of the entity given a key and resource path
     * @param key the key for the images HashMap
     * @param path the resource path to the image
     */
    public void loadImage(String key, String path){
        try{
            images.put(key, ImageIO.read(getClass().getResourceAsStream(path)));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for hitBox
     * @return the entity's hit box
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * Setter method for hitBox
     * @param x the x coordinate of the hit box relative to the entity's coordinates as the origin
     * @param y the y coordinate of the hit box relative to the entity's coordinates as the origin
     * @param width the width of the hit box
     * @param height the height of the hit box
     */
    public void setHitBox(int x, int y, int width, int height) {
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = width;
        hitBox.height = height;
    }

}
