package ru.pimalex1978.concurrent.pools.example3;

public class SimpleThread extends Thread {

    private final SimpleBlockingQueue<Runnable> tasks;

    public SimpleThread(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isInterrupted() || !tasks.isEmpty()) {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}