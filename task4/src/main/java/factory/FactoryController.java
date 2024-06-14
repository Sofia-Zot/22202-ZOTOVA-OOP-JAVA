package factory;

import factory.parts.Car;

public class FactoryController extends Thread {
    private final Workers assemblyLine;
    private final PartsInventory<Car> carStorage;

    public FactoryController(Workers assemblyLine, PartsInventory<Car> carStorage) {
        this.assemblyLine = assemblyLine;
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        while (true) {
            if (carStorage.getSize() < carStorage.getCapacity() / 2
                    && assemblyLine.getQueueSize() < 100) {
                for (int i = 0; i < assemblyLine.getSize(); i++) {
                    assemblyLine.assembleCar();
                }
            }

            synchronized (carStorage) {
                try {
                    carStorage.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
