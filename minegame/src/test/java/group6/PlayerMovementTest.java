package group6;
import group6.entity.MainCharacter;
import group6.main.KeyHandler;
import group6.main.PlayerMovementHandler;
import group6.main.Screen;
import group6.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Class to test player movement from keyboard presses
 */
public class PlayerMovementTest {
    
    private MainCharacter mainCharacter;
    private Map map;
    private KeyHandler keyHandler;
    private PlayerMovementHandler playerMovementHandler;

    /**
     * Empty constructor
     */
    public PlayerMovementTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        map = new Map(new Screen());
        keyHandler = new KeyHandler();
        mainCharacter = new MainCharacter(keyHandler, map);
        playerMovementHandler = new PlayerMovementHandler(mainCharacter, keyHandler);
    }

    /**
     * Main character should move up if the player presses W 
     * with no wall/barrier in the way
     */
    @Test
    public void playerMovesUpFromPressingW() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        playerMovementHandler.setKeyPressed(KeyEvent.VK_W);
        playerMovementHandler.updatePlayerDirection();
        playerMovementHandler.updatePlayerMovement();

        assertEquals(20*map.getCellSize() - mainCharacter.getSpeed(), mainCharacter.getMapPos().getY());
    }

    /**
     * Main character should move down if the player presses S 
     * with no wall/barrier in the way
     */
    @Test
    public void playerMovesDownFromPressingS() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        playerMovementHandler.setKeyPressed(KeyEvent.VK_S);
        playerMovementHandler.updatePlayerDirection();
        playerMovementHandler.updatePlayerMovement();

        assertEquals(20*map.getCellSize() + mainCharacter.getSpeed(), mainCharacter.getMapPos().getY());
    }

    /**
     * Main character should move left if the player presses A 
     * with no wall/barrier in the way
     */
    @Test
    public void playerMovesLeftFromPressingA() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        playerMovementHandler.setKeyPressed(KeyEvent.VK_A);
        playerMovementHandler.updatePlayerDirection();
        playerMovementHandler.updatePlayerMovement();

        assertEquals(2*map.getCellSize() - mainCharacter.getSpeed(), mainCharacter.getMapPos().getX());
    }

    /**
     * Main character should move right if the player presses D 
     * with no wall/barrier in the way
     */
    @Test
    public void playerMovesRightFromPressingD() {
        mainCharacter.setMapPos(2*map.getCellSize(), 20*map.getCellSize());
        playerMovementHandler.setKeyPressed(KeyEvent.VK_D);
        playerMovementHandler.updatePlayerDirection();
        playerMovementHandler.updatePlayerMovement();

        assertEquals(2*map.getCellSize() + mainCharacter.getSpeed(), mainCharacter.getMapPos().getX());
    }
}
