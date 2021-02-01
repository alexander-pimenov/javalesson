package ru.pimalex1978.concurrent.switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class Switcher2 {
    public static void main(String[] args) {
        MasterSlave checking = new MasterSlave();
        Thread first = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checking.tryMaster();
                checking.doneMaster();
            }
        }, "Thread A");
        Thread second = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checking.trySlave();
                checking.doneSlave();
            }
        }, "Thread B");

        second.start();
        first.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

@ThreadSafe
class MasterSlave {
    @GuardedBy("this")
    private AtomicBoolean isMaster = new AtomicBoolean(false);

    public synchronized void tryMaster() {
        while (isMaster.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        while (!isMaster.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        System.out.println(Thread.currentThread().getName());
        isMaster.set(true);
        notifyAll();
    }

    public synchronized void doneSlave() {
        System.out.println(Thread.currentThread().getName());
        isMaster.set(false);
        notifyAll();
    }
}
