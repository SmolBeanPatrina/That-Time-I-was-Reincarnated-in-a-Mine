package group6.main;

/**
 * Client class that creates and starts the game
 */
public class Main {

    /** Default constructor */
    public Main() {}

    /**
     * Main method
     * @param args default args
     */
    public static void main(String[] args) {
        Game mineGame = new Game();
        mineGame.startGame();
        
    }

}
