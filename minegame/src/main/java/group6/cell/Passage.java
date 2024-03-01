package group6.cell;

import group6.cell.Cell;
import group6.entity.MainCharacter;

/**
 * Passage class for exiting
 */
public class Passage extends Cell {

    /**
     * Passage constructor
     * @param cellName the name of the passage
     */
    public Passage(String cellName) {
        setCollision(true);
        setCellName(cellName);
        loadImage();
    }

    /**
     * Loads the image for the passage
     */
    public void loadImage(){
        super.loadImage("/sprites/cells/" + getCellName() + ".png");
    }

    public boolean isUnlockable(MainCharacter mc) {
        if (mc.getStarsCollected() == 5) {
            return true;
        }
        return false;
    }

}
