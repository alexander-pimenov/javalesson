package ru.pimalex1978.concurrent;

public class ThreadRunner {
    public void test() throws InterruptedException {
        for (int i = 1; i < 100; i++) {
            int num = i;
            var thread = new Thread(() -> System.out.println(num));
            thread.start();
        }
    }
}
