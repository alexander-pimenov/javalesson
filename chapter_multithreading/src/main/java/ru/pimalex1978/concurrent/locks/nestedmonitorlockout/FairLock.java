package ru.pimalex1978.concurrent.locks.nestedmonitorlockout;

import java.util.ArrayList;
import java.util.List;

//http://tutorials.jenkov.com/java-concurrency/nested-monitor-lockout.html
// Реализация Fair Lock (справедливая блокировка) с проблемой блокировки вложенного монитора
/*
 * Вы можете заявить, что никогда не реализуете блокировку, подобную показанной
 * ранее. То, что вы бы не вызывали wait() и notify() для внутреннего объекта
 * монитора, а скорее для this. Вероятно, это правда. Но есть ситуации, в
 * которых могут возникнуть конструкции, подобные приведенному выше. Например,
 * если вы хотите реализовать справедливость fairness в Lock. При этом вы хотите,
 * чтобы каждый поток вызывал wait() для каждого своего объекта очереди,
 * чтобы вы могли уведомлять потоки по одному.
 * */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {

        QueueObject queueObject = new QueueObject();

        synchronized (this) {
            waitingThreads.add(queueObject);

            while (isLocked || waitingThreads.get(0) != queueObject) {

                synchronized (queueObject) {
                    try {
                        queueObject.wait();
                    } catch (InterruptedException e) {
                        waitingThreads.remove(queueObject);
                        throw e;
                    }
                }
            }
            waitingThreads.remove(queueObject);
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            QueueObject queueObject = waitingThreads.get(0);
            synchronized (queueObject) {
                queueObject.notify();
            }
        }
    }
}
