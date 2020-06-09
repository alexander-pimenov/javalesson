package ru.pimalex1978.class_loader;

/**
 * Создаем «true-static»-класс
 * В нем есть public-метод getCounter(), которым мы собираемся пользоваться в
 * стационарной части программы. Наследуем от него «динамический» класс
 * DynamicModule.java
 */

public class TrueStaticModule {
    protected static int counter= 0;
    public int getCounter()
    {
        return counter;
    }
}
