package group6.entity;
import group6.main.CollisionHandler;

import java.awt.Graphics2D;

/**
 * Generic Punishment class that penalizes the player if they move onto it
 */
public abstract class Punishment extends Entity {
    private int penalty;

    /**
     * Empty constructor
     */
    public Punishment() {}

    /**
     * Getter method for penalty
     * @return the penalty amount
     */
    public int getPenalty(){
        return penalty;
    }

    /**
     * Setter method for penalty
     * @param penalty the penalty amount
     */
    public void setPenalty(int penalty){
        this.penalty = penalty;
    }

    /**
     * Updates the punishment
     * @param collisionHandler the collision handler to check collision with the player
     * @param mc the main character to get their position and update their score
     */
    public abstract void update(CollisionHandler collisionHandler, MainCharacter mc);

    /**
     * Draws the punishment
     * @param g the Graphics2D object for drawing
     * @param e the Entity to draw with respect to
     */
    public abstract void draw(Graphics2D g, Entity e);
}
