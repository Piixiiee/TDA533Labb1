import java.awt.*;

public class Scania extends Vehicle implements Loadable{

    protected int bedAngle;

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

    public void angleUp() {
        if (currentSpeed == 0) {
            bedAngle = 70;
        }
    }

    public void angleDown() {
        bedAngle = 0;
    }

    @Override
    public void startEngine(){
        if (bedAngle == 0) {
            currentSpeed = 0.1;
        }
    }

    @Override
    public void move() { // Move car at current speed in X and Y directions
        if (bedAngle == 0) {
            x += getCurrentSpeed() * xDir;
            y += getCurrentSpeed() * yDir;
        }
    }

}
