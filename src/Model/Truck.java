package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
public abstract class Truck extends Vehicle { // Class for all vehicles of type truck

    protected int bedAngle;

    public void lower() {
        if (currentSpeed == 0) {
            bedAngle = 1;
        }
    }
    public void raise() {
        if (currentSpeed == 0) {
            bedAngle = 0;
        }
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
