import java.awt.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMethods {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
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
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed(), 0.001);
        scania.startEngine();
        assertEquals(0.1, scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStopEngine() {
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed(), 0.001);
        scania.stopEngine();
        assertEquals(0, scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testMove() {
        volvo.move();
        assertEquals(volvo.getXDir() * volvo.getCurrentSpeed(), volvo.getX(), 0.001);
        assertEquals(volvo.getYDir() * volvo.getCurrentSpeed(), volvo.getY(), 0.001);
    }

    @Test
    public void testTurnLeft() {
        volvo.turnLeft();
        assertEquals(0, volvo.getXDir(), 0.001);
        assertEquals(1, volvo.getYDir(), 0.001);
    }

    @Test
    public void testTurnRight() {
        volvo.turnRight();
        assertEquals(0, volvo.getXDir(), 0.001);
        assertEquals(-1, volvo.getYDir(), 0.001);
    }

    @Test
    public void testGasVolvo() {
        double oldSpeed = volvo.getCurrentSpeed();
        volvo.gas(1);
        assertEquals(Math.min(oldSpeed + 1.25, 100), volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testBrakeVolvo() {
        double oldSpeed = volvo.getCurrentSpeed();
        volvo.brake(1);
        assertEquals(Math.max(oldSpeed - 1.25, 0), volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testGasSaab() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.setTurboOn();
        saab.gas(1);
        assertEquals(Math.min((oldSpeed + (125 * 0.01 * 1.3)), 125), saab.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testBrakeSaab() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.setTurboOff();
        saab.brake(1);
        assertEquals(Math.max((oldSpeed - (125 * 0.01)), 0), saab.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testGasScania() {
        double oldSpeed = scania.getCurrentSpeed();
        scania.gas(1);
        assertEquals(Math.min(oldSpeed + (100 * 0.01), 100), scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testBrakeScania() {
        double oldSpeed = scania.getCurrentSpeed();
        scania.brake(1);
        assertEquals(Math.max(oldSpeed - (100 * 0.01), 0), scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testStartEngineScania() {
        scania.stopEngine();
        scania.angleUp();
        scania.startEngine();
        assertEquals(0, scania.getCurrentSpeed(), 0.001);
        scania.angleDown();
        scania.startEngine();
        assertEquals(0.1, scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testMoveScania() {
        scania.move();
        assertEquals(scania.getXDir() * scania.getCurrentSpeed(), scania.getX(), 0.001);
        assertEquals(scania.getYDir() * scania.getCurrentSpeed(), scania.getY(), 0.001);
        scania.stopEngine();
        scania.angleUp();
        scania.move();
        assertEquals(scania.getXDir() * scania.getCurrentSpeed(), scania.getX(), 0.001);
        assertEquals(scania.getYDir() * scania.getCurrentSpeed(), scania.getY(), 0.001);
    }




    // sanity checks

    @Test
    public void testMaxSpeed() {
        saab.gas(200);
        assertTrue (saab.getCurrentSpeed() <= saab.getEnginePower());
        volvo.gas(200);
        assertTrue (volvo.getCurrentSpeed() <= volvo.getEnginePower());
        scania.gas(200);
        assertTrue (scania.getCurrentSpeed() <= scania.getEnginePower());

    }

    @Test
    public void testMinSpeed() {
        saab.brake(400);
        assertTrue (saab.getCurrentSpeed() >= 0);
        volvo.brake(400);
        assertTrue (volvo.getCurrentSpeed() >= 0);
        scania.brake(400);
        assertTrue(scania.getCurrentSpeed() >= 0);
    }

    @Test
    public void testNegGas() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(-200);
        assertTrue (saab.getCurrentSpeed() >= oldSpeed);
    }

    @Test
    public void testPosBrake() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.brake(200);
        assertTrue(saab.getCurrentSpeed() <= oldSpeed);
    }

    @Test
    public void testAngleUp() {
        scania.angleUp();
        assertEquals(70, scania.bedAngle);
    }

    @Test
    public void testAngleDown() {
        scania.angleDown();
        assertEquals(0, scania.bedAngle);
    }
}