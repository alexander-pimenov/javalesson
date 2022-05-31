package ru.pimalex1978.concurrent.pools.threadpooljenkov;

/*http://tutorials.jenkov.com/java-concurrency/thread-pools.html */
public class ThreadPoolMain {

    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(3, 10);

//        for (int i = 0; i < 20; i++) {
//            int taskNo = i;
//            threadPool.execute(() -> {
//                String message = Thread.currentThread().getName() + ": Task "
//                        + taskNo;
//                System.out.println(message);
//            });
//        }

        int runnableCount = 15;
        Runnable r;
        for (int i = 0; i < runnableCount; i++) {
            r = new TaskJenkov(i);
            threadPool.execute(r);
        }

        threadPool.waitUntilAllTasksFinished();

        threadPool.stop();
    }
}

// Задача класса для выполнения (Шаг 1)
class TaskJenkov implements Runnable {
    //у каждого потока будет id
    private int id;

    TaskJenkov(int id) {
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
        return "Task{id=" + id + '}';
    }
}
