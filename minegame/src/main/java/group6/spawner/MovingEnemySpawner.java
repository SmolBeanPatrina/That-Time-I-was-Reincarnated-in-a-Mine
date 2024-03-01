package group6.spawner;

import group6.main.CollisionHandler;
import group6.entity.MainCharacter;
import group6.entity.MovingEnemy;
import group6.map.Map;

/**
 * Generic spawner for generic moving enemies
 */
public abstract class MovingEnemySpawner extends Spawner {

    /**
     * Aray of moving enemies
     */
    protected MovingEnemy[] movingEnemies;

    public MovingEnemy[] getMovingEnemies() {
        return movingEnemies;
    }

    /**
     * MovingEnemySpawner constructor
     * @param map the map where spawns are set
     */
    public MovingEnemySpawner(Map map) {
        super(map);
    }

    /**
     * Updates each spawned moving enemy
     * @param collisionHandler the collision handler to check collision
     * @param mc the main character to get their position
     */
    public abstract void update(CollisionHandler collisionHandler, MainCharacter mc);

    /**
     * Resets spawns (refactor later)
     */
    public abstract void resetGame();
}

