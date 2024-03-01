package group6.cell;

import group6.cell.Cell;
import group6.entity.MainCharacter;

/**
 * Wall class that blocks movement 
 */
public class Wall extends Cell {

    /**
     * Wall constructor
     * @param cellName the name of the wall for image parsing
     */
    public Wall(String cellName) {
        setCollision(true);
        setCellName(cellName);
        loadImage();
    }

    /**
     * Loads the image for the wall
     */
    public void loadImage(){
        super.loadImage("/sprites/cells/" + getCellName() + ".png");
    }

    @Override
    public boolean isUnlockable(MainCharacter mc) {
        return false;
    }

    
}
