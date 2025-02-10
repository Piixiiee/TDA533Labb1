import java.awt.*;

public class Scania extends Truck implements HasTruckBed { // Scania is a type of truck

    public Scania() {
        nrDoors = 2;
        color = Color.green;
        enginePower = 100;
        modelName = "Scania";
        bedAngle = 0;
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void lower() { // Truck bed angles to unload
        if (currentSpeed == 0 && bedAngle < 70) {
            bedAngle += 10;
        }
    }

    @Override
    public void raise() { // Truck bed angles to neutral position
        if (currentSpeed == 0 && bedAngle > 0) {
            bedAngle -= 10;
        }
    }

}
