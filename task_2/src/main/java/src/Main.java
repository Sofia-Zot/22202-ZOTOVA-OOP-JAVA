package src;

import src.consts.*;
import src.exceptions.ParserException;
import src.logic.*;


import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        String[] parserDescription = determiningParser(args); //определяем парсер
        ParsersFactory pFactory = new ParsersFactory(); //заводим фабрику
        CommandsParser parser = null;
        try{
            parser = pFactory.createParser(ParsersTypes.valueOf(parserDescription[1]));
        }
        catch(ParserException e) {
            String problemDescription = ExceptionConsts.BAD_PARSER + parserDescription[0].toString()
                    + UsefulConsts.LINE_DELIMITER + ExceptionConsts.MAIN_BREAK
                    + UsefulConsts.LINE_DELIMITER + ExceptionConsts.EXCEPTION_MESSAGE_DELIMITER;
            System.out.println(problemDescription);
            return;
        }
        parser.parsingCommandDescriptions(parserDescription[0]);
        OperationsFactory creator = new OperationsFactory();//запускаем фабрику операций
        Executor executor = new Executor(parser.getCommandsAndArguments_(), creator);
        executor.start();
    }
    private static String[] determiningParser(String[] args) {
        //определяем парсер
        String source = UsefulConsts.CONSOLE_INPUT;
        ParsersTypes type = ParsersTypes.CONSOLE_PARSER;
        if (args.length == 1) {
            if (args[UsefulConsts.FILE_INDEX].endsWith(UsefulConsts.FILE_FORMAT)) {
                source = args[UsefulConsts.FILE_INDEX];
                type = ParsersTypes.FILE_PARSER;
            }
        }
        return new String[] {source, type.toString()};
    }

}