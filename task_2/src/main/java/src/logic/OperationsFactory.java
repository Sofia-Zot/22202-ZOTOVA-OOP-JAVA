package src.logic;

import src.consts.*;
import src.exceptions.CommandException;
import src.operations.Operation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class OperationsFactory {

    private final HashMap<String, Operation> _factory;
    private static final Logger logger = LoggerFactory.getLogger(OperationsFactory.class);
    private final String configFile = "/config.properties.txt";

    public OperationsFactory() {
        _factory = new HashMap<>();
        loadConfig();
        //printFactoryEntries();
    }

    public void printFactoryEntries() {
        if (_factory.isEmpty()) {
            System.out.println("_factory is empty.");
        } else {
            for (Map.Entry<String, Operation> entry : _factory.entrySet()) {
                System.out.println("String: " + entry.getKey() + ", operation: " + entry.getValue());
            }
        }
    }

    private void loadConfig() {
        InputStream inputStream = OperationsFactory.class.getResourceAsStream(configFile);
        if (inputStream == null) {
            logger.error("input stream is empty, no resource with this name is found");
            return;
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            for (String name : properties.stringPropertyNames()) {
                String className = properties.getProperty(name);
                // Загрузка и создание экземпляра операции
                try {
                    Class<?> operationClass = Class.forName(className);
                    // Получение конструктора с одним параметром (opName)
                    Constructor<?> constructor = operationClass.getConstructor(String.class);
                    // Создание экземпляра с помощью конструктора
                    Operation operation = (Operation) constructor.newInstance(name);
                    _factory.put(name, operation);
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    logger.error("Error loading operation class: " + className, e);
                }
            }
        } catch (IOException e) {
            logger.error("Impossible to load config file");
        }
    }

    public Operation createOp(String opName) throws CommandException {
        Operation result = _factory.get(opName);
        if (result == null) {
            throw new CommandException(opName, ExceptionConsts.UNKNOWN_OPERATION);
        }
        Operation clone = null;
        try {
            clone = result.clone();
        } catch (CloneNotSupportedException ex) {
            throw new CommandException(opName, ExceptionConsts.UNKNOWN_OPERATION);
        }
        return clone;
    }
}