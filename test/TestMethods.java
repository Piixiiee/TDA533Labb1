import java.awt.*;

import Interfaces.IsPersonalVehicle;
import Model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMethods {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private CarTransport carTransport;
    private CarWorkshop<Volvo240> volvoWorkshop;
    private CarWorkshop<Scania> scaniaWorkshop;
    private CarWorkshop<IsPersonalVehicle> carWorkshop;
    private CarWorkshop<Vehicle> generalWorkshop;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        carTransport = new CarTransport();
        volvoWorkshop = new CarWorkshop<Volvo240>(5);
        scaniaWorkshop = new CarWorkshop<Scania>(2);
        carWorkshop = new CarWorkshop<IsPersonalVehicle>(3);
        generalWorkshop = new CarWorkshop<Vehicle>(4);
    }

    @Test
    public void testSaabDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void testVolvoDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void testSaabColors() {
        saab.setColor(Color.RED);
        assertEquals(Color.RED, saab.getColor());
    }

    @Test
    public void testVolvoColors() {
        volvo.setColor(Color.GREEN);
        assertEquals(Color.GREEN, volvo.getColor());
    }

    @Test
    public void testSaabEnginePower() {
        assertEquals(125, saab.getEnginePower(), 0.001);
    }

    @Test
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
        carTransport.startEngine();
        assertEquals(0.1, carTransport.getCurrentSpeed(), 0.001 );
    }

    @Test
    public void testStopEngine() {
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed(), 0.001);
        scania.stopEngine();
        assertEquals(0, scania.getCurrentSpeed(), 0.001);
        carTransport.stopEngine();
        assertEquals(0, carTransport.getCurrentSpeed(), 0.001);
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
        scania.lower();
        scania.startEngine();
        assertEquals(0, scania.getCurrentSpeed(), 0.001);
        scania.raise();
        scania.startEngine();
        assertEquals(0.1, scania.getCurrentSpeed(), 0.001);
    }

    @Test
    public void testMoveScania() {
        scania.move();
        assertEquals(scania.getXDir() * scania.getCurrentSpeed(), scania.getX(), 0.001);
        assertEquals(scania.getYDir() * scania.getCurrentSpeed(), scania.getY(), 0.001);
        scania.stopEngine();
        scania.raise();
        scania.move();
        assertEquals(scania.getXDir() * scania.getCurrentSpeed(), scania.getX(), 0.001);
        assertEquals(scania.getYDir() * scania.getCurrentSpeed(), scania.getY(), 0.001);
    }

    @Test
    public void testLoadCarTransport() { // Load saab and volvo on carTransport, check placement and x & y values
        saab.x = carTransport.x + 1;
        saab.y = carTransport.y - 1;
        volvo.x = carTransport.x - 1;
        volvo.y = carTransport.y + 1;
        carTransport.lower();
        carTransport.load(saab);
        carTransport.load(volvo);
        carTransport.raise();
        assertEquals(saab, carTransport.vehicles[0]);
        assertEquals(volvo, carTransport.vehicles[1]);
        assertEquals(carTransport.x, saab.x, 0.001);
        assertEquals(carTransport.y, saab.y, 0.001);
    }

    @Test
    public void testUnloadCarTransport() {
        testLoadCarTransport();
        carTransport.unload(); // Try to unload vehicle when bedAngle is up - should not work
        assertEquals(volvo, carTransport.vehicles[1]);
        carTransport.lower();
        carTransport.unload(); // Unload now that bedAngle is down
        carTransport.raise();
        assertEquals(saab, carTransport.vehicles[0]);
        assertNull(carTransport.vehicles[1]);
        assertEquals(saab.x, carTransport.x, 0.001);
        assertEquals(saab.y, carTransport.y, 0.001);
        assertEquals(carTransport.x + 1, volvo.x, 0.001);
        assertEquals(carTransport.y + 1, volvo.y, 0.001);
    }

    @Test
    public void testMoveCarTransport() {
        testUnloadCarTransport();
        double oldVolvoX = carTransport.x + 1;
        double oldVolvoY = carTransport.y + 1;
        carTransport.startEngine();
        carTransport.move();
        carTransport.move();
        assertEquals(carTransport.x, saab.x, 0.001);
        assertEquals(carTransport.y, saab.y, 0.001);
        assertEquals(oldVolvoX, volvo.x, 0.001);
        assertEquals(oldVolvoY, volvo.y, 0.001);
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
        carTransport.gas(200);
        assertTrue(carTransport.getCurrentSpeed() <= carTransport.getEnginePower());

    }

    @Test
    public void testMinSpeed() {
        saab.brake(400);
        assertTrue (saab.getCurrentSpeed() >= 0);
        volvo.brake(400);
        assertTrue (volvo.getCurrentSpeed() >= 0);
        scania.brake(400);
        assertTrue(scania.getCurrentSpeed() >= 0);
        carTransport.brake(400);
        assertTrue(carTransport.getCurrentSpeed() >= 0);
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
        scania.lower();
        assertEquals(10, scania.bedAngle);
    }

    @Test
    public void testAngleDown() {
        scania.raise();
        assertEquals(0, scania.bedAngle);
    }

    @Test
    public void testCarWorkshopException() {
        testTurnInVehicle();
        Exception exception = assertThrows(Exception.class, () -> {
            carWorkshop.unload();
        });
        String expectedMessage = "Must specify car";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCarTransportException() {
        testLoadCarTransport();
        Exception exception = assertThrows(Exception.class, () -> {
            carTransport.unload(volvo);
        });
        String expectedMessage = "Cannot unload specific car";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

// Classes.CarWorkshop tests

    @Test
    public void testTurnInVehicle() {
        generalWorkshop.load(volvo);
        generalWorkshop.load(scania);
        assertEquals(2, generalWorkshop.vehicles.size());
        assertEquals(generalWorkshop.vehicles.get(0), volvo);
        assertEquals(generalWorkshop.vehicles.get(1), scania);
    }

    @Test
    public void testTurnInWrongVehicle() {
        carWorkshop.load(volvo);
        assertEquals(1, carWorkshop.vehicles.size());
        assertEquals(carWorkshop.vehicles.get(0), volvo);
    }

    @Test
    public void testGetVehicle() {
        testTurnInVehicle();
        generalWorkshop.unload(volvo);
        assertEquals(1, generalWorkshop.vehicles.size());
        assertEquals(generalWorkshop.vehicles.get(0), scania);
    }

    @Test
    public void testCarWorkshopCapacity() {
        Scania scania2 = new Scania();
        Scania scania3 = new Scania();
        scaniaWorkshop.load(scania);
        scaniaWorkshop.load(scania2);
        scaniaWorkshop.load(scania3);
        assertEquals(2, scaniaWorkshop.vehicles.size());
    }

    @Test
    public void testCarWorkshopContains() {
        volvoWorkshop.load(volvo);
        volvoWorkshop.load(volvo);
        assertEquals(1, volvoWorkshop.vehicles.size());
    }

}