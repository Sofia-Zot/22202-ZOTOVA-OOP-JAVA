package commands;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

import java.util.List;

public class Add extends ArithmeticOperation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (context.getStack().size() < 2) {
            throw new IllegalAccessException("not sufficient amount of values in stack");
        }

        context.getStack().push(context.getStack().pop() + context.getStack().pop());
    }
}