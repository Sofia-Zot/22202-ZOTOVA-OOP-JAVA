package factory.suppliers;

import factory.parts.AbstractPart;
import factory.PartsInventory;

public abstract class Supplier extends Thread {
    private int sleepMs;
    private final PartsInventory<AbstractPart> storage;
    protected int serialNumber = 0;

    public Supplier(PartsInventory<AbstractPart> storage, int sleepMs) {
        this.sleepMs = sleepMs;
        this.storage = storage;
    }

    public int getSpeed() {
        return sleepMs;
    }

    @Override
    public void run() {
        while (true) {
            AbstractPart part;

            try {
                Thread.sleep(sleepMs);

                part = supplyPart();
                storage.setPart(part);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void setSpeed(int sleepMs) {
        this.sleepMs = sleepMs;
    }

    public abstract AbstractPart supplyPart();
}
