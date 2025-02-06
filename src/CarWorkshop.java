import java.awt.*;

public abstract class CarWorkshop<T> implements Loadable {

    protected T[] vehicles;

    public CarWorkshop(Class<?> vehicleType, int capacity) {
    }

}
