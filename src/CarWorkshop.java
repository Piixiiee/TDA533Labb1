import java.awt.*;
import java.util.*;

public class CarWorkshop<T extends Vehicle> implements Loadable {

    protected ArrayList<T> vehicles;
    public int capacity;

    public CarWorkshop(int cap) {
        vehicles = new ArrayList<>(cap);
        capacity = cap;
    }

    public void turnInVehicle(T vehicle) {
        if (!vehicles.contains(vehicle) && vehicles.size() < capacity) {
            vehicles.add(vehicle);
        }
    }

    public void getVehicle(T vehicle) {
        vehicles.remove(vehicle);
        printVehicleInfo(vehicle);
    }

    public void printVehicleInfo(T vehicle) {
        System.out.println("This vehicle is of model " + vehicle.modelName);
    }

}