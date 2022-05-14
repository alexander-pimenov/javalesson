package ru.pimalex1978.try_catch_finally;

/*Как видно из примера, что блок finally отрабатывает всегда
* и во второй catch-блок мы даже не заходим, а сразу после первого
* пробрасываемся в блок finally
* Это нужно помнить.*/
public class TryCatchFinally2Demo {
    public static void main(String[] args) {
        System.out.println("==Пример работы сблоками try-catch-finally==");
        System.out.println("==Что выведется в консоль при разных способах?==");
        try {
            System.out.println("1");
            throw new MyExc1("выброс new MyExc1");
        } catch (MyExc1 e) {
            System.out.println("2");
            throw new MyExc2("выброс new MyExc2");
        } catch (MyExc2 e) {
            System.out.println("3");
            throw new MyExc3("выброс new MyExc3");
        } finally {
            System.out.println("4");
            throw new MyExc4("выброс new MyExc4");
        }
    }
}

class MyExc1 extends RuntimeException {
    public MyExc1(String message) {
        super(message);
    }
}

class MyExc2 extends RuntimeException {
    public MyExc2(String message) {
        super(message);
    }
}

class MyExc3 extends RuntimeException {
    public MyExc3(String message) {
        super(message);
    }
}

class MyExc4 extends RuntimeException {
    public MyExc4(String message) {
        super(message);
    }
}