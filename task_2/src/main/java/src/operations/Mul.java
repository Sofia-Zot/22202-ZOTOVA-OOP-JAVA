package src.operations;

import src.consts.*;
import src.data.*;
import src.exceptions.ArgumentException;
import src.exceptions.CalculatingException;
public class Mul extends Operation
{
    public Mul(String opName)
    {
        super(opName);
    }
    @Override
    public void execution(ExecutionContext data, Object[] arguments) throws CalculatingException, ArgumentException
    {
        Object[] values = null;
        try
        {
            values = data.getTopElements(UsefulConsts.MUL_ARGUMENTS_NUMBER);
        }
        catch(NumberFormatException formatEx)
        {
            throw new ArgumentException(operationName_,
                    formatEx.getMessage() + UsefulConsts.LINE_DELIMITER + ExceptionConsts.UNDEFINED_VALUE);
        }
        if(values == null)
        {
            throw new ArgumentException(operationName_,
                    ExceptionConsts.NO_ENOUGH_ARGUMENTS);
        }
        Double result = (double)values[UsefulConsts.FIRST_ARGUMENT_INDEX] * (double)values[UsefulConsts.SECOND_ARGUMENT_INDEX];
        data.push(result);
    }
}
