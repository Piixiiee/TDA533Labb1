package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import java.awt.*;

public class Scania extends Truck implements HasTruckBed { // Classes.Scania is a type of truck

    public Scania() {
        nrDoors = 2;
        color = Color.green;
        enginePower = 100;
        modelName = "Classes.Scania";
        bedAngle = 0;
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void lower() { // Classes.Truck bed angles to unload
        if (currentSpeed == 0 && bedAngle < 70) {
            bedAngle += 10;
        }
    }

    @Override
    public void raise() { // Classes.Truck bed angles to neutral position
        if (currentSpeed == 0 && bedAngle > 0) {
            bedAngle -= 10;
        }
    }

}
