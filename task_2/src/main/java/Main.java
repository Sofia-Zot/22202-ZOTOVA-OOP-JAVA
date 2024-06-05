import executioncontext.StackCalculator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import commandfactory.CommandFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner inputScanner;
        if (args.length == 0) {
            inputScanner = new Scanner(System.in);
        } else {
            try {
                inputScanner = new Scanner(new FileInputStream(args[0]));
            } catch (FileNotFoundException e) {
                logger.error(e.getLocalizedMessage());
                return;
            }
        }

        CommandFactory factory = new CommandFactory();
        StackCalculator calculator = new StackCalculator(factory);

        while (inputScanner.hasNextLine()) {
            calculator.execute(inputScanner.nextLine());
        }

        inputScanner.close();
    }
}