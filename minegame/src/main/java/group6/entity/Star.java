package group6.entity;

import group6.main.CollisionHandler;
import group6.map.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Star class that acts as a regular reward
 */
public class Star extends Reward {

    private Map map;
    private String id;

    /**
     * Star constructor
     * @param map the map 
     * @param id the star's ID for image processing
     * @param x the star's x coordinate
     * @param y the star's y coordinate
     */
    public Star(Map map, String id, int x, int y){
        this.map = map;
        this.id = id;
        setMapPos(x, y);
        setHitBox(0, 0, 48, 48);
        setHitBoxDefaultX(0);
        setHitBoxDefaultY(0);
        setCollected(false);
        setCollectedCounter(0);
        setAmount(500);
        loadImages("star");
    }

    /**
     * Getter method for the star's id
     * @return the star's id
     */
    public String getId(){
        return id;
    }

    @Override
    public void loadImages(String className){
        super.loadImages(className);
        loadImage("top", "/sprites/"  + className + "/" + className + "_top.png");
        loadImage("left", "/sprites/" + className + "/" + className + "_left.png");
        loadImage("right", "/sprites/" + className + "/" + className + "_right.png");
        loadImage("bottom_left", "/sprites/" + className + "/" + className + "_bottom_left.png");
        loadImage("bottom_right", "/sprites/" + className + "/" + className + "_bottom_right.png");
        loadImage("collected", "/sprites/" + className + "/" + className + "_collected.png");
        loadImage("blank", "/sprites/" + className + "/" + className + "_blank.png");
    }

    /**
     * Draws the star (refactor later)
     * @param g temp
     * @param e temp
     */
    public void draw(Graphics2D g, Entity e){
        BufferedImage image = null;
        
        if (!this.getCollected()){
            switch(id){
                case "top":
                    image = getImage("top");
                    break;
                case "left":
                    image = getImage("left");
                    break;
                case "right":
                    image = getImage("right");
                    break;
                case "bottom_left":
                    image = getImage("bottom_left");
                    break;
                case "bottom_right":
                    image = getImage("bottom_right");
                    break;
            }
        }else{
            if (getCollectedCounter() < 5){
                setCollectedCounter(getCollectedCounter() + 1);
                image = getImage("collected");
            }else{
                image = getImage("blank");
            }
        }

        //calculating the position
        int mapX = getMapPos().getX() * map.getCellSize();
        int mapY = getMapPos().getY() * map.getCellSize();
        int screenX = mapX - e.getMapPos().getX() + e.getScreenPos().getX();
        int screenY = mapY - e.getMapPos().getY() + e.getScreenPos().getY();

        g.drawImage(image, screenX, screenY, map.getCellSize(), map.getCellSize(), null);

        if (this.getCollected()){
            switch(id){
                case "top":
                    g.drawImage(getImage("top"), 649, 18, 48, 48, null);
                    break;
                case "left":
                    g.drawImage(getImage("left"), 637, 30, 48, 48, null);
                    break;
                case "right":
                    g.drawImage(getImage("right"), 660, 30, 48, 48, null);
                    break;
                case "bottom_left":
                    g.drawImage(getImage("bottom_left"), 640, 42, 48, 48, null);
                    break;
                case "bottom_right":
                    g.drawImage(getImage("bottom_right"), 657, 42, 48, 48, null);
                    break;
            }
        }

        g.drawImage(getImage("star_shell"), 650, 30, 48, 48, null);
        
    }

    //checking for collision and updating upon collision
    public void update(CollisionHandler collisionHandler, MainCharacter mc){
        if (collisionHandler.collidingWithReward(mc, this, map)){
            updateScore(mc);
        }
    }

    @Override
    public void updateScore(MainCharacter mc) {
        super.updateScore(mc);
        mc.setStarsCollected(mc.getStarsCollected() + 1);
    }
}
