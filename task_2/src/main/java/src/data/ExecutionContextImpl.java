package src.data;

//имплементация контекста
//

import src.consts.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
public class ExecutionContextImpl implements ExecutionContext {
    public Stack<Object> stack_;//создаем стек
    public Map<String, Double> definedValues_;//создаем map для переменных и значений
    public ExecutionContextImpl() { //конструктор
        stack_ = new Stack<Object>();
        definedValues_ = new HashMap<String, Double>();
    }
    @Override
    public void push(Object argument) { //переопределение из интерфейса
        stack_.push(argument);
    }

    @Override
    public Object pop() { //переопределение из интерфейса
        Object value;
        if(stack_.empty()) {
            return null;
        }
        value = stack_.pop();
        return value;
    }

    @Override
    public void define(Object[] arguments) { //переопределение из интерфейса

        String valueName = arguments[UsefulConsts.VALUE_NAME_INDEX].toString();
        String valueString = arguments[UsefulConsts.VALUE_INDEX].toString();
        Double value = definedValues_.get(valueString);
        if(value == null) {
            value = Double.valueOf(valueString);
        }
        definedValues_.put(valueName, value);
    }
    @Override
    public Object getDefinedValue(String name) { //переопределение из интерфейса
        Object result = null;
        result = definedValues_.get(name);
        return result;
    }
    @Override
    //переопределение из интерфейса
    public Object[] getTopElements(int elementsNumber) throws NumberFormatException {
        String valueString;
        Double value;
        Double[] result = new Double[elementsNumber];
        if(stack_.empty() || stack_.size() < elementsNumber) {
            return null;
        }
        for(int i = 0; i < elementsNumber; i++) {
            valueString = stack_.pop().toString();
            value = (Double)this.getDefinedValue(valueString);
            if(value == null) {
                try {
                    value = Double.valueOf(valueString);
                }
                catch(NumberFormatException formatEx) {
                    throw formatEx;
                }
            }
            result[i] = value;
        }
        return result;
    }
    @Override
    //переопределение из интерфейса
    public int getDataElementsNumber() {
        return this.stack_.size();
    }

    @Override//переопределение из интерфейса
    public Object peek() {
        Object value;
        if(stack_.empty()) {
            return null;
        }
        value = stack_.peek();
        return value;
    }

    @Override//переопределение из интерфейса
    public void clear() {
        stack_.clear();
        definedValues_.clear();
    }
}