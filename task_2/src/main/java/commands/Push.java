package commands;

import java.util.List;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import exceptions.WrongFormatException;
import executioncontext.CalculatorContext;
import exceptions.*;

public class Push extends Operation {

    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (positionalArgs.isEmpty()) {
            throw new WrongFormatException("missed push argument");
        }
        String stringValue = positionalArgs.get(0);
        try {
            double value = Double.parseDouble(stringValue);
            context.getStack().push(value);
        } catch (NumberFormatException e) {
            if (!context.getNamedArgs().containsKey(stringValue)) {
                throw new IllegalAccessException("there is no defined value with name '" + stringValue + "'");
            } else {
                context.getStack().push(context.getNamedArgs().get(stringValue));
            }
        }
    }
}