package ru.pimalex1978.concurrent.pools.mythreadpool;

public class Simpl {
    public static void main(String[] args) throws InterruptedException {
        THreadPool tHreadPool = new THreadPool();
        TaskExecutor[] arr = fillTask(20);
        Thread.sleep(5000);
    }

    private static TaskExecutor[] fillTask(int size) {
        TaskExecutor[] arr = new TaskExecutor[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new TaskExecutor() {
                @Override
                public void go() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return arr;
    }
}
