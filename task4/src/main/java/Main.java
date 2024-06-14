import factoryManager.FactoryManager;
import factory.Factory;
import factory.FactoryConfig;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        FactoryConfig factoryConfig = new FactoryConfig();
        Factory factory = new Factory(factoryConfig);

        Menu menu = new Menu();

        FactoryManager factoryManager = FactoryManager.getInstance();
        factoryManager.setMenu(menu);
        factoryManager.setFactory(factory);

        factory.addListener(factoryManager);
        menu.display();
        factory.launch();
    }
}
