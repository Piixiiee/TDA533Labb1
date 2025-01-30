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
    @Test
    public void testStartEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStopEngine() {
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testMove() {
        volvo.move();
        assertEquals(volvo.getXDir() * volvo.getCurrentSpeed(), volvo.getX(), 0.001);
        assertEquals(volvo.getYDir() * volvo.getCurrentSpeed(), volvo.getY(), 0.001);
    }

    @Test
    public void testTurnLeft() {
        volvo.setXDir(1);
        volvo.setYDir(0);
        volvo.turnLeft();
        assertEquals(0, volvo.getXDir(), 0.001);
        assertEquals(1, volvo.getYDir(), 0.001);
    }

    @Test
    public void testTurnRight() {
        volvo.setXDir(-1);
        volvo.setYDir(0);
        volvo.turnRight();
        assertEquals(0, volvo.getXDir(), 0.001);
        assertEquals(1, volvo.getYDir(), 0.001);
    }

    @Test
    public void testGasVolvo() {
        double oldSpeed = volvo.getCurrentSpeed();
        volvo.gas(2);
        assertEquals(Math.min(oldSpeed + 1.25 * 2, 100), volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testBrakeVolvo() {
        double oldSpeed = volvo.getCurrentSpeed();
        volvo.brake(2);
        assertEquals(Math.max(oldSpeed - 1.25 * 2, 0), volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testGasSaab() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.setTurboOn();
        saab.gas(2);
        assertEquals((oldSpeed + (125 * 0.01 * 1.3) * 2), saab.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testBrakeSaab() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.setTurboOff();
        saab.brake(2);
        assertEquals((oldSpeed - (125 * 0.01) * 2), saab.getCurrentSpeed(), 0.001);
    }

    // sanity checks

    @Test
    public void testMaxSpeed() {
        saab.gas(300);
        assertTrue (saab.getCurrentSpeed() <= saab.getEnginePower());
        volvo.gas(300);
        assertTrue (volvo.getCurrentSpeed() <= volvo.getEnginePower());
    }

    @Test
    public void testNegGas(){
        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(200);
        assertTrue (saab.getCurrentSpeed() >= oldSpeed);
    }
}