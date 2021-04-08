package ru.pimalex1978.try_catch_finally;
/*Как видно из примера, что блок finally отрабатывает всегда*/
public class TryCatchFinallyDemo {
    public static void main(String[] args) {
        System.out.println("==Пример работы сблоками try-catch-finally==");
        System.out.println("==Что выведется в консоль при разных способах?==");
        try {
            System.out.println("A");
            badMethod();
            System.out.println("B");
        } catch (Exception ex) {
            System.out.println("C");
        } finally {
            System.out.println("D");
        }
    }

    //для примера нужно проверить разный закомментированный код 1 или 2
    public static void badMethod() {
        throw new RuntimeException(); //1-й
//        throw new Error(); //2-й
    }
}
