package group6;

import group6.cell.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class to test Wall
 */
public class WallTest extends CellTest {

    /**Empty Constructor */
    WallTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        cell = new Wall("test1");
    }

    @Test
    public void wallsBlockMovement() {
        assertTrue(cell.getCollision());
    }

    @Test
    public void wallsCannotBeUnlocked() {
        assertFalse(cell.isUnlockable(null));
    }
}
