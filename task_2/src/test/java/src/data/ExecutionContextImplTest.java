package src.data;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExecutionContextImplTest {

    @Test
    void pushPopTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(10.0);
        context.push("test");
        assertEquals("test", context.pop());
        assertEquals(10.0, context.pop());
        assertNull(context.pop());
    }

    @Test
    void defineTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        Object[] arguments = {"variable", "15.5"};
        context.define(arguments);
        assertEquals(15.5, context.getDefinedValue("variable"));
    }

    @Test
    void getTopElementsTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(10.0);
        context.push("20.0");
        context.push("variable");
        context.define(new Object[]{"variable", "30.0"});

        Object[] topElements = context.getTopElements(2);
        assertNotNull(topElements);
        assertEquals(2, topElements.length);
        assertEquals(30.0, topElements[0]);
        assertEquals(20.0, topElements[1]);
    }

    @Test
    void getTopElementsInvalidInputTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(10.0);


        assertNull(context.getTopElements(3));
    }

    @Test
    void getTopElementsNumberFormatExceptionTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push("invalidValue");

        assertThrows(NumberFormatException.class, () -> {
            context.getTopElements(1);
        });
    }

    @Test
    void getDataElementsNumberTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(1.0);
        context.push("test");
        assertEquals(2, context.getDataElementsNumber());
    }

    @Test
    void peekTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(1.0);
        context.push("test");
        assertEquals("test", context.peek());
        assertEquals("test", context.peek());
    }

    @Test
    void clearTest() {
        ExecutionContextImpl context = new ExecutionContextImpl();
        context.push(1.0);
        context.push("test");
        context.define(new Object[]{"variable", "5.0"});
        context.clear();
        assertEquals(0, context.getDataElementsNumber());
        assertNull(context.getDefinedValue("variable"));
    }
}