package ru.pimalex1978.concurrent.locks.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*https://www.youtube.com/watch?v=r1PdmOLrfc4
* В примере видим, что только после того, как все потоки-пасажиры
* "собрались" всместе, они "поехали на самолет", т.е. продолжили свою работу. */
public class CarBooking {
    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Thread th1 = new Thread(new CabService(cyclicBarrier));
        th1.setName("Passenger1");
        th1.start();
        Thread.sleep(8000);

        Thread th2 = new Thread(new CabService(cyclicBarrier));
        th2.setName("Passenger2");
        th2.start();
        Thread.sleep(8000);

        Thread th3 = new Thread(new CabService(cyclicBarrier));
        th3.setName("Passenger3");
        th3.start();

        Thread.sleep(12000);
    }
}

class CabService implements Runnable {
    private CyclicBarrier cyclicBarrier;

    CabService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("++++++++++ " + Thread.currentThread().getName()
                    + " has arrived");
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("++++++++++ " + Thread.currentThread().getName()
                    + " is going to board the cab.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//Output
//++++++++++ Passenger1 has arrived
//++++++++++ Passenger2 has arrived
//++++++++++ Passenger3 has arrived
//++++++++++ Passenger3 is going to board the cab.
//++++++++++ Passenger1 is going to board the cab.
//++++++++++ Passenger2 is going to board the cab.


