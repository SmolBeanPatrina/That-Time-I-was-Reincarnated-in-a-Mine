package group6.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class to handle key inputs from player
 */
public class KeyHandler implements KeyListener {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean enterPressed;

    /**
     * Empty constructor
     */
    public KeyHandler(){}

    /**
     * Getter method for upPressed
     * @return upPressed
     */
    public boolean getUpPressed() {return upPressed;}

    /**
     * Getter method for downPressed
     * @return downPressed
     */
    public boolean getDownPressed() {return downPressed;}

    /**
     * Getter method for leftPressed
     * @return leftPressed
     */
    public boolean getLeftPressed() {return leftPressed;}

    /**
     * Getter method for rightPressed
     * @return rightPressed
     */
    public boolean getRightPressed() {return rightPressed;}

    /**
     * Getter method for enterPressed
     * @return enterPressed
     */
    public boolean getEnterPressed() {return enterPressed;}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int code  = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }
}