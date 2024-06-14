package src.operations;

import src.consts.*;
import src.data.ExecutionContext;
import src.exceptions.ArgumentException;
import src.exceptions.CalculatingException;

public class Div extends Operation
{
    public Div(String opName)
    {
        super(opName);
    }
    @Override
    public void execution(ExecutionContext data, Object[] arguments) throws CalculatingException, ArgumentException
    {
        Object[] values = null;
        try
        {
            values = data.getTopElements(2);
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
        Double denominator = (Double)values[UsefulConsts.DENOMINATOR_INDEX];
        if(denominator == ExceptionConsts.ILLEGAL_DENOMINATOR)
        {
            throw new CalculatingException(operationName_,
                    ExceptionConsts.DIVISION_BY_ZERO,
                    denominator);
        }
        Double result = (double)values[UsefulConsts.NUMERATOR_INDEX] / (double)values[UsefulConsts.DENOMINATOR_INDEX];
        data.push(result);
    }
}
