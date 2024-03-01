package group6.entity;

import group6.main.CollisionHandler;
import group6.map.Map;
import group6.main.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Trap class that is a basic punishment that also stuns the player
 */
public class Trap extends Punishment {
    private Map map;
    private boolean isActive;

    /**
     * Trap constructor
     * @param map the map
     * @param pos the position of the trap
     */
    public Trap(Map map, Position pos){
        this.map = map;
        setPenalty(500);
        setMapPos(pos.getX(), pos.getY());
        setHitBox(0, 0, 48, 48);
        setHitBoxDefaultX(0);
        setHitBoxDefaultY(0);
        loadImages("trap");
    }

    /**
     * Loads images for the trap
     * @param className the name of trap
     */
    public void loadImages(String className){
        loadImage("open", "/sprites/" + className + "/" + className + "_open.png");
        loadImage("closed", "/sprites/" + className + "/" + className + "_closed.png");
    }

    @Override
    public void update(CollisionHandler collisionHandler, MainCharacter mc){
        if (collisionHandler.collidingWithPunishment(mc, this, map) && this.isActive == false){
            mc.isStunned = true;
            this.isActive = true;
            mc.setScore(mc.getScore() - getPenalty());
        }
    }

    @Override
    public void draw(Graphics2D g, Entity e) {
        BufferedImage image = null;
        if (isActive){
            image = getImage("closed");
        } else {
            image = getImage("open");
        }

        int mapX = getMapPos().getX() * map.getCellSize();
        int mapY = getMapPos().getY() * map.getCellSize();
        int screenX = mapX - e.getMapPos().getX() + e.getScreenPos().getX();
        int screenY = mapY - e.getMapPos().getY() + e.getScreenPos().getY();

        g.drawImage(image, screenX, screenY, map.getCellSize(), map.getCellSize(), null);
    }
}
