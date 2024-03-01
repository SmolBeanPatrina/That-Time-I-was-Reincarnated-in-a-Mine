package group6.cell;

import group6.entity.MainCharacter;

/**
 * Floor cell for moving entities to move across
 */
public class Floor extends Cell {

    /**
     * Floor constructor
     * @param cellName the name of the floor cell
     */
    public Floor(String cellName) {
        setCellName(cellName);
        loadImage();
    }
    
    /**
     * Loads the image for the floor
     */
    public void loadImage(){
        super.loadImage("/sprites/cells/" + getCellName() + ".png");
    }

    @Override
    public boolean isUnlockable(MainCharacter mc) {
        return false;
    }

}
