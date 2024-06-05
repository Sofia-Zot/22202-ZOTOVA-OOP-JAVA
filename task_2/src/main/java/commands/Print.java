package commands;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

import java.util.List;

public class Print extends Operation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        if (context.getStack().isEmpty()) {
            throw new IllegalAccessException("attempt to print element of empty stack");
        } else {
            System.out.println(context.getStack().peek());
        }
    }
}