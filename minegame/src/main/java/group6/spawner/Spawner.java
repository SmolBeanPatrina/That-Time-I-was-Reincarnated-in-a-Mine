package group6.spawner;

import group6.main.CollisionHandler;
import group6.entity.Entity;
import group6.entity.MainCharacter;
import group6.map.Map;

import java.awt.Graphics2D;

/**
 * Generic spawner class that spawns generic entities
 */
public abstract class Spawner {
    /**
     * Map for setting spawns in
     */
    protected Map map;
    
    /**
     * Spawner constructor
     * @param map the map where spawns are set
     */
    public Spawner(Map map) {
        this.map = map;
    }

    /**
     * Pre-sets the spawns of entities
     */
    public abstract void setSpawns();

    /**
     * Draws each spawned entity with respect to the position of Entity e
     * @param g the Graphics2D object for drawing
     * @param e the entity to get their position
     */
    public abstract void draw(Graphics2D g, Entity e);

    public abstract void update(CollisionHandler collisionHandler, MainCharacter mc);

    public abstract void resetGame();
}
