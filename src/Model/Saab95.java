package Model;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import java.awt.*;

public class Saab95 extends Vehicle implements IsPersonalVehicle { // Classes.Saab95 is a type of car

    private boolean turboOn;
    
    public Saab95() {
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
	    turboOn = false;
        modelName = "Classes.Saab95";
        stopEngine();
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    protected double speedFactor() { // Returns possible speed change
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
