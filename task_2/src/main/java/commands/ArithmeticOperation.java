package commands;

import java.util.List;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

public class ArithmeticOperation extends Operation {

    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (context.getStack().size() < 2) {
            throw new IllegalAccessException("not sufficient amount of values in stack");
        }
    }
}