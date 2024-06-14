package factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class FactoryConfig {

    public int engineStorageSize;
    public int bodyStorageSize;
    public int accessoryStorageSize;
    public int carStorageSize;
    public int accessorySuppliers;
    public int assemblyWorkers;
    public int carDealers;
    public boolean enableLogging;

    public FactoryConfig(String name) {
        InputStream configFile = getClass().getResourceAsStream(name);
        Properties properties = new Properties();

        try {
            properties.load(configFile);
        } catch (IOException e) {
            System.err.println("error loading config file");
        }

        for (Field field : getClass().getFields()) {
            String fieldName = field.getName();
            if (properties.containsKey(fieldName)) {
                String value = properties.getProperty(fieldName);

                try {
                    if (field.getType() == int.class) {
                        field.setInt(this, Integer.parseInt(value));
                    } else if (field.getType() == boolean.class) {
                        field.setBoolean(this, Boolean.parseBoolean(value));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("error in factory config: " + e.getMessage());
                }
            }
        }
    }

    public FactoryConfig() {
        this("/carFactory.cfg");
    }
}