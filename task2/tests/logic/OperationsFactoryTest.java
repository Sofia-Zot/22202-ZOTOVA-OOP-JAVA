package logic;

import consts.OperationsNames;
import exceptions.CommandException;
import operations.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsFactoryTest {

    private OperationsFactory factory_ = null;

    @BeforeEach
    void setUp() {
        factory_ = new OperationsFactory();
    }

    @AfterEach
    void tearDown() {
        factory_ = null;
    }

    @Test
    void createOp() {
        String[] operations = {OperationsNames.MUL, OperationsNames.ADD, OperationsNames.DIV, OperationsNames.POP, OperationsNames.SQRT,
                OperationsNames.DIF, OperationsNames.PRINT, OperationsNames.DEFINE, OperationsNames.PUSH};

        for (String operation : operations) {
            try {
                Operation op = factory_.createOp(operation);
                assertEquals(operation, op.getOperationName_());
                assertInstanceOf(Operation.class, op);
            } catch (CommandException ex) {
                fail(STR."Unexpected exception: \{ex.getMessage()}");
            }
        }
    }
}
