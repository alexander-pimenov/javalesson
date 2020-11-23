package ru.pimalex1978.concurrent.job4j;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread another = new Thread(
                () -> System.out.println("нить another -> " + Thread.currentThread().getName())
        );
        Thread second = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("нить second -> " + Thread.currentThread().getName());
                    }
                }
        );
        Thread third = new Thread(
                () -> System.out.println("нить third -> " + Thread.currentThread().getName())
        );

        another.start();
        second.start();
        third.start();
//        Для вызова нити исполнения вызываем только метод start() !!!
//        Не путать с run()
//        another.run();
//        second.run();
//        third.run();
        Thread.sleep(3000);
        System.out.println("main метод -> " + Thread.currentThread().getName());
    }
}
//Вывод результата:
//нить second -> Thread-1
//нить third -> Thread-2
//нить another -> Thread-0
//main метод -> main