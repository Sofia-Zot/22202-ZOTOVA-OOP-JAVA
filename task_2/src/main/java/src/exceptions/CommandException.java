package src.exceptions;

import src.consts.ExceptionConsts;

//производный от SCE
//для обработки ошибок, связанных с командами

public class CommandException extends StackCalcException
{
    public CommandException(String problemObjectName, String currentProblem) {
        super(problemObjectName, ExceptionConsts.BAD_OPERATION, currentProblem);
    }
}
