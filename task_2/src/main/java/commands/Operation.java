package commands;


import java.util.List;

import exceptions.CommandsException;
import executioncontext.CalculatorContext;

public abstract class Operation {
    abstract public void execute(CalculatorContext context, List<String> positionalArgs) throws CommandsException;

}