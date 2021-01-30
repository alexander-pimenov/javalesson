package ru.pimalex1978.concurrent.switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * В этом задании нужно многопоточные нити сделать последовательными.
 * Есть две нити. Нужно, чтобы сначала нить А печатала на консоль текст,
 * а потом нить В.
 * Нить А всегда печатает первой.
 * Для реализации работы нужно использовались методы wait, notify, notifyAll.
 */
@ThreadSafe
public class MasterSlaveBarrier {
    @GuardedBy("this")
    private final AtomicInteger count = new AtomicInteger(0);

    public synchronized void tryMaster() {
        while (count.get() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        while (count.get() != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        System.out.println(Thread.currentThread().getName());
        count.incrementAndGet();
        notifyAll();
    }

    public synchronized void doneSlave() {
        System.out.println(Thread.currentThread().getName());
        count.decrementAndGet();
        notifyAll();
    }
}