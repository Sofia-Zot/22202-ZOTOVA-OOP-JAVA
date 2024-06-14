package src.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import src.consts.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackCalcExceptionTest {

    @Test
    void testWhatIsTheProblem(@TempDir Path tempDir) {
        String problemObjectName = "testObject";
        String problemObjectType = "TestType";
        String currentProblem = "Error message";
        StackCalcException exception = new StackCalcException(problemObjectName, problemObjectType, currentProblem);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        exception.whatIsTheProblem();

        System.setOut(originalOut);

        String expectedOutput = problemObjectType + problemObjectName + UsefulConsts.LINE_DELIMITER +
                currentProblem + UsefulConsts.PHRASE_DELIMITER +
                ExceptionConsts.WHAT_WILL_HAPPEN + UsefulConsts.LINE_DELIMITER +
                ExceptionConsts.EXCEPTION_MESSAGE_DELIMITER;

        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}