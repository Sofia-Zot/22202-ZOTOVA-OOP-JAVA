package commands;

import exceptions.CommandsException;
import exceptions.DivisionByZeroException;
import executioncontext.CalculatorContext;

import java.util.List;

public class Div extends ArithmeticOperation {
    @Override
    public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException {
        super.execute(context, positionalArgs);
        double n1 = context.getStack().pop();
        double n2 = context.getStack().pop();
        if (n2 == 0) {
            context.getStack().push(n2);
            context.getStack().push(n1);
            throw new DivisionByZeroException();
        }
        context.getStack().push(n1 / n2);
    }
}
