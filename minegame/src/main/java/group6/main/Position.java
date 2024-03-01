package group6.main;

/**
 * Position class that holds a pair of coordinates (x, y)
 */
public class Position {
    private int x;
    private int y;

    /**
     * Empty constructor
     */
    public Position() {}

    /**
     * Instanties the x and y coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for x
     * @return the x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Setter method for x
     * @param X the x coordinate
     */
    public void setX(int X){
        x = X;
    }

    /**
     * Getter method for y
     * @return the y coordinate
     */
    public int getY(){
        return y;
    }
    
    /**
     * Setter method for x
     * @param Y the y coordinate
     */
    public void setY(int Y){
        y = Y;
    }
}
