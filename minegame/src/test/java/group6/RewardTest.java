package group6;
import group6.entity.Coin;
import group6.entity.MainCharacter;
import group6.entity.Reward;
import group6.entity.Star;
import group6.main.CollisionHandler;
import group6.main.KeyHandler;
import group6.main.Screen;
import group6.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Class to test rewards
 */
public class RewardTest {
    
    private MainCharacter mainCharacter;
    private Map map;
    private KeyHandler keyHandler;
    private CollisionHandler collisionHandler;
    private Reward star;
    private Reward coin;

    /**
     * Empty constructor
     */
    public RewardTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        map = new Map(new Screen());
        keyHandler = new KeyHandler();
        mainCharacter = new MainCharacter(keyHandler, map);
        collisionHandler = new CollisionHandler(null);
        star = new Star(map, "top", 0, 0);
        coin = new Coin(500, map, 0, 0);
    }

    /**
     * Moving into a star should update player score 
     */
    @Test
    public void movingUpIntStarShouldUpdateScore() {
        star.setMapPos(2, 19);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        if(collisionHandler.collidingWithReward(mainCharacter, star, map)) {
            star.updateScore(mainCharacter);
        }
        assertEquals(500, mainCharacter.getScore());
    }

    /**
     * Moving into a star should update player score 
     */
    @Test
    public void movingUpIntCoinShouldUpdateScore() {
        coin.setMapPos(2, 19);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        if(collisionHandler.collidingWithReward(mainCharacter, coin, map)) {
            coin.updateScore(mainCharacter);
        }
        assertEquals(1000, mainCharacter.getScore());
    }


}
