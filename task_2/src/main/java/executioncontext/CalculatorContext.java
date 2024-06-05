package executioncontext;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorContext {
    private final Stack<Double> stack;
    private final HashMap<String, Double> namedArgs;

    public CalculatorContext() {
        this.stack = new Stack<>();
        this.namedArgs = new HashMap<>();
    }

    public Map<String, Double> getNamedArgs() {
        return namedArgs;
    }

    public Stack<Double> getStack() {
        return stack;
    }
}