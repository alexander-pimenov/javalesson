package ru.pimalex1978.concurrent.pools.example8;

/*
 * https://howtodoinjava.com/java/multi-threading/how-to-use-blockingqueue-and-threadpoolexecutor-in-java/
 * */
public class DemoTask implements Runnable {
    private String name = null;

    public DemoTask(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("Executing : " + name);
        System.out.println("Executing " + name + " inside: "
                + Thread.currentThread().getName());
    }
}