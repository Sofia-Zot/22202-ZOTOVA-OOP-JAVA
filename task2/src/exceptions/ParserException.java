package exceptions;

import consts.ExceptionConsts;

//производный от SCE
//для обработки ошибок, связанных с полученной строкой

public class ParserException extends  StackCalcException {
    public ParserException(String problemObjectName, String currentProblem) {
        super(problemObjectName, ExceptionConsts.BAD_PARSER, currentProblem);
    }
}
