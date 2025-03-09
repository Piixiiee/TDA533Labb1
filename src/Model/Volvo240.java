package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import java.awt.*;

public class Volvo240 extends Vehicle implements IsPersonalVehicle { // Classes.Volvo240 is a type of car

    private final static double trimFactor = 1.25;

    public Volvo240() {
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Classes.Volvo240";
        stopEngine();
    }

    @Override
    protected double speedFactor() { // Returns possible speed change
        return enginePower * 0.01 * trimFactor;
    }
}
