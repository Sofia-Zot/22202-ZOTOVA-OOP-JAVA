package factoryManager;


public class ProductionSpeedChangedEvent extends FactoryEvent {
    private final Class<?> partClass;
    private final int speed;

    public ProductionSpeedChangedEvent(Class<?> partClass, int speed) {
        this.partClass = partClass;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Class<?> getPartClass() {
        return partClass;
    }
}
