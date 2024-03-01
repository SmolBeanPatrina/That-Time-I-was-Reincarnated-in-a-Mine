package group6.spawner;

import group6.main.CollisionHandler;
import group6.main.Position;
import group6.entity.Coin;
import group6.entity.Entity;
import group6.entity.MainCharacter;
import group6.map.Map;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;

/**
 * Generic reward spawner
 */
public class CoinSpawner extends Spawner {
    private int maxCoins = 10;
    /**
     * Array of rewards
     */
    protected Coin[] coins = new Coin[maxCoins];
    private int counter;
    private HashMap<Integer, Position> possibleCoordinates = new HashMap<Integer, Position>();

    /**
     * CoinSpawner constructor
     * @param map the map where rewards are spawned
     */
    public CoinSpawner(Map map) {
        super(map);
        counter = 0;

        //goes through the map and checks which cells the main character can collide with,
        //stores the ones that qualify in a hashmap for easy access
        int i = 0;
        for (int x = 0; x < map.getMapCol(); x++){
            for (int y = 0; y < map.getMapRow(); y++){
                if (!map.getCell(x * map.getCellSize(), y * map.getCellSize()).getCollision()){
                    possibleCoordinates.put(++i, new Position(x, y));
                }
            }
        }
    }

    /**
     * Draws each reward (refactor later)
     * @param g temp
     * @param e temp
     */
    public void draw(Graphics2D g, Entity e){
        for (int i = 0; i < maxCoins; i++){
            if (coins[i] != null){
                coins[i].draw(g, e);
            }
        }
    }

    //used for preSetSpawns
    public void setSpawns(){
        
    }

    /**
     * Updates each reward based on player interaction
     * @param collisionHandler the collision handler to handle player-reward collision
     * @param mc the main character to check collision with
     */
    public void update(CollisionHandler collisionHandler, MainCharacter mc){
        if(counter % 300 == 0){//frequency of new spawns

            //choosing a random index in the hashmap of possible positions for the coin to spawn in
            Random rand = new Random();
            int index = rand.nextInt(possibleCoordinates.size());

            int i = 0;
            while(i < maxCoins && coins[i] != null){i++;}//checks which of the array is available for a new spawn
            if(i < maxCoins){//if there is space in the array, create a new coin
                coins[i] = new Coin(500, map, possibleCoordinates.get(index).getX(), possibleCoordinates.get(index).getY());  
            }else{//if there is no space in the array, remove a random coin and create a new coin in the random index chosen above
                int replaceIndex = rand.nextInt(maxCoins - 1);
                coins[replaceIndex] = null;
                coins[replaceIndex] = new Coin(500, map, possibleCoordinates.get(index).getX(), possibleCoordinates.get(index).getY());
            }
            
        }

        //call update for each of the coins, removing coins from the array if they've been collected and the animation complete
        for (int i = 0; i < maxCoins; i++){
            if (coins[i] != null){
                coins[i].update(collisionHandler, mc);
                if (coins[i].getCollectedCounter() == 5){
                    coins[i] = null;

                }
            }
        }
        counter++;
    }

    /**
     * Removes all coins from the array (refactor later to override)
     */
    public void resetGame(){
        for (int i = 0; i < maxCoins; i++){
            coins[i] = null;
        }
    }
}
