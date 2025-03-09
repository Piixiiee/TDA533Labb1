package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import java.awt.*;

public class CarTransport<T extends Vehicle> extends Truck implements Loadable<T>, HasTruckBed {

    protected Vehicle[] vehicles;

    public CarTransport() {
        nrDoors = 2;
        color = Color.blue;
        enginePower = 100;
        modelName = "Classes.CarTransport";
        stopEngine();
        bedAngle = 0;
        vehicles = new Vehicle[4];
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void move() { // Move car transport and loaded cars at current speed in X and Y directions
        if (bedAngle == 0) {
            x += getCurrentSpeed() * xDir;
            y += getCurrentSpeed() * yDir;
            for (int i = 0; i < 4; i++) {
                if (vehicles[i] != null) {
                    vehicles[i].x = x;
                    vehicles[i].y = y;
                }
            }
        }
    }

    private boolean closeEnough(Vehicle vehicle) { // Check if vehicle is close to car transport
        boolean closeX = Math.abs(x - vehicle.x) <= 1;
        boolean closeY = Math.abs(y - vehicle.y) <= 1;
        return (closeX && closeY);
    }

    public void load(T vehicle) { // If vehicle fulfills the conditions and car transport has space available, load vehicle
        if (bedAngle == 1 && (vehicle instanceof IsPersonalVehicle) && closeEnough(vehicle)) {
            for (int i = 0; i < 4; i++) {
                if (vehicles[i] == null) {
                    vehicles[i] = vehicle;
                    vehicle.x = x;
                    vehicle.y = y;
                    break;
                }
            }
        }
    }

    public void unload(T vehicle) throws Exception {
        throw new Exception("Cannot unload specific car");
    }

    public void unload() { // Unload vehicle from car transport in reverse order of loading; vehicle ends up close to car transport
        if (bedAngle == 1) {
            for (int i = 3; i >= 0; i--) {
                if (vehicles[i] != null) {
                    vehicles[i].x = x + 1;
                    vehicles[i].y = y + 1;
                    vehicles[i] = null;
                    break;
                }
            }
        }
    }
}