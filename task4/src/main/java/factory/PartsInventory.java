package factory;

import factoryManager.InventoryChangedEvent;
import factoryManager.FactoryEvent;
import messages.Messenger;
import messages.Listener;

import java.util.ArrayList;
import java.util.List;

public class PartsInventory<T> implements Messenger<InventoryChangedEvent> {
    private final int capacity;
    private int currentCount = 0;
    private final List<T> partsList;
    private final Class<?> partType;
    private Listener<FactoryEvent> listener = null;

    public PartsInventory(int capacity, Class<?> storedType) {
        this.capacity = capacity;
        this.partType = storedType;
        this.partsList = new ArrayList<>();
    }

    public void addListener(Listener<FactoryEvent> listener) {
        this.listener = listener;
    }

    public synchronized void setPart(T part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        partsList.add(part);
        currentCount++;

        if (listener != null) publishChange();

        notifyAll();
    }

    public synchronized T removePart() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T part = partsList.remove(0);

        if (listener != null) publishChange();

        notifyAll();
        return part;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return partsList.size();
    }

    public boolean isEmpty() {
        return partsList.isEmpty();
    }

    public boolean isFull() {
        return partsList.size() == capacity;
    }

    public int getTotalCount() {
        return currentCount;
    }

    private synchronized void publishChange() {
        sendMessage(new InventoryChangedEvent(partType, getSize(), getTotalCount()));
    }

    @Override
    public void sendMessage(InventoryChangedEvent event) {
        listener.recieveMessage(event);
    }

    public Class<?> getPartType() {
        return partType;
    }
}
