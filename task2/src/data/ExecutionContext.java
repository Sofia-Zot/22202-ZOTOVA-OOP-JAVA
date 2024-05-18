package data;

//интерфейс контекста
//функции для управления стеком

public interface ExecutionContext
{
    void push(Object arguments);//положить наверх
    Object pop();//убрать сверху
    void define(Object[] arguments);//объявить
    public Object getDefinedValue(String value);//вернуть значение по имени
    Object[] getTopElements(int elementsNumber)  throws java.lang.NumberFormatException;//возвращает n элементов из стека массивом
    int getDataElementsNumber();//получить количество элементов в стеке
    Object peek();//посмотреть верхний элемент стека
    void clear();//очистить стек
}