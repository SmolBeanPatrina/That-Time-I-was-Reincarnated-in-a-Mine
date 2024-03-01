package group6.main;
import group6.entity.MainCharacter;
import group6.map.Map;
import group6.spawner.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Creates the game and has the game loop.
 */
public class Game extends JPanel implements Runnable {
    /** The game thread to start with run */
    private Thread gameThread;
    /** The FPS of the game */
    private int FPS = 30;
    /** The game timer in seconds */
    private double timer = 0;
    /** The game window */
    private JFrame window;
    /** For display settings */
    private Screen screen;
    /** For GUI interaction */
    private GUI gui;
    /** For the player to control */
    private MainCharacter mc;
    /** For handling player input */
    private KeyHandler keyHandler;
    /** For handling entity collision */
    private CollisionHandler collisionHandler;
    /** For spawning and updating star pieces */
    private StarSpawner starSpawner;
    /** For spawning and updating moving enemies */
    private MovingEnemySpawner slimeSpawner;
    /** For spawning and updating traps */
    private TrapSpawner trapSpawner;
    /** For spawning and updating coins */
    private CoinSpawner coinSpawner;
    /** Holds the map */
    private Map map;

    /**
     * States of the game
     */
    public enum GameState {
        /** Game is in the main menu */
        MAIN_MENU,
        /** Player is currently playing */
        IN_GAME,
        /** Player won the game */   
        WIN,
        /** Player lost the game */
        LOSE,
        /** Game wants to close its window */
        CLOSING
    }
    /** Holds the game state */
    private GameState state = GameState.MAIN_MENU;

    /**
     * Game constructor
     */
    public Game(){
        this.screen = makeScreen();
        this.keyHandler = makeKeyHandler();
        this.collisionHandler = makeCollisionHandler(this);
        this.gui = makeGUI(keyHandler, screen);
        this.map = makeMap(screen);
        this.mc = makePlayer(keyHandler, map);
        this.starSpawner = new StarSpawner(map);
        this.trapSpawner = makeTrapSpawner(map);
        this.slimeSpawner = makeSlimeSpawner(map);
        this.coinSpawner = makeCoinSpawner(map);

        starSpawner.setSpawns();
        slimeSpawner.setSpawns();
        trapSpawner.setSpawns();
        this.setPreferredSize(new Dimension(screen.getScreenWidth(), screen.getScreenHeight()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * Create a game window.
     */
    private void createWindow() {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(this);

        window.pack();

        window.setTitle("That Time I Was Transported into A Mine");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Starts the game thread
     */
    private void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Creates the game window and starts the game loop
     */
    public void startGame() {
        createWindow();
        startThread();
    }

    /**
     * Convert time (s) to a string MM:SS.ss
     * @param timeSec
     * @return
     */
    private String formatTime(double timeSec) {
        int min = (int) timeSec/60;
        double sec = timeSec%60;

        String timeFormatted = String.format("%02d:%.2f", min, sec);
        return timeFormatted;
    }

    /**
     * Main drawing method
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D G2D = (Graphics2D) g;

        switch(state) {
            case MAIN_MENU:
                gui.drawMainMenu(G2D);
                break;
            case IN_GAME:
                map.draw(G2D, mc);
                trapSpawner.draw(G2D, mc);
                mc.draw(G2D, null);
                starSpawner.draw(G2D, mc);
                slimeSpawner.draw(G2D, mc);
                coinSpawner.draw(G2D, mc);
                gui.drawScoreAndTime(G2D, formatTime(timer), mc);
                break;
            case WIN:
                map.draw(G2D, mc);
                gui.drawWinScreen(G2D, mc);
                break;
            case LOSE:
                gui.drawLoseScreen(G2D);
                break;
            case CLOSING: break; // do nothing
        }

        G2D.dispose();

    }


    /**
     * Contains the game loop
     */
    @Override
    public void run(){

        // Use delta time method: https://fulmanski.pl/zajecia/tippgk/zajecia_20162017/wyklad_cwiczenia_moje/game_loop_and_time.pdf
        double deltaTime = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        double targetFrameTime = 1e9/FPS; // convert to nanoseconds
        double accumulator = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;
            accumulator += deltaTime;
            timer += deltaTime/1e9;
            // System.out.println("Timer (s): " + timer);

            while (accumulator > targetFrameTime) {
                // Update component info 
                update();
                // Draw components
                repaint();

                accumulator -= targetFrameTime;
            }
        }
    }

    /**
     * Factory method that makes a main character
     * @param keyHandler the key handler to listen to player inputs
     * @param map the map to draw onto the screen relative to the player's position
     * @return a new MainCharacter object
     */
    public MainCharacter makePlayer(KeyHandler keyHandler, Map map) {
        return new MainCharacter(keyHandler, map);
    }

    /**
     * Factory method that makes a key handler
     * @return a new KeyHandler object
     */
    public KeyHandler makeKeyHandler(){
        return new KeyHandler();
    }

    /**
     * To be used in game loop. Updates information in the game.
     */
    public void update(){
        gui.update(this, mc, formatTime(timer));

        switch(state) {
            case MAIN_MENU:
                timer = 0;
                break;
            case IN_GAME:
                mc.update(collisionHandler);
                starSpawner.update(collisionHandler, mc);
                trapSpawner.update(collisionHandler, mc);
                slimeSpawner.update(collisionHandler, mc);
                coinSpawner.update(collisionHandler, mc);
                break;
            case WIN:
                break;
            case LOSE:
                break;
            case CLOSING:
                window.dispose();
                System.exit(0);
                break;
        }
        
    }

    /**
     * Factory method that makes a screen
     * @return a new Screen object
     */
    public Screen makeScreen(){
        return new Screen();
    }

    /**
     * Factory method that makes a GUI
     * @param keyHandler the keyhandler to listen to player input
     * @param screen the screen to draw on
     * @return a new GUI object
     */
    public GUI makeGUI(KeyHandler keyHandler, Screen screen){
        return new GUI(keyHandler, screen);
    }

    /**
     * Factory method that makes a map
     * @param screen the screen to get display settings from
     * @return a new Map object
     */
    public Map makeMap(Screen screen){
        return new Map(screen);
    }

    /**
     * Factory method that makes a collision handler
     * @param game this game class
     * @return a new collision handler object
     */
    public CollisionHandler makeCollisionHandler(Game game){
        return new CollisionHandler(game);
    }

    /**
     * Factory method that makes a Slime spawner
     * @param map the map to set spawn points
     * @return a new SlimeSpawner object
     */
    public SlimeSpawner makeSlimeSpawner(Map map){
        return new SlimeSpawner(map);
    }

    /**
     * Factory method that makes a Trap spawner
     * @param map the map to set spawn points
     * @return a new TrapSpawner object
     */
    public TrapSpawner makeTrapSpawner(Map map){
        return new TrapSpawner(map);
    }

    /**
     * Factory method that makes a Reward spawner
     * @param map the map to set spawn points
     * @return a new RewardSpawner object
     */
    public CoinSpawner makeCoinSpawner(Map map){
        return new CoinSpawner(map);
    }

    /**
     * Getter method for game's state
     * @return the current state of the game
     */
    public GameState getState() {
        return state;
    }

    /**
     * Setter method for game's state
     * @param state the state of the game
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Resets game by setting all values back to default
     */
    public void resetGame(){
        timer = 0;
        mc.setScore(0);
        mc.setStarsCollected(0);
        mc.setMapPos(map.getCellSize()*1, map.getCellSize()*20);
        starSpawner.resetGame();
        slimeSpawner.resetGame();
        coinSpawner.resetGame();
        trapSpawner.resetGame();
        trapSpawner.setSpawns();
        slimeSpawner.setSpawns();
        mc.resetGame();
    }

}
