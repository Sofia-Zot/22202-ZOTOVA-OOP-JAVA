import consts.ExceptionConsts;
import consts.ParsersTypes;
import consts.UsefulConsts;
import exceptions.ParserException;
import javafx.util.Pair;
import logic.CommandsParser;
import logic.Executor;
import logic.OperationsFactory;
import logic.ParsersFactory;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Pair<String, ParsersTypes> parserDescriptin = determiningParser(args); //определяем парсер
        ParsersFactory pFactory = new ParsersFactory(); //заводим фабрику
        CommandsParser parser = null;
        try{
            parser = pFactory.createParser(parserDescriptin.getValue());
        }
        catch(ParserException e) {
            String problemDescription = ExceptionConsts.BAD_PARSER + parserDescriptin.getKey().toString()
                    + UsefulConsts.LINE_DELIMITER + ExceptionConsts.MAIN_BREAK
                    + UsefulConsts.LINE_DELIMITER + ExceptionConsts.EXCEPTION_MESSAGE_DELIMITER;
            System.out.println(problemDescription);
            return;
        }
        parser.parsingCommandDescriptions(parserDescriptin.getKey());
        OperationsFactory creator = new OperationsFactory();//запускаем фабрику операций
        Executor executor = new Executor(parser.getCommandsAndArguments_(), creator);
        executor.start();
    }
    private static Pair<String, ParsersTypes> determiningParser(String[] args) {
        //определяем парсер
        String source = UsefulConsts.CONSOLE_INPUT;
        ParsersTypes type = ParsersTypes.CONSOLE_PARSER;
        if(args.length == UsefulConsts.REQURED_ARGUMENTS_NUMBER) {
            if(args[UsefulConsts.FILE_INDEX].endsWith(UsefulConsts.FILE_FORMAT)) {
                source = args[UsefulConsts.FILE_INDEX];
                type = ParsersTypes.FILE_PARSER;
            }
        }
        return new Pair<>(source, type);
    }
}