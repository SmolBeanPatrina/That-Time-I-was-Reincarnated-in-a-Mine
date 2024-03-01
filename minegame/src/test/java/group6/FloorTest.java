package group6;

import group6.cell.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Class to test Floor
 */
public class FloorTest extends CellTest {

    /**Empty Constructor */
    FloorTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        cell = new Floor("test1");
    }

    @Test
    public void floorsAllowMovement() {
        assertFalse(cell.getCollision());
    }

    @Test
    public void floorsCannotBeUnlocked() {
        assertFalse(cell.isUnlockable(null));
    }
}
