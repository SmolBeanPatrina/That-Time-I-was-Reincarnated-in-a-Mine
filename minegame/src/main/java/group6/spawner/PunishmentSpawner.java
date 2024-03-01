package group6.spawner;

import group6.entity.Entity;
import group6.entity.Punishment;
import group6.map.Map;
import java.awt.Graphics2D;

/**
 * Generic punishment spawner
 */
public abstract class PunishmentSpawner extends Spawner {
    /**
     * Array of punishments
     */
    protected Punishment[] punishments = new Punishment[10];

    public Punishment[] getPunishments() {
        return punishments;
    }

    /**
     * Default constructor
     * @param map the map where the spawns are set
     */
    public PunishmentSpawner(Map map) {
        super(map);
    }

    /**
     * Resets spawns (refactor later)
     */
    public abstract void resetGame();

    /**
     * Draws the spawned punishments (refactor later)
     * @param g temp
     * @param e temp
     */
    public abstract void draw(Graphics2D g, Entity e);
}
