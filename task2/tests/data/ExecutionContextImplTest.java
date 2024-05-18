package data;

import logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionContextImplTest {
    @Test
    public void executionContextImplTest() {
        CommandsParser parser = new FileParser();
        try {
            parser.parsingCommandDescriptions("test.txt");
        } catch (java.io.IOException _) {}

        OperationsFactory creator = new OperationsFactory();

        Executor executor = new Executor(parser.getCommandsAndArguments_(), creator);

        executor.start();

        Double expRes = 13.0;
        Double res = (double)executor.getResult();
        assertEquals(expRes, res);
    }
}