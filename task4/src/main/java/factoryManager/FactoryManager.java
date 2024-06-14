package factoryManager;

import factory.Factory;
import factory.Dealer;
import messages.Messenger;
import messages.Listener;
import ui.Menu;

public class FactoryManager implements Listener<FactoryEvent>, Messenger<FactoryEvent> {
    private Factory factory;
    private Menu menu;

    private static FactoryManager instance = null;

    public static FactoryManager getInstance() {
        if (instance == null) {
            instance = new FactoryManager();
        }
        return instance;
    }

    private FactoryManager() {}

    public FactoryManager setFactory(Factory factory) {
        this.factory = factory;
        return this;
    }

    public FactoryManager setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    @Override
    public void recieveMessage(FactoryEvent event) {
        if (event instanceof ProductionSpeedChangedEvent) {
            updateProductionSpeed((ProductionSpeedChangedEvent) event);
        } else if (event instanceof InventoryChangedEvent) {
            notifyStockChange((InventoryChangedEvent) event);
        }
    }

    @Override
    public void sendMessage(FactoryEvent event) {
        if (menu == null) {
            throw new RuntimeException("menu not attached to factory manager");
        }
        menu.recieveMessage((InventoryChangedEvent) event);
    }

    private void updateProductionSpeed(ProductionSpeedChangedEvent event) {
        if (factory == null) {
            throw new RuntimeException("factory not attached to factory manager");
        }

        Class<?> partClass = event.getPartClass();
        int speed = event.getSpeed();

        switch (partClass.getSimpleName()) {
            case "Body":
                factory.setBodySpeed(speed);
                break;
            case "Engine":
                factory.setEngineSpeed(speed);
                break;
            case "Accessories":
                factory.setAccessorySpeed(speed);
                break;
            case "Dealer":
                Dealer.setSleepMs(speed);
                break;
            default:
                throw new IllegalArgumentException("Unknown part type: " + partClass);
        }
    }

    private void notifyStockChange(InventoryChangedEvent event) {
        this.sendMessage(event);
    }
}