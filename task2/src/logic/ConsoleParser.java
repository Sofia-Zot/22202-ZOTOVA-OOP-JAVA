package logic;

import consts.ParsersTypes;
import consts.UsefulConsts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//наследуется от CP, для консоли

public class ConsoleParser extends CommandsParser {

    private ArrayList<String> commands_;
    private Scanner consoleScanner_;

    public ConsoleParser() {
        super(ParsersTypes.CONSOLE_PARSER);
        commands_ = new ArrayList<String>();
        consoleScanner_ = new Scanner(System.in);
    }

    public void parsingCommandDescriptions(String commandsSource) throws IOException {
        System.out.println("Enter commands to execute:");
        String newString = consoleScanner_.nextLine();
        while (!(newString.equals(UsefulConsts.DESCRIPTION_END))) {
            commands_.add(newString);
            newString = consoleScanner_.nextLine();
        }

        //int i;
        for (String command : commands_) {
            String[] commandWithArguments = super.parseCommand(command);
            commandsAndArguments_.add(commandWithArguments);
        }
    }

}
