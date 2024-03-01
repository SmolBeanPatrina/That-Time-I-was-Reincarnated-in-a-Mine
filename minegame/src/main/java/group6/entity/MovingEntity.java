package group6.entity;

/**
 * Generic class for a moving entity
 */
public abstract class MovingEntity extends Entity {
    // //[idle, up, down, left, right]
    private int speed;
    private boolean spriteSwitch; // To help with sprite animation
    private int spriteSwitchCounter;
    private String direction = "down";

    /**
     * Empty constructor
     */
    public MovingEntity() {}

    /**
     * Getter method for speed
     * @return the speed of the moving entity
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Setter method for speed
     * @param s the speed of the moving entity
     */
    public void setSpeed(int s){
        speed = s;
    }

    /**
     * Getter method for the sprite switch
     * @return the value of sprite switch of the moving entity
     */
    public boolean getSpriteSwitch(){
        return spriteSwitch;
    }

    /**
     * Setter method for the sprite switch
     * @param b the value of sprite switch of the moving entity
     */
    public void setSpriteSwitch(boolean b){
        spriteSwitch = b;
    }

    /**
     * Setter method for the sprite switch counter
     * @return the sprite switch counter
     */
    public int getSpriteSwitchCounter(){
        return spriteSwitchCounter;
    }

    /**
     * Setter method for the sprite switch counter
     * @param s the sprite switch counter
     */
    public void setSpriteSwitchCounter(int s){
        spriteSwitchCounter = s;
    }

    /**
     * Getter method for direction
     * @return the direction of the moving enemy
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Setter method for direction
     * @param d the direction of the moving enemy
     */
    public void setDirection(String d){
        direction = d;
    }

    // public BufferedImage getImage(int index){
    //     return image[index];
    // }

    /**
     * Load images for sprite animation
     * @param className the name of the moving entity
     */
    public void loadImages(String className){

        // try{
        //     image[i] = ImageIO.read(getClass().getResourceAsStream(path));
        // }catch(IOException e){
        //     e.printStackTrace();
        // }
        // will become for e.g.
        // loadImage("up", "sprites/" + className + "/" + className + "/_up.png"); 
        // when sprites are ready
        // System.out.println("Path: " + "/sprites/" + className + "/" + className + "_idle.png");
        loadImage("up1", "/sprites/" + className + "/" + className + "_up1.png");
        loadImage("down1", "/sprites/" + className + "/" + className + "_down1.png");
        loadImage("left1", "/sprites/" + className + "/" + className + "_left1.png");
        loadImage("right1", "/sprites/" + className + "/" + className + "_right1.png");
        loadImage("up2", "/sprites/" + className + "/" + className + "_up2.png");
        loadImage("down2", "/sprites/" + className + "/" + className + "_down2.png");
        loadImage("left2", "/sprites/" + className + "/" + className + "_left2.png");
        loadImage("right2", "/sprites/" + className + "/" + className + "_right2.png");

    }

    // public void resetGame(){
        
    // }
}
