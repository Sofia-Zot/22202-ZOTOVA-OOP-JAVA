package exceptions;

import consts.*;

//производный от AE
//обработка ошибок при расчетах

public class CalculatingException extends ArgumentException {
    public CalculatingException(String problemObjectName, String currentProblem, Object arguments) {
        super(problemObjectName, currentProblem);
        badArgumentsAsString_ = arguments.toString();
    }
    /*public void showArguments()
    {
        System.out.println((ExceptionConsts.SHOW_ARGUMENT + badArgumentsAsString_));
    }*/
    private String badArgumentsAsString_;
}