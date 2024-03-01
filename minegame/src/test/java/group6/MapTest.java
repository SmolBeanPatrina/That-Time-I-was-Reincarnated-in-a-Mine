package group6;

import group6.cell.Cell;
import group6.main.Position;
import group6.main.Screen;
import group6.map.Map;
import group6.map.MapGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test the Map
 */
public class MapTest {
    
    private Map map;
    private MapGridLoader mapGridLoader;

    MapTest() {}

    @BeforeEach
    public void setup() {
        map = new Map(new Screen());
    }
    
    /**
     * Gets a Cell from the Map's boundary
     */
    @Test
    public void getCellFromMapBoundary() {
        Position cellMapPos = new Position(map.getCellSize() * (map.getMapCol() - 1), map.getCellSize() * (map.getMapRow() - 1));
        Cell actualCell = map.getCell(cellMapPos.getX(), cellMapPos.getY());
        assertEquals("void", actualCell.getCellName());
    }

    /**
     * Tries to gets a Cell the Map's out-of-bounds area on the left
     */
    @Test
    public void getCellTooFarLeftInvalid() {
        Cell actualCell = map.getCell(-1, 0);
        assertEquals(null, actualCell);
    }

    /**
     * Tries to gets a Cell the Map's out-of-bounds area on the right
     */
    @Test
    public void getCellTooFarRightInvalid() {
        Cell actualCell = map.getCell(map.getCellSize() * map.getMapCol() , 0);
        assertEquals(null, actualCell);
    }

    /**
     * Tries to gets a Cell the Map's out-of-bounds area at the top
     */
    @Test
    public void getCellTooFarUpInvalid() {
        Cell actualCell = map.getCell(-1, 0);
        assertEquals(null, actualCell);
    }

    /**
     * Tries to gets a Cell the Map's out-of-bounds area at the bottom
     */
    @Test
    public void getCellTooFarDownInvalid() {
        Cell actualCell = map.getCell(0, map.getCellSize() * map.getMapRow());
        assertEquals(null, actualCell);
    }

    /**
     * Loads a test map grid from resources
     */
    @Test
    public void loadMapGridFromResourceFolder() {
        mapGridLoader = new MapGridLoader(5, 5);
        int expectedGrid[][] = {{0,0,0,0,0}, {1,1,1,1,1}, {2,2,2,2,2}, {3,3,3,3,3}, {4,4,4,4,4}};
        int actualGrid[][] = new int[5][5];
        mapGridLoader.loadGrid("map_grid_test", actualGrid);
        assertArrayEquals(expectedGrid, actualGrid);
    }

}
