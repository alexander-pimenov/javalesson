package ru.pimalex1978.concurrent.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

/**
 * Класс блокирующей очереди.
 * Методы добавления элемента в очередь put() и получения элемента из
 * очереди get() синхронизованны. Монитором синхронизации выступает
 * сам объект блокирующей очереди BlockingQueue.
 * Наша очередь параметризована объектом Generic.
 * В этой очереди, в ее методах, используются методы wait() и notify(),
 * чтобы мы смогли сделать блокирующую очередь.
 * Метод put() добавляет элементы в очередь. Метод get() получает элементы
 * из очереди, но если очередь пуста, то поток вызвавший этот метод вызывает
 * метод wait(), находящийсяв этом методе get(), и поток блокируется,
 * исполнение останавливается до тех пор пока другой поток не вызовет
 * метод put(), положит элемент в очередь и вызовет метод notify(), который
 * пробудит поток, остановленный на методе wait() и исполнение кода метода
 * get() продолжится.
 */
@ThreadSafe
public class BlockingQueueGeneric<T> {

    /*контейнер для задач, ожидающих выполнение. Т.е. реализуем очередь на ArrayList*/
    @GuardedBy("this")
    private ArrayList<T> queue = new ArrayList<>();

    //так будем задавать максимальное количество элементов в очереди
    private final int limitBound;

    public BlockingQueueGeneric(int limitBound) {
        this.limitBound = limitBound;
    }

    /**
     * Метод добавления элемента в очередь.
     *
     * @param value объект, который будем добавлять в очередь.
     */
    public synchronized void offer(T value) {
        //Если очередь заполнена, то ждать.
        while (queue.size() == limitBound) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(value);
        this.notifyAll();
    }

    /**
     * Метод получения элемента из очереди.
     * Пробрасываем InterruptedException в сигнатуре метода,
     * чтобы правильно обрабатывать его в клиентском коде, т.е.
     * в той программе, которая будет использовать очередь.
     */
    public synchronized T poll() {
        /*Пока в очереди ничего нет - ждать.*/
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        /*Берем первый элемент из очереди.*/
        T value = queue.get(0);
        /*Удаляем этот элемент из очереди.*/
        queue.remove(value);
        /*Возвращаем взятый элемент.*/
        this.notifyAll();
        return value;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
