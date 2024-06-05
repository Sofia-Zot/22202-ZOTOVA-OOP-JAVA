package commandfactory;

import commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class CommandFactory {

    private final HashMap<String, Operation> _factory;
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    private final String configFile = "/config.properties.txt";

    public CommandFactory() {
        _factory = new HashMap<>();
        loadConfig();
    }

    private Operation createCommandInstance(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Operation) Class.forName(className).getDeclaredConstructor().newInstance();
    }

    public Operation getCommand(String name) {
        if (_factory.containsKey(name)) {
            return _factory.get(name);
        } else {
            throw new IllegalArgumentException("no command with name '" + name + "' was found");
        }
    }

    private void loadConfig() {
        InputStream inputStream = CommandFactory.class.getResourceAsStream(configFile);
        if (inputStream == null) {
            logger.error("input stream is empty, no resource with this name is found");
            return;
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            for (String name : properties.stringPropertyNames()) {
                _factory.put(name, createCommandInstance(properties.getProperty(name)));
            }
        } catch (IOException e) {
            logger.error("impossible to load config file");
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }
}