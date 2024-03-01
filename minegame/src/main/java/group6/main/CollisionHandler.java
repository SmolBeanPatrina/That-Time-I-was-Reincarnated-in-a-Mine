package group6.main;

import group6.main.Game.GameState;
import group6.cell.Cell;
import group6.entity.*;
import group6.map.Map;

/**
 * Class that handles all collision
 */ 
public class CollisionHandler {  
    
    private Game game;

    /**
     * CollisoinHabndler constructor
     * @param game the game to handle states
     */
    public CollisionHandler(Game game) {
        this.game = game;
    }

    /**
     * Checks if the moving entity is colliding with a cell that can block movement
     * @param me the moving entity
     * @param map the map that contains all cells
     * @return True if the moving entity is colliding with a blocking cell
     */
    public boolean colldingWithCell(MovingEntity me, Map map){

        // Get hitbox position to get its corners
        int eHitBoxMapX = me.getMapPos().getX() + (int)me.getHitBox().getX();
        int eHitBoxMapXOffset = me.getMapPos().getX() + (int)me.getHitBox().getX() + (int)me.getHitBox().getWidth();
        int eHitBoxMapY = me.getMapPos().getY() + (int)me.getHitBox().getY();
        int eHitBoxMapYOffset = me.getMapPos().getY() + (int)me.getHitBox().getY() + (int)me.getHitBox().getHeight();

        int eHitBoxPredictedMapX;
        int eHitBoxPredictedMapY;

        switch(me.getDirection()) {
            case "up":
                eHitBoxPredictedMapY = (eHitBoxMapY - me.getSpeed()); // moving up

                // Get contacting cells
                Cell cellTopLeft = map.getCell(eHitBoxMapX, eHitBoxPredictedMapY);
                Cell cellTopRight = map.getCell(eHitBoxMapXOffset, eHitBoxPredictedMapY);

                if (cellTopLeft.getCollision() || cellTopRight.getCollision()) return true;
                break;
            case "down":
                eHitBoxPredictedMapY = (eHitBoxMapYOffset + me.getSpeed()); // moving down

                // Get contacting cells
                Cell cellBottomLeft = map.getCell(eHitBoxMapX, eHitBoxPredictedMapY);
                Cell cellBottomRight = map.getCell(eHitBoxMapXOffset, eHitBoxPredictedMapY);

                if (cellBottomLeft.getCollision() || cellBottomRight.getCollision()) return true;
                break;
            case "left":
                eHitBoxPredictedMapX = (eHitBoxMapX - me.getSpeed()); // moving left

                // Get contacting cells
                Cell cellLeftUp = map.getCell(eHitBoxPredictedMapX, eHitBoxMapY);
                Cell cellLeftDown = map.getCell(eHitBoxPredictedMapX, eHitBoxMapYOffset);

                if (cellLeftUp.getCollision() || cellLeftDown.getCollision()) return true;
                break;
            case "right":
                eHitBoxPredictedMapX = (eHitBoxMapXOffset + me.getSpeed()); // moving right

                // Get contacting cells
                Cell cellRightUp = map.getCell(eHitBoxPredictedMapX, eHitBoxMapY);
                Cell cellRightDown = map.getCell(eHitBoxPredictedMapX, eHitBoxMapYOffset);

                if (cellRightUp.getCollision() || cellRightDown.getCollision()) return true;
                break;
        }

        return false;
    }

    /**
     * Checks if the moving entity is colliding with a reward
     * @param me the moving entity
     * @param r the reward
     * @param map the map that contains all cells
     * @return True if the moving entity is colliding with a reward
     */
    public boolean collidingWithReward(MovingEntity me, Reward r, Map map){
        int eHitBoxMapX = me.getMapPos().getX() + (int)me.getHitBox().getX();
        int eHitBoxMapXOffset = me.getMapPos().getX() + (int)me.getHitBox().getX() + (int)me.getHitBox().getWidth();
        int eHitBoxMapY = me.getMapPos().getY() + (int)me.getHitBox().getY();
        int eHitBoxMapYOffset = me.getMapPos().getY() + (int)me.getHitBox().getY() + (int)me.getHitBox().getHeight();

        int eHitBoxPredictedMapX;
        int eHitBoxPredictedMapY;

        int cellLeftX;
        int cellLeftY;
        int cellRightX;
        int cellRightY;

        if (r.getCollected()) { // reward already collected
            return false;
        }

        switch(me.getDirection()) {
            case "up":
                eHitBoxPredictedMapY = (eHitBoxMapY - me.getSpeed()); // moving up

                //checking the colliding cells
                cellLeftX = eHitBoxMapX/map.getCellSize();
                cellLeftY = eHitBoxPredictedMapY/map.getCellSize();
                cellRightX = eHitBoxMapXOffset/map.getCellSize();
                cellRightY = eHitBoxPredictedMapY/map.getCellSize();

                //checking if the colliding cells match with the rewards' position
                if ((r.getMapPos().getX() == cellLeftX && r.getMapPos().getY() == cellLeftY) ||
                    (r.getMapPos().getX() == cellRightX && r.getMapPos().getY() == cellRightY)){
                   return true; 
                } 
                break;
            case "down":
                eHitBoxPredictedMapY = (eHitBoxMapYOffset + me.getSpeed()); // moving down

                //checking the colliding cells
                cellLeftX = eHitBoxMapX/map.getCellSize();
                cellLeftY = eHitBoxPredictedMapY/map.getCellSize();
                cellRightX = eHitBoxMapXOffset/map.getCellSize();
                cellRightY = eHitBoxPredictedMapY/map.getCellSize();

                //checking if the colliding cells match with the rewards' position
                if ((r.getMapPos().getX() == cellLeftX && r.getMapPos().getY() == cellLeftY) ||
                    (r.getMapPos().getX() == cellRightX && r.getMapPos().getY() == cellRightY)){
                   return true; 
                } 
                break;
            case "left":
                eHitBoxPredictedMapX = (eHitBoxMapX - me.getSpeed()); // moving left

                //checking the colliding cells
                cellLeftX = eHitBoxPredictedMapX/map.getCellSize();
                cellLeftY = eHitBoxMapY/map.getCellSize();
                cellRightX = eHitBoxPredictedMapX/map.getCellSize();
                cellRightY = eHitBoxMapYOffset/map.getCellSize();

                //checking if the colliding cells match with the rewards' position
                if ((r.getMapPos().getX() == cellLeftX && r.getMapPos().getY() == cellLeftY) ||
                    (r.getMapPos().getX() == cellRightX && r.getMapPos().getY() == cellRightY)){
                   return true; 
                } 
                break;
            case "right":
                eHitBoxPredictedMapX = (eHitBoxMapXOffset + me.getSpeed()); // moving right

                //checking the colliding cells
                cellLeftX = eHitBoxPredictedMapX/map.getCellSize();
                cellLeftY = eHitBoxMapY/map.getCellSize();
                cellRightX = eHitBoxPredictedMapX/map.getCellSize();
                cellRightY = eHitBoxMapYOffset/map.getCellSize();

                //checking if the colliding cells match with the rewards' position
                if ((r.getMapPos().getX() == cellLeftX && r.getMapPos().getY() == cellLeftY) ||
                    (r.getMapPos().getX() == cellRightX && r.getMapPos().getY() == cellRightY)){
                   return true; 
                } 
                break;
        }

        return false;
    }

    /**
     * Starts a lose screen if the main character is colliding with a moving enemy
     * @param mc the main character
     * @param me the moving enemy
     * @param map the map that contains all cells
     */
    public void collidingWithEnemy(MainCharacter mc, MovingEnemy me, Map map){

        int mcHitBoxMapX = mc.getMapPos().getX() + (int)mc.getHitBox().getX();
        int mcHitBoxMapXOffset = mc.getMapPos().getX() + (int)mc.getHitBox().getX() + (int)mc.getHitBox().getWidth();
        int mcHitBoxMapY = mc.getMapPos().getY() + (int)mc.getHitBox().getY();
        int mcHitBoxMapYOffset = mc.getMapPos().getY() + (int)mc.getHitBox().getY() + (int)mc.getHitBox().getHeight();

        int meHitBoxMapX = me.getMapPos().getX() + (int)me.getHitBox().getX();
        int meHitBoxMapXOffset = me.getMapPos().getX() + (int)me.getHitBox().getX() + (int)me.getHitBox().getWidth();
        int meHitBoxMapY = me.getMapPos().getY() + (int)me.getHitBox().getY();
        int meHitBoxMapYOffset = me.getMapPos().getY() + (int)me.getHitBox().getY() + (int)me.getHitBox().getHeight();

        int meHitBoxPredictedMapX;
        int meHitBoxPredictedMapY;

        switch(me.getDirection()) {
            case "up":
                meHitBoxPredictedMapY = (meHitBoxMapY - me.getSpeed()); // moving up

                if (meHitBoxMapX >= mcHitBoxMapX
                    && meHitBoxMapX <= mcHitBoxMapXOffset
                    && meHitBoxPredictedMapY <= mcHitBoxMapYOffset
                    && meHitBoxPredictedMapY >= mcHitBoxMapY) {
                    game.setState(GameState.LOSE);
                }
                break;
            case "down":
                meHitBoxPredictedMapY = (meHitBoxMapYOffset + me.getSpeed()); // moving down

                if (meHitBoxMapX >= mcHitBoxMapX
                    && meHitBoxMapX <= mcHitBoxMapXOffset
                    && meHitBoxPredictedMapY <= mcHitBoxMapYOffset
                    && meHitBoxPredictedMapY >= mcHitBoxMapY) {
                    game.setState(GameState.LOSE);
                }
                break;
            case "left":
                meHitBoxPredictedMapX = (meHitBoxMapX - me.getSpeed()); // moving left

                if (meHitBoxPredictedMapX >= mcHitBoxMapX
                    && meHitBoxPredictedMapX <= mcHitBoxMapXOffset
                    && meHitBoxMapY <= mcHitBoxMapYOffset
                    && meHitBoxMapY >= mcHitBoxMapY) {
                    game.setState(GameState.LOSE);
                }
                break;
            case "right":
                meHitBoxPredictedMapX = (meHitBoxMapXOffset + me.getSpeed()); // moving left

                if (meHitBoxPredictedMapX >= mcHitBoxMapX
                    && meHitBoxPredictedMapX <= mcHitBoxMapXOffset
                    && meHitBoxMapY <= mcHitBoxMapYOffset
                    && meHitBoxMapY >= mcHitBoxMapY) {
                    game.setState(GameState.LOSE);
                }
                break;
        }

    }

    /**
     * Starts a win screen if the main character collides with an unlockable exit
     * @param mc the main character
     * @param map the map that contains all cells
     */
    public void collidingWithExit(MainCharacter mc, Map map) {

        // Get hitbox position to get its corners
        int mcHitBoxMapX = mc.getMapPos().getX() + (int)mc.getHitBox().getX();
        int mcHitBoxMapXOffset = mc.getMapPos().getX() + (int)mc.getHitBox().getX() + (int)mc.getHitBox().getWidth();
        int mcHitBoxMapY = mc.getMapPos().getY() + (int)mc.getHitBox().getY();

        int mcHitBoxPredictedMapY;

        if (mc.getDirection() == "up") {

            mcHitBoxPredictedMapY = (mcHitBoxMapY - mc.getSpeed()); // moving upw

            // Get contacting cells
            Cell cellTopLeft = map.getCell(mcHitBoxMapX, mcHitBoxPredictedMapY);
            Cell cellTopRight = map.getCell(mcHitBoxMapXOffset, mcHitBoxPredictedMapY);

            if (cellTopLeft.isUnlockable(mc) || cellTopRight.isUnlockable(mc)) {
                game.setState(GameState.WIN);
            }
        } 
    }

    /**
     * Checks if the main character is colliding with a punishment
     * @param mc the main character
     * @param p the punishment
     * @param map the map that contains all cells
     * @return True if the main character is colliding with a punishment
     */
    public boolean collidingWithPunishment(MainCharacter mc, Punishment p, Map map){
        int eHitBoxMapX = mc.getMapPos().getX() + (int)mc.getHitBox().getX();
        int eHitBoxMapXOffset = mc.getMapPos().getX() + (int)mc.getHitBox().getX() + (int)mc.getHitBox().getWidth();
        int eHitBoxMapY = mc.getMapPos().getY() + (int)mc.getHitBox().getY();
        int eHitBoxMapYOffset = mc.getMapPos().getY() + (int)mc.getHitBox().getY() + (int)mc.getHitBox().getHeight();

        int eHitBoxPredictedMapX;
        int eHitBoxPredictedMapY;

        int cellLeftX;
        int cellLeftY;
        int cellRightX;
        int cellRightY;

        switch(mc.getDirection()) {
            case "up":
                eHitBoxPredictedMapY = (eHitBoxMapY - mc.getSpeed()); // moving up

                cellLeftX = eHitBoxMapX/map.getCellSize();
                cellLeftY = eHitBoxPredictedMapY/map.getCellSize();
                cellRightX = eHitBoxMapXOffset/map.getCellSize();
                cellRightY = eHitBoxPredictedMapY/map.getCellSize();

                if ((p.getMapPos().getX() == cellLeftX && p.getMapPos().getY() == cellLeftY) ||
                        (p.getMapPos().getX() == cellRightX && p.getMapPos().getY() == cellRightY)){
                    return true;
                }
                break;
            case "down":
                eHitBoxPredictedMapY = (eHitBoxMapYOffset + mc.getSpeed()); // moving down

                cellLeftX = eHitBoxMapX/map.getCellSize();
                cellLeftY = eHitBoxPredictedMapY/map.getCellSize();
                cellRightX = eHitBoxMapXOffset/map.getCellSize();
                cellRightY = eHitBoxPredictedMapY/map.getCellSize();

                if ((p.getMapPos().getX() == cellLeftX && p.getMapPos().getY() == cellLeftY) ||
                        (p.getMapPos().getX() == cellRightX && p.getMapPos().getY() == cellRightY)){
                    return true;
                }
                break;
            case "left":
                eHitBoxPredictedMapX = (eHitBoxMapX - mc.getSpeed()); // moving left

                cellLeftX = eHitBoxPredictedMapX/map.getCellSize();
                cellLeftY = eHitBoxMapY/map.getCellSize();
                cellRightX = eHitBoxPredictedMapX/map.getCellSize();
                cellRightY = eHitBoxMapYOffset/map.getCellSize();

                if ((p.getMapPos().getX() == cellLeftX && p.getMapPos().getY() == cellLeftY) ||
                        (p.getMapPos().getX() == cellRightX && p.getMapPos().getY() == cellRightY)){
                    return true;
                }
                break;
            case "right":
                eHitBoxPredictedMapX = (eHitBoxMapXOffset + mc.getSpeed()); // moving right

                cellLeftX = eHitBoxPredictedMapX/map.getCellSize();
                cellLeftY = eHitBoxMapY/map.getCellSize();
                cellRightX = eHitBoxPredictedMapX/map.getCellSize();
                cellRightY = eHitBoxMapYOffset/map.getCellSize();

                if ((p.getMapPos().getX() == cellLeftX && p.getMapPos().getY() == cellLeftY) ||
                        (p.getMapPos().getX() == cellRightX && p.getMapPos().getY() == cellRightY)){
                    return true;
                }
                break;
        }

        return false;
    }
}
