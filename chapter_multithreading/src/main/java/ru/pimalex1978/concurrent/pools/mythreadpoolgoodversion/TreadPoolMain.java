package ru.pimalex1978.concurrent.pools.mythreadpoolgoodversion;

public class TreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("The Main thread started!");

        //для примера создаем разные пулы потоков по умолчанию и с параметрами
        ThreadPool threadPool = new ThreadPool();
        ThreadPool threadPool_3_8 = new ThreadPool(3,8);

        //добавляем в пулы задачи
        threadPool.work(new Taska(100));
        threadPool.work(new Taska(200));
        threadPool.work(new Taska(300));
        threadPool.work(new Taska(400));
        threadPool.work(new Taska(500));
        threadPool.work(new Taska(600));
        threadPool.work(new Taska(700));
        threadPool.work(new Taska(800));

//        Thread.sleep(5000);

        int runnableCount = 15;
        Runnable r;
        for (int i = 0; i < runnableCount; i++) {
            r = new Taska(i);
            threadPool.work(r);
            threadPool_3_8.work(r);
        }

        //ждем пока в очереди поступают задачи
        threadPool.waitUntilAllTasksFinished();
        threadPool_3_8.waitUntilAllTasksFinished();

        //закрываем пулы потоков
        threadPool.shutdown();
        threadPool_3_8.shutdown();

        System.out.println("The Main thread has finished running!");
    }
}

class Taska implements Runnable {

    //у каждой задачи будет id
    private int id;

    Taska(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Executing " + this.toString()
                + " inside : " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();//для правильной обработки перевыставим флаг
        }
        System.out.println(this.toString() + " end..");
    }

    @Override
    public String toString() {
        return "Taska{id=" + id + '}';
    }
}
