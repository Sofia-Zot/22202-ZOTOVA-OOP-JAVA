package src.operations;

import src.data.*;
import src.exceptions.ArgumentException;
import src.exceptions.CalculatingException;


public class Pop extends Operation
{
    public Pop(String opName)
    {
        super(opName);
    }

    @Override
    public void execution(ExecutionContext data, Object[] arguments) throws CalculatingException, ArgumentException
    {
        Object value =  data.pop();
    }
}
