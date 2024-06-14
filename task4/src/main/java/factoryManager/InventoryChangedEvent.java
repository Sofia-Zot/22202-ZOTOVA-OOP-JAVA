package factoryManager;

public class InventoryChangedEvent extends FactoryEvent {
    private final Class<?> partClass;
    private final int current;
    private final int total;

    public InventoryChangedEvent(Class<?> partClass, int current, int total) {
        this.partClass = partClass;
        this.current = current;
        this.total = total;
    }

    public int getTotalAmount() {
        return total;
    }

    public int getCurrentAmount() {
        return current;
    }

    public Class<?> getPartClass() {
        return partClass;
    }
}
