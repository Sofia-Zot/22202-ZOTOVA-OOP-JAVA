package commands;

import java.util.List;
import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import exceptions.WrongFormatException;
import executioncontext.CalculatorContext;

public class Define extends Operation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (positionalArgs.size() < 2) {
            throw new WrongFormatException("define command requires 2 positional arguments");
        }
        try {
            context.getNamedArgs().put(positionalArgs.get(0), Double.parseDouble(positionalArgs.get(1)));
        } catch (NumberFormatException e) {
            throw new IllegalAccessException("can't parse given value of parameter");
        }
    }
}