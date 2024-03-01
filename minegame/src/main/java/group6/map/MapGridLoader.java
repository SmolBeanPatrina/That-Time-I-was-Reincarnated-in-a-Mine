package group6.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class to load map grid from resource folder
 */
public class MapGridLoader {
    private int mapCol;
    private int mapRow;

    /**
     * MapGrid constructor
     */
    public MapGridLoader(int mapCol, int mapRow){
        this.mapCol = mapCol;
        this.mapRow = mapRow;
    }

    /**
     * Load map grid from a map_grid text file. Numbers in map_grid are indices of a cell.
     * @param mapData Name of the map_grid text file
     */
    public void loadGrid(String mapData, int[][] grid){
        try {
            InputStream is = getClass().getResourceAsStream("/maps/" + mapData + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Traverse map data entry-by-entry and store into grid array
            for (int row = 0; row < mapRow; row++) {
                String line = br.readLine();
                String[] cellNums = line.split(",");

                for (int col = 0; col < mapCol; col++) {
                    int cellNum = Integer.parseInt(cellNums[col]);
                    grid[row][col] = cellNum;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
