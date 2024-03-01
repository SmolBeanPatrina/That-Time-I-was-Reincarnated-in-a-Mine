package group6;


import group6.entity.MainCharacter;
import group6.entity.Slime;
import group6.main.KeyHandler;
import group6.main.Screen;
import group6.map.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EntityTest {

    private MainCharacter mc;
    private Slime slime;

    @Test
    public void testMCConstructor(){
        mc = new MainCharacter(new KeyHandler(), new Map(new Screen()));
        assertNotEquals(null, mc);
    }
}
