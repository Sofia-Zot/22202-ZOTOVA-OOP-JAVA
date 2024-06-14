package src.exceptions;

import src.consts.*;

//суперкласс, производный от StackCalcException
//обработка ошибок, связанных с аргументами
public class ArgumentException extends StackCalcException {
    public ArgumentException(String problemObjectName, String currentProblem) {
        super(problemObjectName, ExceptionConsts.PROBLEM_WITH_ARGUMENT, currentProblem);
    }
}
