package ru.pimalex1978.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/*https://www.youtube.com/watch?v=r1PdmOLrfc4*/
public class CarBooking {
    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Thread th1 = new Thread(new CabService(cyclicBarrier));
        th1.setName("Passenger1");
        th1.start();
        Thread.sleep(12000);

        Thread th2 = new Thread(new CabService(cyclicBarrier));
        th2.setName("Passenger2");
        th2.start();
        Thread.sleep(12000);

        Thread th3 = new Thread(new CabService(cyclicBarrier));
        th3.setName("Passenger3");
        th3.start();
        Thread.sleep(12000);
    }
}


