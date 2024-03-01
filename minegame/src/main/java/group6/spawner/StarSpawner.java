package group6.spawner;

import group6.main.CollisionHandler;
import group6.entity.Entity;
import group6.entity.MainCharacter;
import group6.entity.Star;
import group6.map.Map;

import java.awt.Graphics2D;
import java.util.HashMap;

public class StarSpawner extends Spawner {
    /**
     * Array of preset-spawned Star pieces
     */
    protected HashMap<String, Star> starHashMap = new HashMap<String, Star>();

    /**
     * SlimeSpawner constructor
     * @param map the map where spawns are set
     */
    public StarSpawner(Map map) {
        super(map);
    }

    public HashMap<String, Star> getStars() {
        return starHashMap;
    }

    @Override
    public void setSpawns() {
        starHashMap.put("top", new Star(map, "top", 31, 30));
        starHashMap.put("left", new Star(map, "left", 50, 15));
        starHashMap.put("right", new Star(map, "right", 73, 4));
        starHashMap.put("bottom_left", new Star(map, "bottom_left", 70, 20));
        starHashMap.put("bottom_right", new Star(map, "bottom_right", 43, 37));
    }

    @Override
    public void update(CollisionHandler collisionHandler, MainCharacter mc) {
        starHashMap.get("top").update(collisionHandler, mc);
        starHashMap.get("left").update(collisionHandler, mc);
        starHashMap.get("right").update(collisionHandler, mc);
        starHashMap.get("bottom_left").update(collisionHandler, mc);
        starHashMap.get("bottom_right").update(collisionHandler, mc);
    }

    @Override
    public void draw(Graphics2D g, Entity e) {
        starHashMap.get("top").draw(g, e);
        starHashMap.get("left").draw(g, e);
        starHashMap.get("right").draw(g, e);
        starHashMap.get("bottom_left").draw(g, e);
        starHashMap.get("bottom_right").draw(g, e);
    }

    @Override
    public void resetGame(){
        starHashMap.get("top").setCollected(false);
        starHashMap.get("left").setCollected(false);
        starHashMap.get("right").setCollected(false);
        starHashMap.get("bottom_left").setCollected(false);
        starHashMap.get("bottom_right").setCollected(false);
    }
}
