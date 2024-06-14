package factory;

import factory.parts.*;
import threadpool.ThreadPool;

public class Workers extends ThreadPool {
    private static final int sleepMs = 1000;

    private final PartsInventory<Car> carStorage;

    private final PartsInventory<AbstractPart> engineStorage;
    private final PartsInventory<AbstractPart> bodyStorage;
    private final PartsInventory<AbstractPart> accessoryStorage;

    public Workers(
            int size,
            PartsInventory<AbstractPart> bodyStorage,
            PartsInventory<AbstractPart> engineStorage,
            PartsInventory<AbstractPart> accessoryStorage,
            PartsInventory<Car> carStorage
    ) {
        super(size);
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    public void assembleCar() {
        enqueue(() -> {
            try {
                Engine engine = (Engine) engineStorage.removePart();
                Body body = (Body) bodyStorage.removePart();
                Accessories accessory = (Accessories) accessoryStorage.removePart();

                Thread.sleep(sleepMs);
                Car car = new Car(engine, body, accessory);

                carStorage.setPart(car);
            } catch (InterruptedException ignored) {}
        });
    }
}
