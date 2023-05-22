import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {
    private static Integer counter = 0;
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> counter++).start();
        new Thread(() -> counter++).start();

        new Thread(atomicCounter::getAndIncrement).start();
        new Thread(atomicCounter::getAndIncrement).start();

        System.out.println(counter);
        System.out.println(atomicCounter.get());
    }

    //Если мы будем использовать просто counter, то есть вероятность, что
    //разные треды не успеют увеличить значение counter закончится метод main
    //и результат может быть разный:
    //  read0() 0
    //  read1() 0
    //  add0 1
    //  add1 1
    //  write 1
    //  write 1
    //
    //  //0,1,2
}
