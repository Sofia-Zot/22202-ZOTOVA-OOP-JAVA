package src.exceptions;

import src.consts.*;

//базовый для обработки ошибок
//наследуется от throwable - базового для обработок ошибок в java


public  class StackCalcException extends Throwable
{
    StackCalcException(String problemObjectName, String problemObjectType, String currentProblem)
    {
        problemObjectName_ = problemObjectName;
        problemObjectType_ = problemObjectType;
        shortProblemDesc_ = currentProblem;
    }
    public void whatIsTheProblem() //выводит строку с полным описанием проблемы
    {
        problemDescription_ = problemObjectType_ + problemObjectName_ + UsefulConsts.LINE_DELIMITER
                + shortProblemDesc_ + UsefulConsts.PHRASE_DELIMITER
                + ExceptionConsts.WHAT_WILL_HAPPEN + UsefulConsts.LINE_DELIMITER
                + ExceptionConsts.EXCEPTION_MESSAGE_DELIMITER;
        System.out.println(problemDescription_);
    }

    /*public String getProblemObjectName_() {
        return problemObjectName_;
    }

    public String getProblemObjectType_() {
        return problemObjectType_;
    }*/

    private String problemDescription_;
    private String problemObjectName_;
    private String problemObjectType_;

    /*public String getShortProblemDesc_() {
        return shortProblemDesc_;
    }*/

    private String shortProblemDesc_;

}