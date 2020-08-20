package ru.pimalex1978.concurrent.intro_in_multithreading;

/*Этот класс для того, чтобы посмотреть что выведется в консоль
 * Результат: TestTest*/
public class TestRun extends Thread {
    @Override
    public void run() {
        System.out.print("Test");
    }

    public static void main(String[] args) {
        TestRun test = new TestRun();
        test.run();
        test.start();
    }
}
