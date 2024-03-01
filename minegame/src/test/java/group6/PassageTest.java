package group6;

import group6.cell.Passage;
import group6.entity.MainCharacter;
import group6.main.KeyHandler;
import group6.main.Screen;
import group6.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class to test Passage
 */
public class PassageTest extends CellTest {
    
    private MainCharacter mc;

    /**Empty Constructor */
    PassageTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        cell = new Passage("test1");
        mc = new MainCharacter(new KeyHandler(), new Map(new Screen()));
    }

    /**
     * Passage collision should be toggled to true when the player has collected all stars
     */
    @Test
    public void unlockableWhenPlayerCollectedAllStars() {
        mc.setStarsCollected(5);
        assertTrue(cell.isUnlockable(mc));
    }

    /**
     * Passage collision should stay false when the player has not collected all stars
     */
    @Test
    public void lockedWhenPlayerHasNotCollectedAllStars() {
        mc.setStarsCollected(0);
        assertFalse(cell.isUnlockable(mc));
    }
}
