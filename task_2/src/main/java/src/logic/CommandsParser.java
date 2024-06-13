package src.logic;

import src.consts.*;

import java.io.IOException;
import java.util.ArrayList;

//абстрактный класс для парсинга
//общий интерфейс для парсеров
//реализует clonable - базовый класс основной библиотеки

public abstract class CommandsParser implements Cloneable {

    private ParsersTypes type_;
    protected ArrayList<String[]> commandsAndArguments_;

    public CommandsParser(ParsersTypes type) { //конструктор
        this.type_ = type;
        this.commandsAndArguments_ = new ArrayList<String[]>();
    }
    //дает возможность поверхностно скопировать объект, который можно изменять без изменения оригинала
    public CommandsParser clone() throws CloneNotSupportedException {
        return (CommandsParser) super.clone();
    }

    public ArrayList<String[]> getCommandsAndArguments_() { //возвращает список массивов строк, содержащих команды и их аргументы
        return commandsAndArguments_;
    }

    protected String[] parseCommand(String command) { //делит строку по "словам" в массив
        String[] result = null;
        String delimiter = UsefulConsts.COMMAND_DESCRIPTION_DELIMITER;
        result = command.split(delimiter);
        return result;
    }

    public ParsersTypes getParserType() {
        return type_;
    }

    abstract public void parsingCommandDescriptions(String commandsSource) throws IOException;
    //будет реализован в дочерних классах


    /*public void addCommand(String[] commandWithArguments) {
        this.commandsAndArguments_.add(commandWithArguments);
    }*/
}
