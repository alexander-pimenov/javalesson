package ru.pimalex1978.concurrent.pools.example9;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
public class BlockingQueue<T> {
    /*контейнер для задач, ожидающих выполнение. Т.е. реализуем очередь на ArrayList*/
    @GuardedBy("this")
    private ArrayList<T> queue = new ArrayList<>();

    private final int maxSizeQueue;

    public BlockingQueue(int bound) {
        this.maxSizeQueue = bound;
    }

    /*
     * Метод добавляющий задачи в очередь. Он также синхронизированный.
     * В момент когда вызыватся notify(), поток, заблокированный в
     * методе wait(), метода get() будет разбужен. И когда выполнение
     * кода в методе put() закончится, тогда освободится монитор, и
     * потом поток заблокированный в методе wait() получит этот монитор
     * обратно, и продолжит выполнение кода.
     */
    public synchronized void put(T task) throws InterruptedException {
        while (queue.size() >= maxSizeQueue) {
            wait();
        }
        queue.add(task);
        notifyAll();
    }

    /*
     * Метод получения задач из очереди.
     * Он синхронизирован. Монитором служит экземпляр очереди
     * BlockingQueue.
     * Метод возвращает задачу из очереди. Но если очередь пустая, то
     * вызывающий поток блокируется методом wait(). Метод wait()
     * освобождает МОНИТОР СИНХРОНИЗАЦИИ, но этот поток блокируется
     * в этом месте до тех пор пока на этом же мониторе другим потоком
     * не будет вызван метод notify(). Метод notify() будет вызван
     * потоком в методе put(). Как только другим потоком, но с захваченным
     * монитором объекта BlockingQueue, будет вызван в методе put()
     * метод notify() и код в методе put() закончится, так сразу
     * будет освобожден монитор. И метод ожидающий в методе wait()
     * захватит снова монитор, то он проснется и сможет продолжить
     * свою работу дальше по коду. И возмет задачу из очереди.
     *
     */
    public synchronized T get() throws InterruptedException {
        /*Пока в очереди ничего нет - ждать.*/
        while (queue.isEmpty()) {
            wait();
        }
        /*Берем первый элемент из очереди.*/
        T task = queue.get(0);
        /*Удаляем эту задачу из очереди.*/
        queue.remove(task);
        /*Возвращаем взятую задачу.*/
        notifyAll();
        return task;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
