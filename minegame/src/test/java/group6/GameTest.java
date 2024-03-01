package group6;

import group6.main.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setup(){
        game = new Game();
    }

    @AfterEach
    public void reset(){
        game = null;
    }

    @Test
    public void testGameStart(){
        game.startGame();
        assertEquals(Game.GameState.MAIN_MENU, game.getState());
    }

}
