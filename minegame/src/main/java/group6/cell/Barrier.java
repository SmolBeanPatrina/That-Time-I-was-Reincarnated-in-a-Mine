package group6.cell;

import group6.cell.Cell;
import group6.entity.MainCharacter;

/**
 * Barrier class that blocks movement
 */
public class Barrier extends Cell {

    /**
     * Barrier constructor
     * @param cellName Name of barrier for image parsing
     */
    public Barrier(String cellName) {
        setCollision(true);
        setCellName(cellName);
        loadImage();
    }

    /**
     * Loads the image for the barrier
     */
    public void loadImage(){
        super.loadImage("/sprites/cells/" + getCellName() + ".png");
    }

    @Override
    public boolean isUnlockable(MainCharacter mc) {
        return false;
    }

}
