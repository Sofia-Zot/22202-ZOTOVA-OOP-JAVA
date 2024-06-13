package src.operations;

import src.consts.*;
import src.data.*;
import src.exceptions.*;


public class Define extends Operation
{
    public Define(String opName)
    {
        super(opName);
    }

    @Override
    public void execution(ExecutionContext data, Object[] arguments) throws CalculatingException, ArgumentException
    {
        String valueString = arguments[UsefulConsts.VALUE_INDEX].toString();
        Double value = (Double)data.getDefinedValue(valueString);
        if (value == null) {
            try {
                value = Double.valueOf(valueString);
            } catch (NumberFormatException formatEx) {
                throw new ArgumentException(operationName_,
                        ExceptionConsts.SHOW_ARGUMENT + valueString + UsefulConsts.LINE_DELIMITER + ExceptionConsts.BAD_ARGUMENT +
                                UsefulConsts.LINE_DELIMITER + ExceptionConsts.AND_ANOTHER_PROBLEM + ExceptionConsts.UNDEFINED_VALUE);
            }
        }
        data.define(arguments);
    }
    @Override
    public void validate(ExecutionContext data, Object[] arguments) throws ArgumentException {
        if (arguments.length < UsefulConsts.DEFINE_ARGUMENTS_NUMBER) {
            throw new ArgumentException(operationName_,
                    ExceptionConsts.NO_ENOUGH_ARGUMENTS);
        }
        if (arguments.length > UsefulConsts.DEFINE_ARGUMENTS_NUMBER) {
            throw new ArgumentException(operationName_,
                    ExceptionConsts.TOO_MANY_ARGUMENTS);
        }
    }
}