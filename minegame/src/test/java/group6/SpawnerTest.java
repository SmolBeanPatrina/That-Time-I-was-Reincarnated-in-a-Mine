package group6;

import group6.entity.Punishment;
import group6.entity.Slime;
import group6.entity.MovingEnemy;
import group6.entity.Star;
import group6.main.Screen;
import group6.map.Map;
import group6.spawner.SlimeSpawner;
import group6.spawner.StarSpawner;
import group6.spawner.TrapSpawner;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SpawnerTest {
    private TrapSpawner trapSpawner;
    private SlimeSpawner slimeSpawner;
    private StarSpawner starSpawner;

    @Test
    public void testTrapSpawnerSpawns(){
        trapSpawner = new TrapSpawner(new Map(new Screen()));
        trapSpawner.setSpawns();
        Punishment[] punishmentArray = trapSpawner.getPunishments();
        for (int i = 0; i < punishmentArray.length; i++){
            if(punishmentArray[i] == null){
                assertNotEquals(null, punishmentArray[i]);
            }
        }
        assertTrue(true);
    }

    @Test
    public void testSlimeSpawnerSpawns(){
        slimeSpawner = new SlimeSpawner(new Map(new Screen()));
        slimeSpawner.setSpawns();
        MovingEnemy[] movingEnemiesArray = slimeSpawner.getMovingEnemies();
        for (int i = 0; i < movingEnemiesArray.length; i++){
            if(movingEnemiesArray[i] == null){
                assertNotEquals(null, movingEnemiesArray[i]);
            }
        }
        assertTrue(true);
    }

    @Test
    public void testStarSpawns(){
        starSpawner = new StarSpawner(new Map(new Screen()));
        starSpawner.setSpawns();
        HashMap<String, Star> starHashMap = starSpawner.getStars();
        if(starHashMap.get("top") == null){
            assertNotEquals(null, starHashMap.get("top"));
        }
        if(starHashMap.get("left") == null){
            assertNotEquals(null, starHashMap.get("left"));
        }
        if(starHashMap.get("right") == null){
            assertNotEquals(null, starHashMap.get("right"));
        }
        if(starHashMap.get("bottom_left") == null){
            assertNotEquals(null, starHashMap.get("bottom_left"));
        }
        if(starHashMap.get("bottom_right") == null){
            assertNotEquals(null, starHashMap.get("bottom_right"));
        }
        assertTrue(true);
    }
}
