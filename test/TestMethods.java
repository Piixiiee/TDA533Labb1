import java.awt.*;
//import java.util.Random;
import src.Saab95;
import src.Volvo240;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMethods {
    private Saab95 saab;
    private Volvo240 volvo;
    //private Random random;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        //random = new Random();
    }

    @Test //check amount of doors on Saab
    public void testSaabDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test //check amount of doors on Volvo
    public void testVolvoDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test //test color of Saab
    public void testSaabColors() {
        saab.setColor(Color.RED);
        assertEquals(Color.RED, saab.getColor());
    }

    @Test //test color of Volvo
    public void testVolvoColors() {
        volvo.setColor(Color.GREEN);
        assertEquals(Color.GREEN, volvo.getColor());
    }

    @Test //test engine power of Saab
    public void testSaabEnginePower() {
        assertEquals(125, saab.getEnginePower(), 0.001);
    }

    @Test //test engine power of Volvo
    public void testVolvoEnginePower() {
        assertEquals(100, volvo.getEnginePower(), 0.001);
    }

}