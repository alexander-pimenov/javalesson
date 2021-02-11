package ru.pimalex1978.concurrent.deadlock;

/* Если lock2 и lock1 в synchronized блоках поменять местами,
 * использовать Вариант 2, то deadlock не будет. Потоки будут работать последовательно.
 * Один поток будет ждать другого.*/
public class RunnableSync2 implements Runnable {
    private Object lock1 = null;
    private Object lock2 = null;

    public RunnableSync2(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    //Вариант 1
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " attempting to lock Lock 2");
        synchronized (lock2) {
            System.out.println(threadName + " locked Lock 2");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //ignore
            }

            System.out.println(threadName + " attempting to lock Lock 1");
            synchronized (lock1) {
                System.out.println(threadName + " locked Lock 1");
                //to do the work - now that both locks have been acquired (locked by this thread)
                //для выполнения работы - теперь, когда обе блокировки были получены (заблокированы этим потоком)
            }
            System.out.println(threadName + " unlock Lock 1");
        }
    }

    //Вариант 2
//    @Override
//    public void run() {
//        String threadName = Thread.currentThread().getName();
//
//        System.out.println(threadName + " attempting to lock Lock 1");
//        synchronized (lock1) {
//            System.out.println(threadName + " locked Lock 1");
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                ignore
//            }
//
//            System.out.println(threadName + " attempting to lock Lock 2");
//            synchronized (lock2) {
//                System.out.println(threadName + " locked Lock 2");
//                to do the work - now that both locks have been acquired (locked by this thread)
//                для выполнения работы - теперь, когда обе блокировки были получены (заблокированы этим потоком)
//            }
//            System.out.println(threadName + " unlock Lock 2");
//        }
//    }

}
