package group6.map;

import group6.cell.Floor;
import group6.cell.Passage;
import group6.main.Screen;
import group6.cell.Wall;
import group6.cell.Barrier;
import group6.cell.Cell;
import group6.entity.MainCharacter;

import java.awt.Graphics2D;

/**
 * Class to load and draw the map
 */
public class Map {
    private int mapCol = 76; // change these according to map grid size
    private int mapRow = 42;
    private MapGridLoader mapGridLoader;
    private int[][] grid; // entries are used to index cells
    private Cell[] cells;
    private Screen screen;
    private String[] cellNames = {"void", "floor", "wall1", "wall2", "boulder1", "boulder2", "crate", "rail1", "rail2", "passage", "summon_circle"};

    /**
     * Map constructor
     * @param screen the screen to get display settings from
     */
    public Map(Screen screen){
        this.screen = screen;
        this.cells = new Cell[cellNames.length];
        grid = new int[mapRow][mapCol];
        mapGridLoader = new MapGridLoader(mapCol, mapRow);
        loadCells(cellNames);
        mapGridLoader.loadGrid("map_grid_02", grid);
    }

    /**
     * Getter method to get the number of columns in the map grid
     * @return the number of grid columns
     */
    public int getMapCol(){
        return mapCol;
    }

    /**
     * Getter method to get the number of rows in the map grid
     * @return the number of grid rows
     */
    public int getMapRow(){
        return mapRow;
    }

    /**
     * Getter method to get the cell size. Wraps the Screen's getCellSize method.
     * @return cell size
     */
    public int getCellSize() {
        return screen.getCellSize();
    }

    /**
     * Getter method to get the screen height. Wraps the Screen's getScreenHeight method.
     * @return the screen height
     */
    public int getScreenHeight() {
        return screen.getScreenHeight();
    }

    /**
     * Getter method to get the cell size. Wraps the Screen's getScreenWidth method.
     * @return the screen width
     */
    public int getScreenWidth() {
        return screen.getScreenWidth();
    }

    /**
     * Get Cell from map Position (mapX and mapY) 
     * @param mapX the absolute x coordinate of the cell
     * @param mapY the absolute y coordinate of the cell
     * @return the requested Cell
     */
    public Cell getCell(int mapX, int mapY) {
        if (mapX < 0 || mapY < 0 || mapX >= getCellSize() * getMapCol() || mapY >= getCellSize() * getMapRow()) {
            return null;
        }
        
        // Convert to grid coordinates
        int cellCol = mapX/getCellSize();
        int cellRow = mapY/getCellSize(); 

        Cell targetCell = cells[grid[cellRow][cellCol]];
        return targetCell;
    }

    /**
     * Load cells 
     * @param cellNames
     */
    private void loadCells(String[] cellNames) {
        for (int i = 0; i < cellNames.length; i++) {
            cells[i] = createCellFromCellName(cellNames[i]);
        }
    }

    /**
     * Draws the map relative the main character's position
     * @param g the Graphics2D object for drawing
     * @param mc the main character to get their position
     */
    public void draw(Graphics2D g, MainCharacter mc) {
        int mapX = 0;
        int mapY = 0;
        int screenX = 0;
        int screenY = 0;
        Cell cell;

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                cell = cells[grid[row][col]];

                // Calculate cell's position
                mapX = getCellSize()*col;
                mapY = getCellSize()*row;
                screenX = mapX - mc.getMapPos().getX() + mc.getScreenPos().getX();
                screenY = mapY - mc.getMapPos().getY() + mc.getScreenPos().getY();

                g.drawImage(cell.getImage(), screenX, screenY, getCellSize(), getCellSize(), null);
            }
        }
    }
    
    /**
     * Create a subclass of Cell based from cellName
     * @param cellName
     * @return
     */
    private Cell createCellFromCellName(String cellName) {
        Cell cell = null;

        switch (cellName){
            case "void":
            case "boulder1":
            case "boulder2":
            case "crate":
                cell = new Barrier(cellName);
                break;
            case "wall1":
            case "wall2":
                cell = new Wall(cellName);
                break;
            case "floor":
            case "rail1":
            case "rail2":
            case "summon_circle":
                cell = new Floor(cellName);
                break;
            case "passage":
                cell = new Passage(cellName);
                break;
        }

        return cell;
    }
}
