package group6.entity;

import group6.main.CollisionHandler;
import group6.main.KeyHandler;
import group6.map.Map;
import group6.main.PlayerMovementHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for the player-controlled main character
 */
public class MainCharacter extends MovingEntity {
    private int score = 0;
    private boolean idle;
    private KeyHandler keyHandler;
    private PlayerMovementHandler playerMovementHandler;
    private Map map;
    private int starsCollected = 0;
    private int stunTimer = 0;
    private final int stunTime = 10; // How long main character is to be stunned for

    /** refactor later */
    public boolean isStunned = false; 
    
    /**
     * Main character constructor
     * @param keyHandler the key handler the listens to player input
     * @param map the map to interface with cell collision
     */
    public MainCharacter(KeyHandler keyHandler, Map map){ 
        this.keyHandler = keyHandler;
        this.playerMovementHandler = new PlayerMovementHandler(this, keyHandler);
        this.map = map;
        setMapPos(map.getCellSize()*1, map.getCellSize()*20); 
        setScreenPos(
            (map.getScreenWidth() - map.getCellSize())/2, 
            (map.getScreenHeight() - map.getCellSize())/2
        ); //Initializes character to centre of screen
        setSpeed(12); //Sets the player speed (constant)
        setHitBox(8, 16, 32,24);
        setHitBoxDefaultX(8);
        setHitBoxDefaultY(16);
        loadImages("mc");
        setSpriteSwitchCounter(0);
        setSpriteSwitch(false);
    }

    /**
     * Draws the main character based on direction and game clock tick
     * @param g the Graphics2D object for drawing
     * @param e Unused for the main character - set this to null
     */
    public void draw(Graphics2D g, Entity e){
        BufferedImage image = null;

        // Update sprite frame based on direction
        switch(getDirection()){
            case "up":
                if (idle)
                    image = getImage("idle_up");
                else if (getSpriteSwitch())
                    image = getImage("up1");
                else
                    image = getImage("up2");
                break;
            case "down":
                if (idle)
                    image = getImage("idle_down");
                else if (getSpriteSwitch())
                    image = getImage("down1");
                else
                    image = getImage("down2");
                break;
            case "left":
                if (idle)
                    image = getImage("idle_left");
                else if (getSpriteSwitch())
                    image = getImage("left1");
                else
                    image = getImage("left2");
                break;
            case "right":
                if (idle)
                    image = getImage("idle_right");
                else if (getSpriteSwitch())
                    image = getImage("right1");
                else
                    image = getImage("right2");
                break; 
        }
        g.drawImage(image, getScreenPos().getX(), getScreenPos().getY(), map.getCellSize(), map.getCellSize(), null);
    }

    /**
     * Getter method for score
     * @return score
     */
    public int getScore(){
        return score;
    }

    /**
     * Setter method for score
     * @param s score
     */
    public void setScore(int s){
        score = s;
    }

    // public void pickUpReward(){

    // }

    @Override
    public void loadImages(String className) {
        super.loadImages(className);
        loadImage("idle_up", "/sprites/" + className + "/" + className + "_idle_up.png");
        loadImage("idle_down", "/sprites/" + className + "/" + className + "_idle_down.png");
        loadImage("idle_left", "/sprites/" + className + "/" + className + "_idle_left.png");
        loadImage("idle_right", "/sprites/" + className + "/" + className + "_idle_right.png");
    }

    /**
     * Updates player position based on key inputs
     * @param collisionHandler Needed to check cell collision
     */
    public void update(CollisionHandler collisionHandler){

        if(!isStunned){
            if (playerMovementHandler.playerIsIdle()) {
                idle = true;
                return;
            }

            // Player wants to move
            idle = false;

            playerMovementHandler.updatePlayerDirection();

            collisionHandler.collidingWithExit(this, map);

            if (!collisionHandler.colldingWithCell(this, map)) { // MC is not colliding
                playerMovementHandler.updatePlayerMovement();
            }

            if (getSpriteSwitchCounter() % 5 == 0) {
                setSpriteSwitch(!getSpriteSwitch());
                setSpriteSwitchCounter(0);
            }

            setSpriteSwitchCounter(getSpriteSwitchCounter() + 1);
        } else {
            if(stunTimer >= stunTime){
                isStunned = false;
                stunTimer = 0;
            } else {
                stunTimer += 1;
            }
        }
    }

    /**
     * Getter method for collected stars
     * @return the number of stars collected
     */
    public int getStarsCollected() {
        return starsCollected;
    }

    /**
     * Setter method for collected stars
     * @param starsCollected the number of stars collected
     */
    public void setStarsCollected(int starsCollected) {
        this.starsCollected = starsCollected;
    }

    /**
     * Resets to default value (refactor later)
     */
    public void resetGame() {
        isStunned = false;
    }
}
