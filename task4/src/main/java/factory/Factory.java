package factory;

import factoryManager.FactoryEvent;
import factory.parts.*;
import factory.suppliers.*;
import messages.Listener;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Factory {
    private final PartsInventory<AbstractPart> engineStorage;
    private final PartsInventory<AbstractPart> bodyStorage;
    private final PartsInventory<AbstractPart> accessoryStorage;

    private final EngineSupplier engineSupplier;
    private final BodySupplier bodySupplier;
    private final AccessorySupplier[] accessorySuppliers;

    private final Workers workers;
    private final PartsInventory<Car> carStorage;
    private final Dealer[] dealers;

    private final FactoryController factoryController;

    public Factory(FactoryConfig config) {
        engineStorage = new PartsInventory<>(config.engineStorageSize, Engine.class);
        bodyStorage = new PartsInventory<>(config.bodyStorageSize, Body.class);
        accessoryStorage = new PartsInventory<>(config.accessoryStorageSize, Accessories.class);
        carStorage = new PartsInventory<>(config.carStorageSize, Car.class);

        engineSupplier = new EngineSupplier(engineStorage);
        bodySupplier = new BodySupplier(bodyStorage);

        accessorySuppliers = new AccessorySupplier[config.accessorySuppliers];
        for (int i = 0; i < config.accessorySuppliers; i++) {
            accessorySuppliers[i] = new AccessorySupplier(accessoryStorage);
        }

        workers = new Workers(
                config.assemblyWorkers,
                bodyStorage,
                engineStorage,
                accessoryStorage,
                carStorage
        );

        Logger logger = null;
        if (config.enableLogging) {
            logger = Logger.getLogger("Sales Logger");

            try {
                FileHandler fh = new FileHandler("sales.log");
                logger.addHandler(fh);
            } catch (IOException e) {
                throw new RuntimeException("error opening log file: " + e.getMessage());
            }
        }

        dealers = new Dealer[config.carDealers];
        for (int i = 0; i < config.carDealers; i++) {
            dealers[i] = new Dealer(i + 1, carStorage, logger);
        }

        factoryController = new FactoryController(workers, carStorage);
    }

    public void addListener(Listener<FactoryEvent> listener) {
        engineStorage.addListener(listener);
        bodyStorage.addListener(listener);
        accessoryStorage.addListener(listener);
        carStorage.addListener(listener);
    }

    public void launch() {
        engineSupplier.start();
        bodySupplier.start();

        for (AccessorySupplier supplier : accessorySuppliers) {
            supplier.start();
        }

        for (Dealer dealer : dealers) {
            dealer.start();
        }

        factoryController.start();
    }

    public void setEngineSpeed(int sleepMs) {
        engineSupplier.setSpeed(sleepMs);
    }

    public void setBodySpeed(int sleepMs) {
        bodySupplier.setSpeed(sleepMs);
    }

    public void setAccessorySpeed(int sleepMs) {
        for (AccessorySupplier supplier : accessorySuppliers) {
            supplier.setSpeed(sleepMs);
        }
    }
}
