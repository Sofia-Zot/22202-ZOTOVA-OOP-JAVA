import commands.*;
import commands.Add;
import exceptions.CommandsException;
import exceptions.DivisionByZeroException;
import executioncontext.CalculatorContext;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import commands.exceptions.*;

public class CommandsTest {

    private CalculatorContext context = new CalculatorContext();

    @Test
    public void addTest() {
        Operation operation = new Add();
        context.getStack().push(1.0);
        context.getStack().push(2.0);
        List<String> args = new ArrayList<>();
        try {
            operation.execute(context, args);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getStack().peek();
            Assert.assertEquals(3.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void subTest() {
        Operation operation = new SubCommand();
        context.getStack().push(1.0);
        context.getStack().push(2.0);
        List<String> args = new ArrayList<>();
        try {
            operation.execute(context, args);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getStack().peek();
            Assert.assertEquals(1.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void mulTest() {
        Operation operation = new Mul();
        context.getStack().push(1.0);
        context.getStack().push(2.0);
        List<String> args = new ArrayList<>();
        try {
            operation.execute(context, args);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getStack().peek();
            Assert.assertEquals(2.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void divTest() {
        Operation operation = new Div();
        context.getStack().push(2.0);
        context.getStack().push(6.0);
        List<String> args = new ArrayList<>();
        try {
            operation.execute(context, args);
        } catch (CommandsException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getStack().peek();
            Assert.assertEquals(3.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void DefinePushTest() {
        Operation def = new Define();
        Operation push = new Push();
        context = new CalculatorContext();
        List<String> args = new ArrayList<>();
        args.add("a");
        args.add("8");
        try {
            def.execute(context, args);
            args.remove(1);
            push.execute(context, args);
        } catch (CommandsException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getStack().peek();
            Assert.assertEquals(8.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void DefineTest() {
        Operation def = new Define();
        context = new CalculatorContext();
        List<String> args = new ArrayList<>();
        args.add("a");
        args.add("8");
        try {
            def.execute(context, args);
        } catch (CommandsException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            double val = context.getNamedArgs().get("a");
            Assert.assertEquals(8.0, val, 0.0);
        } catch (EmptyStackException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }


    @Test(expected = CommandsException.class)
    public void addExceptionTest() throws CommandsException {
        Operation operation = new Add();
        context.getStack().push(1.0);
        List<String> args = new ArrayList<>();
        operation.execute(context, args);
    }

    @Test(expected = CommandsException.class)
    public void popExceptionTest() throws CommandsException {
        Operation operation = new Pop();
        List<String> args = new ArrayList<>();
        operation.execute(context, args);
    }

    @Test(expected = DivisionByZeroException.class)
    public void divisionByZeroExceptionTest() throws CommandsException {
        Operation operation = new Div();
        context.getStack().push(0.0);
        context.getStack().push(0.0);
        List<String> args = new ArrayList<>();
        operation.execute(context, args);
    }
}