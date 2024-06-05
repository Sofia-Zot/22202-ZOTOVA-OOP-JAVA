package commands;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

import java.util.List;

public class Sqrt extends Operation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (context.getStack().isEmpty()) {
            throw new IllegalAccessException("attempt to access item from empty stack");
        }
        double n = context.getStack().pop();

        double result = Math.sqrt(n);

        context.getStack().push(result);
    }
}
