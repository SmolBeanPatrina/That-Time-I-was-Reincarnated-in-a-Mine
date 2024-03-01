package group6.entity;

import group6.main.CollisionHandler;
import group6.map.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Coin class that acts as a bonus reward
 */
public class Coin extends Reward {
    private int bonus;
    private Map map;

    /**
     * Coin constructor
     * @param bonus the bonus reward amount
     * @param map the map to spawn in
     * @param x horizontal map position
     * @param y vertical map position
     */
    public Coin(int bonus, Map map, int x, int y){
        this.bonus = bonus;
        this.map = map;
        setAmount(500);
        setMapPos(x, y);
        setHitBox(0, 0, 48, 48);
        setHitBoxDefaultX(0);
        setHitBoxDefaultY(0);
        setCollected(false);
        setCollectedCounter(0);
        loadImages("coin");
    }

    public void loadImages(String className){
        loadImage("idle", "/sprites/" + className + "/" + className + "_idle.png");
        loadImage("collected", "/sprites/" + className + "/" + className + "_collected.png");
        loadImage("blank", "/sprites/" + className + "/" + className + "_blank.png");
    }

    /**
     * Getter method for bonus amount
     * @return The bonus amount of coin
     */
    public int getBonus(){
        return bonus;
    }

    public void draw(Graphics2D g, Entity e){
        BufferedImage image = null;
        
        if (!this.getCollected()){
            image = getImage("idle");
        }else{
            if (getCollectedCounter() < 5){
                setCollectedCounter(getCollectedCounter() + 1);
                image = getImage("collected");
            }else{
                image = getImage("blank");
            }
        }

        int mapX = getMapPos().getX() * map.getCellSize();
        int mapY = getMapPos().getY() * map.getCellSize();
        int screenX = mapX - e.getMapPos().getX() + e.getScreenPos().getX();
        int screenY = mapY - e.getMapPos().getY() + e.getScreenPos().getY();

        g.drawImage(image, screenX, screenY, map.getCellSize(), map.getCellSize(), null);
    }

    @Override
    public void update(CollisionHandler collisionHandler, MainCharacter mc){
        if (collisionHandler.collidingWithReward(mc, this, map)){
            updateScore(mc);
        }
    }

    @Override
    public void updateScore(MainCharacter mc) {
        super.updateScore(mc);
        mc.setScore(mc.getScore() + getBonus());
    }
}
