package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import java.util.*;

public class CarWorkshop<T extends IsVehicle> implements Loadable<T> {

    protected ArrayList<T> vehicles;
    public int capacity;

    public CarWorkshop(int cap) {
        vehicles = new ArrayList<T>(cap);
        capacity = cap;
    }

    public void load(T vehicle) {  // Turn in car to workshop for storage
        if (!vehicles.contains(vehicle) && vehicles.size() < capacity) {
            vehicles.add(vehicle);
        }
    }

    public void unload(T vehicle) { // Retrieve car with model info
        vehicles.remove(vehicle);
        printVehicleInfo(vehicle);
    }

    public void unload() throws Exception {
        throw new Exception("Must specify car");
    }

    public void printVehicleInfo(T vehicle) {
        System.out.println("This vehicle is of model " + vehicle.getModelName());
    }

}