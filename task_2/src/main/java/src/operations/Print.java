package src.operations;

import src.consts.*;
import src.data.*;
import src.exceptions.ArgumentException;
import src.exceptions.CalculatingException;

public class Print extends Operation
{
    public Print(String opName)
    {
        super(opName);
    }

    @Override
    public void execution(ExecutionContext data, Object[] arguments) throws CalculatingException, ArgumentException
    {
        Object value = data.peek();
        if(value == null)
        {
            throw new ArgumentException(operationName_,
                    ExceptionConsts.NO_ENOUGH_ARGUMENTS);
        }
        System.out.println(UsefulConsts.SHOWING_RESULT + value.toString()+UsefulConsts.LINE_DELIMITER + ExceptionConsts.EXCEPTION_MESSAGE_DELIMITER);
    }
}
