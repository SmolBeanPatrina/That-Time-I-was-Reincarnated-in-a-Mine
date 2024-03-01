package group6.spawner;

import group6.main.CollisionHandler;
import group6.main.Position;
import group6.entity.Entity;
import group6.entity.MainCharacter;
import group6.entity.Punishment;
import group6.entity.Trap;
import group6.map.Map;

import java.awt.Graphics2D;

/**
 * Trap spawner that spawns traps at preset locations
 */
public class TrapSpawner extends PunishmentSpawner {

    private int trapsSize = 12;

    /**
     * TrapSpawner constructor
     * @param map the map where spawns are set
     */
    public TrapSpawner(Map map) {
        super(map);
        punishments = new Trap[trapsSize];
    }

    @Override
    public void setSpawns() {
        punishments[0] = new Trap(map, new Position(29, 23));
        punishments[1] = new Trap(map, new Position(55, 18));
        punishments[2] = new Trap(map, new Position(57, 22));
        punishments[3] = new Trap(map, new Position(53, 3));
        punishments[4] = new Trap(map, new Position(58, 2));
        punishments[5] = new Trap(map, new Position(57, 40));
        punishments[6] = new Trap(map, new Position(29, 28));
        punishments[7] = new Trap(map, new Position(61, 34));
        punishments[8] = new Trap(map, new Position(46, 37));
        punishments[9] = new Trap(map, new Position(42, 14));
        punishments[10] = new Trap(map, new Position(45, 16));
        punishments[11] = new Trap(map, new Position(44, 12));
    }

    /**
     * Updates each trap (refactor later)
     * @param collisionHandler the collison handler to check for player collision
     * @param mc the main character to get their position
     */
    public void update(CollisionHandler collisionHandler, MainCharacter mc) {
        for (Punishment trap : punishments) {
            trap.update(collisionHandler, mc);
        }
    }

    @Override
    public void draw(Graphics2D g, Entity e) {
        for (Punishment trap : punishments) {
            trap.draw(g, e);
        }
    }

    @Override
    public void resetGame(){
        for (int i = 0; i < trapsSize; i++){
            punishments[i] = null;
        }
    }
}
