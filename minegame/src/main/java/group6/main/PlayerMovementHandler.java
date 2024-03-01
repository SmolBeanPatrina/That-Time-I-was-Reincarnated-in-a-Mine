package group6.main;
import group6.entity.MainCharacter;

import java.awt.event.KeyEvent;

/**
 * Class to handle abstract keyboard presses away from the MainCharacter
 */
public class PlayerMovementHandler {
    private KeyHandler keyHandler;
    private MainCharacter mc;
    private int keyPressed = KeyEvent.VK_UNDEFINED;

    /**
     * PlayerMovementHandler constructor
     * @param mc the main character who is controller by the player
     * @param keyHandler the key handler that listens to player inputs
     */
    public PlayerMovementHandler(MainCharacter mc, KeyHandler keyHandler) {
        this.mc = mc;
        this.keyHandler = keyHandler;
    }

    /**
     * Updates the player's direction based on key
     */
    public void updatePlayerDirection() {
        updateKeyPressed();
        
        if (keyPressed == KeyEvent.VK_W) {
            mc.setDirection("up");
        }
        if (keyPressed == KeyEvent.VK_S) {
            mc.setDirection("down");
        }
        if (keyPressed == KeyEvent.VK_D) {
            mc.setDirection("right");
        }
        if (keyPressed == KeyEvent.VK_A) {
            mc.setDirection("left");
        }
    }

    /**
     * Updates the player's movement based on direction
     */
    public void updatePlayerMovement() {
        int mcX = mc.getMapPos().getX();
        int mcY = mc.getMapPos().getY();
        int mcSpeed = mc.getSpeed();

        switch (mc.getDirection()) {
            case "up":
                mc.setMapPos(mcX, mcY - mcSpeed);
                break;
            case "down":
                mc.setMapPos(mcX, mcY + mcSpeed);
                break;
            case "left":
                mc.setMapPos(mcX - mcSpeed, mcY);
                break;
            case "right":
                mc.setMapPos(mcX + mcSpeed, mcY);
                break;
        }
    }

    /**
     * Checks if player is making any inputs
     * @return True if no player input is made
     */
    public boolean playerIsIdle() {
        if (!keyHandler.getUpPressed()
            && !keyHandler.getDownPressed()
            && !keyHandler.getLeftPressed()
            && !keyHandler.getRightPressed()) {return true;}

        return false;
    }

    /**
     * Update the pressed key
     */
    private void updateKeyPressed() {
        if (keyHandler.getUpPressed()) {
            keyPressed = KeyEvent.VK_W;
        }
        if (keyHandler.getDownPressed()) {
            keyPressed = KeyEvent.VK_S;
        }
        if (keyHandler.getRightPressed()) {
            keyPressed = KeyEvent.VK_D;
        }
        if (keyHandler.getLeftPressed()) {
            keyPressed = KeyEvent.VK_A;
        }
    }

    /**
     * Getter method for key pressed
     * @return the pressed key encoded by KeyEvent's VK keys
     */
    public int getKeyPressed() {
        return keyPressed;
    }

    /**
     * Setter method for key pressed
     * @param keyPressed the pressed key encoded by KeyEvent's VK keys
     */
    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    
}
