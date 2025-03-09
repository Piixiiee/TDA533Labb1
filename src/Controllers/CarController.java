package Controllers;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    protected Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void initCC(CarController cc) {
        // Instance of this class
        cc.cars.add(new Volvo240());
        cc.cars.add(new Scania());
        cc.cars.add(new Saab95());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     **/
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                if(car.getX() >= (frame.getWidth() - 100) || car.getX() <= 0) {
                   car.turnLeft();
                   car.turnLeft();
                }
                else if (car instanceof Volvo240 && car.getX() >= 300){
                    car.stopEngine();
                }
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.getDrawPanel().moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.getDrawPanel().repaint();
            }
        }
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            if (car.getCurrentSpeed() > 0) {
                car.gas(gas);
            }
        }
    }

    // Calls the brake method for each car once
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    // Calls the startEngine method for each car once
    public void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    // Calls the stopEngine method for each car once
    public void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    // Calls the turbo on method for each saab
    public void turboOn() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Classes.Saab95")) {
                Saab95 saab = (Saab95) car;
                saab.setTurboOn();
            }
        }
    }

    // Calls the turbo off method for each saab
    public void turboOff() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Classes.Saab95")) {
                Saab95 saab = (Saab95) car;
                saab.setTurboOff();
            }
        }
    }

    // Calls the lower method for scania truck
    public void lower() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Classes.Scania")) {
                Scania scania = (Scania) car;
                scania.lower();
            }
        }
    }

    // Calls the raise method for scania truck
    public void raise() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Classes.Scania")) {
                Scania scania = (Scania) car;
                scania.raise();
            }
        }
    }

    public void add() {
        if(cars.size() < 7){
            cars.add(new Volvo240());

        }
    }

    public void remove() {
        if (cars.size() > 0 ) {
            cars.removeLast();
        }
    }
}



