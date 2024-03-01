package group6.entity;

import group6.main.CollisionHandler;

import java.awt.Graphics2D;

/**
 * Generic class for enemies that move
 */
public abstract class MovingEnemy extends MovingEntity {

    /**
     * Empty constructor
     */
    public MovingEnemy() {}

    /**
     * Main draw method.
     * @param g Needed to check cell collision
     * @param e Usually MainCharacter
     */
    public abstract void draw(Graphics2D g, Entity e);

    /**
     * Update movement relative to player's position
     * @param collisionHandler the collision handler to check if they are colliding with the player
     * @param mc the main character to get their position
     */
    public abstract void update(CollisionHandler collisionHandler, MainCharacter mc);
    
    // public void configMovement() {
        
    // }
}
