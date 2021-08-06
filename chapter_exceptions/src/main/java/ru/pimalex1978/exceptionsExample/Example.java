package ru.pimalex1978.exceptionsExample;

public class Example {

    /*покажем как выбросить исключение
     * если мы выбрасываем исключение, то тогда его нужно явным образом
     * обозначить в методе method1
     * и таким образом мы показываем, что этот метод может выбросить исключение*/
    public void method1() throws Exception {
        throw new Exception();//бросаем вновь созданный объект исключения
    }

    public void method2() throws ExapleException {
        throw new ExapleException("Text method2!!!");
    }

    public void method3() throws NewException {
        throw new NewException();
    }
}
