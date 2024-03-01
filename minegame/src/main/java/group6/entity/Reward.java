package group6.entity;

import group6.main.CollisionHandler;

/**
 * Generic reward class for the player to collect
 */
public class Reward extends Entity {
    private int amount;
    private boolean collected = false;
    private int collectedCounter; //used for animation timing

    /**
     * Empty constructor
     */
    public Reward() {}

    /**
     * Setter method for the reward amount
     * @param amount the reward amount
     */
    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * Getter method for the reward amount
     * @return the reward amount
     */
    public int getAmount(){
        return amount;
    }

    /**
     * Setter method for collected
     * @param bool the value of collected
     */
    public void setCollected(boolean bool){
        this.collected = bool;
    }

    /**
     * Getter method for collected
     * @return the value of collected
     */
    public boolean getCollected(){
        return collected;
    }

    /**
     * Setter method for the collected counter
     * @param c the value of collected counter
     */
    public void setCollectedCounter(int c){
        this.collectedCounter = c;
    }

    /**
     * Getter method for the collected counter
     * @return the value of collected counter
     */
    public int getCollectedCounter(){
        return collectedCounter;
    }

    /**
     * refactor later
     * @param className temp
     */
    public void loadImages(String className){
        
    }

    /**
     * refactor later 
     * @param collisionHandler temp
     * @param mc temp
     */
    public void update(CollisionHandler collisionHandler, MainCharacter mc){

    }

    /**
     * Update the player score 
     * @param mc the main character to update their score
     */
    public void updateScore(MainCharacter mc) {
        setCollected(true);
        mc.setScore(mc.getScore() + getAmount());
    }
}
