package group6.spawner;

import group6.main.CollisionHandler;
import group6.main.Position;
import group6.entity.Entity;
import group6.entity.MainCharacter;
import group6.entity.MovingEnemy;
import group6.entity.Slime;
import group6.map.Map;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;

/**
 * Slime spawner class that spawns slimes at preset coordinates and at random
 */
public class SlimeSpawner extends MovingEnemySpawner {
    private int maxSlimes = 6;
    /**
     * Array of preset-spawned slimes
     */
    protected Slime[] slimes = new Slime[maxSlimes];
    private int countSlimes;
    private int counter;
    private HashMap<Integer, Position> possibleCoordinates = new HashMap<Integer, Position>();

    public Slime[] getSlimes() {
        return slimes;
    }

    /**
     * SlimeSpawner constructor
     * @param map the map where spawns are set
     */
    public SlimeSpawner(Map map) {
        super(map);
        movingEnemies = new Slime[3];

        counter = 0;

        int i = 0;
        for (int x = 0; x < map.getMapCol(); x++){
            for (int y = 0; y < map.getMapRow(); y++){
                if (!map.getCell(x * map.getCellSize(), y * map.getCellSize()).getCollision()){
                    possibleCoordinates.put(++i, new Position(x, y));
                }
            }
        }
        
        countSlimes = 0;
    }

    @Override
    public void setSpawns() {
        movingEnemies[0] = new Slime(map, new Position(24, 20));
        // movingEnemies[1] = new Slime(map, new Position(35, 32));
        // movingEnemies[2] = new Slime(map, new Position(43, 15));
        // movingEnemies[2] = new Slime(map, new Position(60, 20));
        movingEnemies[1] = new Slime(map, new Position(58, 2));
        movingEnemies[2] = new Slime(map, new Position(57, 40));
    }

    @Override
    public void update(CollisionHandler collisionHandler, MainCharacter mc) {
        for (MovingEnemy slime : movingEnemies) {
            slime.update(collisionHandler, mc);
        }

        if(counter % 300 == 0 && countSlimes < maxSlimes){

            int j = 0;
            while(j < maxSlimes && slimes[j]!= null){j++;}

            Random rand = new Random();
            int index = rand.nextInt(possibleCoordinates.size());

            slimes[j] = new Slime(map, new Position(possibleCoordinates.get(index).getX(), possibleCoordinates.get(index).getY()));
            countSlimes++;
        }

        for (int i = 0; i < maxSlimes; i++){
            if (slimes[i] != null){
                slimes[i].update(collisionHandler, mc);
            }
        }
        counter++;
    }

    @Override
    public void draw(Graphics2D g, Entity e) {
        for (MovingEnemy slime : movingEnemies) {
            slime.draw(g, e);
        }

        for (int i = 0; i < maxSlimes; i++){
            if (slimes[i] != null){
                slimes[i].draw(g, e);
            }
        }
    }

    @Override
    public void resetGame(){
        for (int i = 0; i < maxSlimes; i++){
            slimes[i] = null;
        }
        for (int i = 0; i < 3; i++){
            movingEnemies[i] = null;
        }
        countSlimes = 0;
    }
    
}
