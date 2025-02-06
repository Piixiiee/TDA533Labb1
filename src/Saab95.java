import java.awt.*;

public class Saab95 extends Car { // Saab95 is a type of car

    private boolean turboOn;
    
    public Saab95() {
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
	    turboOn = false;
        modelName = "src.Saab95";
        stopEngine();
        transportable = true;
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
