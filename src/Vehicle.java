import java.awt.*;

public abstract class Vehicle implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed = 0; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double x = 0; // The x position of the car
    protected double y = 0; // The y position of the car
    protected double xDir = 1.0; // The direction on x-axis
    protected double yDir = 0; // The direction on y-axis

    protected abstract double speedFactor();

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public double getX() {return x;}
    public double getY() {return y;}

    public double getXDir() {return xDir;}
    public double getYDir() {return yDir;}

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public void move() { // Move car at current speed in X and Y directions
        x += getCurrentSpeed() * xDir;
        y += getCurrentSpeed() * yDir;
    }

    public void turnLeft (){ // Turn car left by changing directions
        double oldXDir = xDir;
        xDir = -yDir;
        yDir = oldXDir;
    }

    public void turnRight() { // Turn car right by changing directions
        double oldYDir = yDir;
        yDir = -xDir;
        xDir = oldYDir;
    }

    private void incrementSpeed(double amount) { // Increase speed to minimum of current speed plus speed change and engine power
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount) { // Decrease speed to maximum of current speed minus speed change and 0
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount) {  // Method for gas with caps on how much you can gas
        double oldSpeed = currentSpeed;
        incrementSpeed(amount);
        if (currentSpeed < oldSpeed || amount > 1 || amount < 0) {currentSpeed = oldSpeed;}
    }

    public void brake(double amount) {  // Method for brake with caps on how much you can brake
        double oldSpeed = currentSpeed;
        decrementSpeed(amount);
        if (currentSpeed > oldSpeed || amount > 1 || amount < 0) {currentSpeed = oldSpeed;}
    }

}
