package factory;

import factory.parts.Car;

import java.util.logging.Logger;

public class Dealer extends Thread {
    private static int sleepMs = 1000;

    private final int id;
    private final PartsInventory<Car> carStorage;
    private final Logger logger;

    public Dealer(int id, PartsInventory<Car> carStorage, Logger logger) {
        this.id = id;
        this.carStorage = carStorage;
        this.logger = logger;
    }

    public static void setSleepMs(int sleepMs) { //  Добавляем метод для установки sleepMs
        Dealer.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepMs);
                Car car = carStorage.removePart();

                if (logger != null) {
                    logger.info("Dealer " + id + ": " + car);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}