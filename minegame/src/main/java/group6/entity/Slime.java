package group6.entity;

import group6.main.CollisionHandler;
import group6.map.Map;
import group6.main.Position;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Slime class that is a basic moving enemy 
 */
public class Slime extends MovingEnemy {
    private Map map;

    /**
     * Slime construtor
     * @param map the map for interaction with cells
     * @param pos the position of the slime
     */
    public Slime(Map map, Position pos){
        this.map = map;
        setMapPos(pos.getX()*map.getCellSize(), pos.getY()*map.getCellSize()); 
        setScreenPos(0, 0);
        setSpeed(4); //Sets the slime speed (constant)
        setHitBox(8, 16, 32,24);
        loadImages("slime");
    }

    @Override
    public void draw(Graphics2D g, Entity e){
        BufferedImage image = null;

        int screenX = getMapPos().getX() - e.getMapPos().getX() + e.getScreenPos().getX();
        int screenY = getMapPos().getY() - e.getMapPos().getY() + e.getScreenPos().getY();
        setScreenPos(screenX, screenY);

        
        // //Checks if slime is in frame of camera
        // if (getMapPos().getX() + map.getCellSize() > mc.getMapPos().getX() + mc.getScreenPos().getX() &&
        //         getMapPos().getX() - map.getCellSize() < mc.getMapPos().getX() + mc.getScreenPos().getX() &&
        //         getMapPos().getY() + map.getCellSize() > mc.getMapPos().getY() + mc.getScreenPos().getY() &&
        //         getMapPos().getY() - map.getCellSize() < mc.getMapPos().getY() + mc.getScreenPos().getY()){
        switch(getDirection()) {
            case "up":
            case "down":
                if (getSpriteSwitch()) {
                    image = getImage("frame1");
                } else {
                    image = getImage("frame2");
                }
                break;
            case "left":
            case "right":
                if (getSpriteSwitch()) {
                    image = getImage("frame2");
                } else {
                    image = getImage("frame3");
                }
                break;
        }
        g.drawImage(image, screenX, screenY, map.getCellSize(), map.getCellSize(), null);
        // }

        if (getSpriteSwitchCounter() >= 30){
            setSpriteSwitch(!getSpriteSwitch());
            setSpriteSwitchCounter(0);
        }

        setSpriteSwitchCounter(getSpriteSwitchCounter() + 1);
    }

    @Override
    public void loadImages(String className) {
        loadImage("frame1", "/sprites/" + className + "/" + className + "_frame1.png");
        loadImage("frame2", "/sprites/" + className + "/" + className + "_frame2.png");
        loadImage("frame3", "/sprites/" + className + "/" + className + "_frame3.png");
    }

    /**
     * Updates slime position based on player's position
     */
    @Override
    public void update(CollisionHandler collisionHandler, MainCharacter mc){
        // Get positions and speed
        int slimeX = getMapPos().getX();
        int slimeY = getMapPos().getY();
        int slimeSpeed = getSpeed();
        
        int mcX = mc.getMapPos().getX();
        int mcY = mc.getMapPos().getY();

        // Calculate displacements between player and slime
        int xDiff = slimeX - mcX;
        int yDiff = slimeY - mcY;

        if (xDiff > 0) {
            setDirection("left");
        } else if (xDiff < 0) {
            setDirection("right");
        } else if (yDiff > 0){
            setDirection("up");
        } else {
            setDirection("down");
        }
        
        collisionHandler.collidingWithEnemy(mc, this, map);

        if (!collisionHandler.colldingWithCell(this, map)) { // Slime is not colliding
            // Update slime movement
            switch(getDirection()) {
                case "up":
                    setMapPos(slimeX, slimeY - slimeSpeed);
                    break;
                case "down":
                    setMapPos(slimeX, slimeY + slimeSpeed);
                    break;
                case "left":
                    setMapPos(slimeX - slimeSpeed, slimeY);
                    break;
                case "right":
                    setMapPos(slimeX + slimeSpeed, slimeY);
                    break;
            }
        }

        if (getSpriteSwitchCounter()%20 == 0){
            setSpriteSwitch(!getSpriteSwitch());
            setSpriteSwitchCounter(0);
        }

        setSpriteSwitchCounter(getSpriteSwitchCounter() + 1);
        
    }

}
