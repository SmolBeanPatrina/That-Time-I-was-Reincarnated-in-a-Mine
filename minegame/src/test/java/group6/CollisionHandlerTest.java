package group6;

import group6.entity.*;
import group6.main.*;
import group6.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group6.main.Game.GameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class to test CollisionHandler
 */
public class CollisionHandlerTest {
    
    private CollisionHandler collisionHandler;
    private Map map;
    private MovingEnemy movingEnemy;
    private MainCharacter mainCharacter;
    private Reward reward;
    private Punishment punishment;
    private Game game;

    /**
     * Empty constructor
     */
    public CollisionHandlerTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        game = new Game();
        collisionHandler = new CollisionHandler(game);
        map = new Map(new Screen());
        mainCharacter = new MainCharacter(new KeyHandler(), map);
        reward = new Star(map, "top", 0, 0);
        movingEnemy = new Slime(map, new Position(0, 0));
        punishment = new Trap(map, new Position(0, 0));
    }

    /**
     * Moving up into barrier/wall blocks movement
     */
    @Test
    public void movingUpIntoBlockedCellStopsMovement() {
        mainCharacter.setMapPos(1*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        assertTrue(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving down into barrier/wall blocks movement
     */
    @Test
    public void movingDownIntoBlockedCellStopsMovement() {
        mainCharacter.setDirection("down");
        assertTrue(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving left into barrier/wall blocks movement
     */
    @Test
    public void movingLeftIntoBlockedCellStopsMovement() {
        mainCharacter.setDirection("left");
        assertTrue(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving right into barrier/wall blocks movement
     */
    @Test
    public void movingRightIntoBlockedCellStopsMovement() {
        mainCharacter.setMapPos(9*map.getCellSize(), 19*map.getCellSize());
        mainCharacter.setDirection("right");
        assertTrue(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving up into a cell without a wall/barrier allows for movement
     */
    @Test
    public void movingUpIntoFreeCellAllowsMovement() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("up");
        assertFalse(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving down into a cell without a wall/barrier allows for movement
     */
    @Test
    public void movingDownIntoFreeCellAllowsMovement() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("down");
        assertFalse(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving left into a cell without a wall/barrier allows for movement
     */
    @Test
    public void movingLeftIntoFreeCellAllowsMovement() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("left");
        assertFalse(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving right into a cell without a wall/barrier allows for movement
     */
    @Test
    public void movingRightIntoFreeCellAllowsMovement() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("right");
        assertFalse(collisionHandler.colldingWithCell(mainCharacter, map));
    }

    /**
     * Moving up into a uncollected reward should collide with it
     */
    @Test
    public void movingUpIntoUncollectedRewardShouldCollide() {
        reward.setMapPos(2, 19);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        assertTrue(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving down into a uncollected reward should collide with it
     */
    @Test
    public void movingDownIntoUncollectedRewardShouldCollide() {
        reward.setMapPos(2, 21);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("down");
        assertTrue(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving right into a uncollected reward should collide with it
     */
    @Test
    public void movingRightIntoUncollectedRewardShouldCollide() {
        reward.setMapPos(3, 20);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("right");
        assertTrue(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving left into a uncollected reward should collide with it
     */
    @Test
    public void movingLeftIntoUncollectedRewardShouldCollide() {
        reward.setMapPos(1, 20);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("left");
        assertTrue(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving up into a collected reward should not collide with it
     */
    @Test
    public void movingUpIntoCollectedRewardShouldNotCollide() {
        reward.setMapPos(2, 19);
        reward.setCollected(true);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        assertFalse(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving down into a collected reward should not collide with it
     */
    @Test
    public void movingDownIntoCollectedRewardShouldNotCollide() {
        reward.setMapPos(2, 21);
        reward.setCollected(true);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("down");
        assertFalse(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving right into a collected reward should not collide with it
     */
    @Test
    public void movingRightIntoCollectedRewardShouldNotCollide() {
        reward.setMapPos(3, 20);
        reward.setCollected(true);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("right");
        assertFalse(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Moving left into a collected reward should not collide with it
     */
    @Test
    public void movingLeftIntoCollectedRewardShouldNotCollide() {
        reward.setMapPos(1, 20);
        reward.setCollected(true);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("left");
        assertFalse(collisionHandler.collidingWithReward(mainCharacter, reward, map));
    }

    /**
     * Enemy moving up into the player results in a game over
     */
    @Test
    public void enemyMovingUpIntoPlayerEndsGame() {
        movingEnemy.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        movingEnemy.setDirection("up");
        mainCharacter.setMapPos(2*map.getCellSize(), 19*map.getCellSize());
        
        collisionHandler.collidingWithEnemy(mainCharacter, movingEnemy, map);
        assertEquals(GameState.LOSE, game.getState());
    }

    /**
     * Enemy moving down into the player results in a game over
     */
    @Test
    public void enemyMovingDownIntoPlayerEndsGame() {
        movingEnemy.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        movingEnemy.setDirection("down");
        mainCharacter.setMapPos(2*map.getCellSize(), 21*map.getCellSize()  - map.getCellSize()/2);

        collisionHandler.collidingWithEnemy(mainCharacter, movingEnemy, map);
        assertEquals(GameState.LOSE, game.getState());
    } 

    /**
     * Enemy moving right into the player results in a game over
     */
    @Test
    public void enemyMovingRightIntoPlayerEndsGame() {
        movingEnemy.setMapPos(2*map.getCellSize() + map.getCellSize()/2, 20*map.getCellSize());
        movingEnemy.setDirection("right");
        mainCharacter.setMapPos(3*map.getCellSize(), 20*map.getCellSize());
        collisionHandler.collidingWithEnemy(mainCharacter, movingEnemy, map);
        assertEquals(GameState.LOSE, game.getState());
    } 

    /**
     * Enemy moving left into the player results in a game over
     */
    @Test
    public void enemyMovingLeftIntoPlayerEndsGame() {
        movingEnemy.setMapPos(3*map.getCellSize(), 20*map.getCellSize());
        movingEnemy.setDirection("left");
        mainCharacter.setMapPos(2*map.getCellSize() + map.getCellSize()/2, 20*map.getCellSize());
        collisionHandler.collidingWithEnemy(mainCharacter, movingEnemy, map);
        assertEquals(GameState.LOSE, game.getState());
    } 

    /**
     * Moving up into a punishment should collide with it
     */
    @Test
    public void movingUpIntoPunishmentShouldCollide() {
        punishment.setMapPos(2, 19);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        assertTrue(collisionHandler.collidingWithPunishment(mainCharacter, punishment, map));
    }

    /**
     * Moving down into a punishment should collide with it
     */
    @Test
    public void movingDownIntoPunishmentShouldCollide() {
        punishment.setMapPos(2, 21);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("down");
        assertTrue(collisionHandler.collidingWithPunishment(mainCharacter, punishment, map));
    }

    /**
     * Moving right into a punishment should collide with it
     */
    @Test
    public void movingRightIntoPunishmentShouldCollide() {
        punishment.setMapPos(3, 20);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("right");
        assertTrue(collisionHandler.collidingWithPunishment(mainCharacter, punishment, map));
    }

    /**
     * Moving left into a punishment should collide with it
     */
    @Test
    public void movingLeftIntoPunishmentShouldCollide() {
        punishment.setMapPos(1, 20);
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        mainCharacter.setDirection("left");
        assertTrue(collisionHandler.collidingWithPunishment(mainCharacter, punishment, map));
    }

    /**
     * Moving into exit after collecting all stars results in a win
     */
    @Test
    public void movingIntoExitWithAllStarsWinsGame() {
        mainCharacter.setMapPos(70*map.getCellSize(), 35*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        mainCharacter.setStarsCollected(5);
        collisionHandler.collidingWithExit(mainCharacter, map);
        assertEquals(GameState.WIN, game.getState());
    }

    /**
     * Moving into exit after collecting all stars results in a win
     */
    @Test
    public void movingIntoExitWithoutAllStarsCollides() {
        mainCharacter.setMapPos(70*map.getCellSize(), 35*map.getCellSize() - map.getCellSize()/2);
        mainCharacter.setDirection("up");
        mainCharacter.setStarsCollected(0);
        collisionHandler.collidingWithExit(mainCharacter, map);
        assertTrue(collisionHandler.colldingWithCell(mainCharacter, map));
    }
}
