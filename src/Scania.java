import java.awt.*;

public class Scania extends Truck { // Scania is a type of truck

    public Scania() {
        nrDoors = 2;
        color = Color.green;
        enginePower = 100;
        modelName = "src.Scania";
        bedAngle = 0;
        stopEngine();
        transportable = false;
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void open() {
        if (currentSpeed == 0) {
            bedAngle = 70;
        }
    }

}
