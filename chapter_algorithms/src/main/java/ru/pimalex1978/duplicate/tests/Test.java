package ru.pimalex1978.duplicate.tests;

/**
 * Простая задача "Что выведется в консоль?"
 * Раскомментируй код и посмотри.
 */

class A {
    //final public int getResult(int a, int b) {
    //        return 0;
    //    }
    //
    // final public int getResult(int a, int b) - при таком методе будет ошибка в Test,
    //т.к. класс В будет наследовать класс А и метод getResult() будет иметь одинаковую сигнатуру,
    //т.е. как бы захочет переопределить этот метод, но FINAL не разрешает переопределять методы!!!
    public int getResult(int a, int b) {
        return 0;
    }
}

class B extends A {
    public int getResult(int a, int b) {
        return 1;
    }
}

public class Test {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        System.out.println("x= " + b.getResult(0, 1));
        System.out.println("y= " + a.getResult(0, 1));
    }

}
