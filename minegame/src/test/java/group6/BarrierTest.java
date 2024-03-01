package group6;

import group6.cell.Barrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class to test Barrier
 */
public class BarrierTest extends CellTest {

    /**Empty Constructor */
    BarrierTest() {}

    /**
     * Set up method
     */
    @BeforeEach
    public void setup() {
        cell = new Barrier("test1");
    }

    @Test
    public void barriersBlockMovement() {
        assertTrue(cell.getCollision());
    }

    @Test
    public void barriersCannotBeUnlocked() {
        assertFalse(cell.isUnlockable(null));
    }
}
