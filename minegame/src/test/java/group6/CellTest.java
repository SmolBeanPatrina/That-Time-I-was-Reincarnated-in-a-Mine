package group6;

import group6.cell.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Generic class to test Cell
 */
public abstract class CellTest {
    
    protected Cell cell;

    /**Empty Constructor */
    CellTest() {}
    
    @Test
    public void imageIsNotNull() {
        assertNotNull(cell.getImage());
    };
}
