package commands;

import java.util.List;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

public class Pop extends Operation {

    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (context.getStack().isEmpty()) {
            throw new IllegalAccessException("pop command was used on empty stack");
        }
        context.getStack().pop();
    }

}
