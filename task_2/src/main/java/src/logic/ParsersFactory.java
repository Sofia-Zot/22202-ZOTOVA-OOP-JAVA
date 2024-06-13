package src.logic;

import src.consts.*;
import src.exceptions.ParserException;

import java.util.HashMap;
import java.util.Map;

//аналогично OF, но для парсеров

public class ParsersFactory
{
    public ParsersFactory()
    {
        _factory = new HashMap<src.consts.ParsersTypes, Object>();
        _factory.put(src.consts.ParsersTypes.FILE_PARSER, new FileParser());
        _factory.put(src.consts.ParsersTypes.CONSOLE_PARSER, new ConsoleParser());
    };
    public CommandsParser createParser(src.consts.ParsersTypes parserType) throws ParserException
    {
        CommandsParser parser = null;
        parser = (CommandsParser)_factory.get(parserType);
        CommandsParser clone = null;
        try
        {
            clone = parser.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new ParserException(parserType.toString(), ExceptionConsts.UNKNOWN_PARSER
                    + UsefulConsts.LINE_DELIMITER + ExceptionConsts.MAIN_BREAK);
        }
        return clone;
    }
    private Map<src.consts.ParsersTypes, Object>_factory;
}