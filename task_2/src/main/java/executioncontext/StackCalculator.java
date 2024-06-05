package executioncontext;

import commands.Operation;
import exceptions.CommandsException;

import java.util.Arrays;
import java.util.List;

import commandfactory.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackCalculator {
    private final CalculatorContext ctx;
    private final CommandFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(StackCalculator.class);

    public StackCalculator(CommandFactory factory) {
        ctx = new CalculatorContext();
        this.factory = factory;
    }

    public void execute(String rawString) {
        String[] parsed = rawString.split(" ");

        List<String> positionalArgs = Arrays.asList(parsed);
        positionalArgs = positionalArgs.subList(1, positionalArgs.size());

        Operation operation = null;
        try {
            operation = factory.getCommand(parsed[0]);
        } catch (Exception e) {
            logger.warn(e.getLocalizedMessage());
        }
        if (operation != null) {
            try {
                operation.execute(ctx, positionalArgs);
            } catch (CommandsException e) {
                logger.warn(e.getLocalizedMessage());
            }
        }
    }

    public CalculatorContext getCtx() {
        return ctx;
    }
}