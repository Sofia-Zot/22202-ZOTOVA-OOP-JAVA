package commands;

import exceptions.CommandsException;
import exceptions.IllegalAccessException;
import executioncontext.CalculatorContext;

import java.util.List;

public class Add extends ArithmeticOperation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        super.execute(context, positionalArgs);

        double n1 = context.getStack().pop();
        double n2 = context.getStack().pop();

        double result = n1 + n2;

        context.getStack().push(result);

        context.getStack().push(context.getStack().pop() + context.getStack().pop());
    }
}
