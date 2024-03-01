package group6.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.imageio.ImageIO;

import group6.main.Game.GameState;
import group6.entity.MainCharacter;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class that handles GUI of the game
 */
public class GUI {

    private HashMap<String, Font> fonts;
    private String selectedButton;
    private KeyHandler keyHandler;
    private Screen screen;
    private String savedTime;
    private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    /**
     * GUI constructor
     * @param keyHandler the key handler that listens to player input
     * @param screen the screen to get display settings from
     */
    public GUI(KeyHandler keyHandler, Screen screen) {
        this.keyHandler = keyHandler;
        this.screen = screen;
        fonts = new HashMap<String, Font>();
        fonts.put("title_font", new Font("Times New Roman", Font.BOLD, 80));
        fonts.put("menu_font", new Font("Times New Roman", Font.BOLD, 30));
        loadImages("gui");
        loadImages("star");
    }

    /**
     * Getter method to get saved time
     * @return the saved time
     */
    public String getSavedTime () {
        return savedTime;
    }

    /**
     * Setter method to get saved time
     * @param time the saved time
     */
    public void setSavedTime(String time) {
        savedTime = time;
    }

    /**
     * Draws the main menu screen
     * @param g the Graphics2D object for drawing
     */
    public void drawMainMenu(Graphics2D g) {
        // Create title
        g.setFont(fonts.get("title_font"));
        g.setColor(Color.WHITE);
        g.drawString("MINE GAME", screen.getScreenWidth()/2 - screen.getCellSize()*6 + 40, screen.getCellSize()*3);

        // Create menu border
        drawMenuBorder(g, screen.getScreenWidth()/2 - screen.getCellSize()*2 + 28, screen.getCellSize()*8 - 32, screen.getCellSize()*2, screen.getCellSize() + 32);
        
        // Create menu buttons
        g.setFont(fonts.get("menu_font"));
        g.drawString("Start", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8);
        g.drawString("Exit", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32);

        g.setColor(Color.YELLOW);
        if (selectedButton == "start") {
            g.drawString("Start", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8);
        } else if (selectedButton == "exit") {
            g.drawString("Exit", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32);
        }
    }

    /**
     * Draws the in-game score and time display
     * @param g the Graphics2D object for drawing
     * @param time the current game time
     * @param mc the main character to get and display their score
     */
    public void drawScoreAndTime(Graphics2D g, String time, MainCharacter mc) {
        drawMenuBorder(g, screen.getCellSize() - 8, screen.getCellSize()/4, screen.getCellSize()*4 + 32, screen.getCellSize() + 32);
        drawScore(g, mc, screen.getCellSize(), screen.getCellSize());
        drawTime(g, time, screen.getCellSize(), screen.getCellSize()*2 - 16);
    }

    /**
     * Draws the win screen
     * @param g the Graphics2D object for drawing
     * @param mc the main character to get their score
     */
    public void drawWinScreen(Graphics2D g, MainCharacter mc) {
        drawMenuBorder(g, screen.getScreenWidth()/2 - screen.getCellSize()*5, screen.getCellSize()*3 - 24, screen.getCellSize()*9 + 32, screen.getCellSize()*6);
        
        g.setFont(fonts.get("title_font"));
        g.setColor(Color.WHITE);
        g.drawString("THE END", screen.getScreenWidth()/2 - screen.getCellSize()*4 + 8, screen.getCellSize()*4 + 16);

        g.setFont(fonts.get("menu_font"));
        drawScore(g, mc, screen.getScreenWidth()/2 - screen.getCellSize()*2, screen.getCellSize()*6 - 16);
        drawTime(g, getSavedTime(), screen.getScreenWidth()/2 - screen.getCellSize()*2, screen.getCellSize()*6 + 24);
        // Create menu buttons
        g.setFont(fonts.get("menu_font"));
        g.drawString("Retry", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 - 25);
        g.drawString("Menu", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32 - 25);

        g.setColor(Color.YELLOW);
        if (selectedButton == "retry") {
            g.drawString("Retry", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 - 25);
        } else if (selectedButton == "menu") {
            g.drawString("Menu", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32 - 25);
        }  
    }

    /**
     * Draws the lose ("GAME OVER") screen
     * @param g the Graphics2D object for drawing
     */
    public void drawLoseScreen(Graphics2D g) {
        g.setFont(fonts.get("title_font"));
        g.setColor(Color.WHITE);
        g.drawString("GAME", screen.getScreenWidth()/2 - screen.getCellSize()*3 + 16, screen.getCellSize()*3);
        g.drawString("OVER", screen.getScreenWidth()/2 - screen.getCellSize()*3 + 24, screen.getCellSize()*4 + 16);

        // Create menu buttons
        g.setFont(fonts.get("menu_font"));
        g.drawString("Retry", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 - 5);
        g.drawString("Menu", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32 - 5);

        g.setColor(Color.YELLOW);
        if (selectedButton == "retry") {
            g.drawString("Retry", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 - 5);
        } else if (selectedButton == "menu") {
            g.drawString("Menu", screen.getScreenWidth()/2 - screen.getCellSize(), screen.getCellSize()*8 + 32 - 5);
        }  
    }

    private void drawMenuBorder(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        g.fillRect( x, y, width, height);
        g.setStroke(new BasicStroke(4));
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect( x - 4, y + 4, width, height);
        g.setColor(Color.WHITE);
        g.drawRect( x, y, width, height);
    }

    private void drawTime(Graphics2D g, String time, int x, int y) {
        g.setFont(fonts.get("menu_font"));
        g.setColor(Color.WHITE);
        g.drawString("Time: " + time, x, y);
    }

    private void drawScore(Graphics2D g, MainCharacter mc, int x, int y) {
        g.setFont(fonts.get("menu_font"));
        g.setColor(Color.WHITE);
       g.drawString("Score: " + mc.getScore(), x, y);
    }

    /**
     * Loads the images for the collected stars indicator
     * @param className refactor later
     */
    public void loadImages(String className){
        if (className == "gui"){
            loadImage("star_shell", "/sprites/" + className + "/" + className + "_star_shell.png");
        }else if (className == "star"){
            loadImage("top", "/sprites/"  + className + "/" + className + "_top.png");
            loadImage("left", "/sprites/" + className + "/" + className + "_left.png");
            loadImage("right", "/sprites/" + className + "/" + className + "_right.png");
            loadImage("bottom_left", "/sprites/" + className + "/" + className + "_bottom_left.png");
            loadImage("bottom_right", "/sprites/" + className + "/" + className + "_bottom_right.png");
            loadImage("collected", "/sprites/" + className + "/" + className + "_collected.png");
            loadImage("blank", "/sprites/" + className + "/" + className + "_blank.png");
        }
        
    }

    /**
     * Loads the images of the collected stars indicator
     * @param key the key to the images HashMap
     * @param path the resource path to the image
     */
    public void loadImage(String key, String path){
        try{
            images.put(key, ImageIO.read(getClass().getResourceAsStream(path)));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMainMenu(Game game) {
        if (keyHandler.getUpPressed()) {
            selectedButton = "start";
        }
        if (keyHandler.getDownPressed()) {
            selectedButton = "exit";
        }
        if (keyHandler.getEnterPressed()) {
            if (selectedButton == "start") {
                game.setState(GameState.IN_GAME);
            } else if (selectedButton == "exit") {
                game.setState(GameState.CLOSING);
            }
        }
    }

    private void handleInGame(Game game, MainCharacter mc) {
        if (mc.getScore() < 0) {
            game.setState(GameState.LOSE);
        }
    }

    private void handleWinScreen(Game game) {
        if (keyHandler.getUpPressed()) {
            selectedButton = "retry";
        }
        if (keyHandler.getDownPressed()) {
            selectedButton = "menu";
        }

        if (keyHandler.getEnterPressed()){
            if (selectedButton == "retry"){
                game.resetGame();
                game.setState(GameState.IN_GAME);
            }else if (selectedButton == "menu"){
                game.resetGame();
                game.setState(GameState.MAIN_MENU);
            }
        }
    }

    private void handleLoseScreen(Game game) {
        if (keyHandler.getUpPressed()) {
            selectedButton = "retry";
        }
        if (keyHandler.getDownPressed()) {
            selectedButton = "menu";
        }

        if (keyHandler.getEnterPressed()){
            if (selectedButton == "retry"){
                game.resetGame();
                game.setState(GameState.IN_GAME);
            }else if (selectedButton == "menu"){
                game.resetGame();
                game.setState(GameState.MAIN_MENU);
            }
        }
    }
    
    /**
     * Updates the GUI based on the game state
     * @param game the game to ask its state
     * @param mc the main character
     * @param time the time to constantly update the saved time
     */
    public void update(Game game, MainCharacter mc, String time) {
        switch(game.getState()) {
            case MAIN_MENU:
                handleMainMenu(game);
                break;
            case IN_GAME: 
                setSavedTime(time);
                handleInGame(game, mc);
                break;
            case WIN:
                handleWinScreen(game);
                break;
            case LOSE:
                handleLoseScreen(game);
                break;
            case CLOSING: break;
        }
    }
    
}
