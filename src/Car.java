import java.awt.*;

public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double x = 0; //The x position of the car
    protected double y = 0; //The y position of the car
    private double xDir = 1.0; //The direction on x-axis
    private double yDir = 0; //The direction on y-axis

    protected abstract double speedFactor();

    protected abstract void incrementSpeed(double amount);
    protected abstract void decrementSpeed(double amount);

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

    public void setXDir(double xDir){this.xDir = xDir;}
    public void setYDir(double yDir){this.yDir = yDir;}

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public void move(){
        x += getCurrentSpeed() * xDir;
        y += getCurrentSpeed() * yDir;
    }

    public void turnLeft(){
        double oldXDir = xDir;
        xDir = -yDir;
        yDir = oldXDir;
    }

    public void turnRight(){
        double oldYDir = yDir;
        yDir = -xDir;
        xDir = oldYDir;
    }

    public void gas(double amount){
        double oldSpeed = currentSpeed;
        incrementSpeed(amount);
        if (currentSpeed < oldSpeed || amount > 1 || amount < 0) {currentSpeed = oldSpeed;}
    }

    public void brake(double amount){
        double oldSpeed = currentSpeed;
        decrementSpeed(amount);
        if (currentSpeed > oldSpeed || amount > 1 || amount < 0) {currentSpeed = oldSpeed;}
    }

}
