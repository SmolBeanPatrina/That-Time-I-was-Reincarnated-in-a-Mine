package group6.main;

/**
 * Screen class to group display settings
 */
public class Screen {

    private int originalCellSize = 16; //Each tile is 16x16 pixels
    private int scale = 3;

    private int cellSize = originalCellSize*scale;
    private int screenCol = 16;
    private int screenRow = 12;
    private int screenWidth = cellSize*screenCol;
    private int screenHeight = cellSize*screenRow;

    // public int getTileSize(){return tileSize;}

    // public void setTileSize(int size){tileSize = size;}

    /**
     * Empty constructor
     */
    public Screen() {}

    /**
     * Getter method for the cell size
     * @return the universal size of a cell
     */
    public int getCellSize(){
        return cellSize;
    }

    /**
     * Setter method for the cell size
     * @param n the universal size of a cell
     */
    public void setCellSize(int n){
        cellSize = n;
    }

    /**
     * Getter method for the screen width
     * @return the width of the screen
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Setter method for the screen width
     * @param n the width of the screen
     */
    public void setScreenWidth(int n){
        screenWidth = n;
    }

    /**
     * Getter method for the screen height
     * @return the height of the screen
     */
    public int getScreenHeight(){
        return screenHeight;
    }

    /**
     * Setter method for the screen height
     * @param n the height of the screen
     */
    public void setScreenHeight(int n){
        screenHeight = n;
    }

    /**
     * Getter method for the number of columns in the screen (refactor later)
     * @return number of columns
     */
    public int getScreenCol(){
        return screenCol;
    }

    /**
     * Setter method for the number of columns in the screen (refactor later)
     * @param screenCol number of columns
     */
    public void setScreenCol(int screenCol){
        this.screenCol = screenCol;
    }

    /**
     * Getter method for the number of rows in the screen (refactor later)
     * @return number of rows
     */
    public int getScreenRow(){
        return screenRow;
    }

    /**
     * Setter method for the number of rows in the screen (refactor later)
     * @param screenRow number of rows
     */
    public void setScreenRow(int screenRow){
        this.screenRow = screenRow;
    }

}
